<template>
  <div class="app-container">
    <el-card class="box-card" style="width:100%" shadow="never" :body-style="{padding:'20px 20px 0px 20px'}">
      <div slot="header" class="clearfix">数据库备份</div>
      <el-row>
        <el-form
          :model="backupForm"
          size="mini"
          label-width="80px"
          ref="backupForm"
          :rules="rules"
        >
          <el-form-item label="备份原因" prop="reason">
            <el-row>
            <el-input v-model="backupForm.reason" maxlength="255" style="width:80%;margin-right:15px"></el-input>
            <el-button type="primary" @click="handleBackup()" size="mini">开始备份</el-button>
            </el-row>
          </el-form-item>
        </el-form>
      </el-row>
    </el-card>
    <el-card shadow="never" style="margin-top:10px;" :body-style="{padding:'20px 20px 0px 20px'}">
      <div slot="header" class="clearfix">数据库恢复</div>
      <el-row>
        <el-table
          v-loading="loading"
          :data="tableData"
          border
          fit
          size="mini"
          stripe
          max-height="245"
          highlight-current-row
          style="width: 100%;"
          @sort-change="handleSort"
        >
          <el-table-column
            header-align="center"
            align="center"
            :label="$t('table.id')"
            width="80"
            type="index"
          ></el-table-column>

          <el-table-column width="100px" header-align="center" label="备份人员编号" prop="employeeId"></el-table-column>

          <el-table-column width="100px" header-align="center" label="备份人员姓名" prop="employeeName"></el-table-column>
          <el-table-column width="150px" header-align="center" label="备份时间" prop="backupTime">
            <template slot-scope="scope">
              <span>{{scope.row.backupTime | formatDate}}</span>
            </template>
          </el-table-column>
          <el-table-column width="200px" header-align="center" label="备份文件名称" prop="dataName"></el-table-column>
          <el-table-column min-width="100px" header-align="center" label="备份原因" prop="reasonRemark"></el-table-column>
          <el-table-column
            align="center"
            width="80"
            header-align="center"
            :label="$t('table.option')"
          >
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="handleRestore(scope.row.dataName)">恢复</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row type="flex" justify="end">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="page.currentPage"
          :page-size="page.pageSize"
          layout="total, slot, prev, pager, next"
          :total="page.totalRecord"
          prev-text="上一页"
          next-text="下一页"
        ></el-pagination>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import { formatDate } from "@/utils/date";
import Cookies from "js-cookie";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }
  },
  data() {
    return {
      loading: false,
      activeTab: "backup",
      tableData: [],
      backupForm: {
        reason: ""
      },
      rules: {
        reason: [{ required: true, message: "请输入备份原因", trigger: "blur" }]
      },
      page: {
        currentPage: 1,
        totalRecord: 0,
        pageSize: 10
      }
    };
  },
  created() {
    this.getBackupFileList();
  },
  methods: {
    handleBackup() {
      let vm = this;
      vm.$refs.backupForm.validate(valid => {
        if (valid) {
          let formData = {
            employeeId: Cookies.get("loginName"),
            dataName: vm.backupForm.fileName,
            reasonRemark: vm.backupForm.reason
          };
          vm.$store.dispatch("backup", formData).then(res => {
            if (res.success) {
              vm.$message.success("备份成功");
              vm.backupForm.reason = "";
            } else {
              vm.$message.error("备份失败");
            }
          });
        } else {
          return false;
        }
      });
    },
    getBackupFileList() {
      let param = {
        pageNum: this.page.currentPage,
        pageSize: this.page.pageSize
      };
      this.$store.dispatch("getBackupList", param).then(res => {
        if (res.success) {
          this.tableData = res.data.list;
          this.page.totalRecord = res.data.total;
        }
      });
    },
    handleCurrentChange(val) {
      this.page.currentPage = val;
      this.getBackupFileList();
    },
    handleRestore(fileName) {
      this.$store.dispatch("restore", fileName).then(res => {
        if (res.success) {
          this.$message.success("恢复成功");
        } else {
          this.$message.error("恢复失败");
        }
      });
    }
  }
};
</script>

<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}
</style>
