<template>
  <div class="app-container">
    <el-form :inline="true" :model="searchForm" class="search-condition">
      <el-row>
        <el-col :span="4">
          <el-form-item>
            <el-select
              v-model="searchForm.product"
              size="mini"
              clearable
              placeholder="产品线"
              @change="productChange"
            >
              <el-option
                v-for="item in searchForm.productOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item>
            <el-select
              v-model="searchForm.du"
              size="mini"
              clearable
              placeholder="DU"
              @change="changeDU"
            >
              <el-option
                v-for="item in searchForm.duOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item>
            <el-select v-model="searchForm.pdu" size="mini" clearable placeholder="PDU">
              <el-option
                v-for="item in searchForm.pduOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item>
            <el-input v-model="searchForm.teamName" size="mini" clearable placeholder="项目组名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item>
            <el-input v-model="searchForm.pm" size="mini" clearable placeholder="PM"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="2">
          <el-form-item>
            <el-button
              type="primary"
              size="mini"
              @click="onSearchForm"
              icon="el-icon-search"
            >{{ $t("table.search") }}</el-button>
          </el-form-item>
        </el-col>
        <el-col :span="2">
          <el-form-item>
            <el-button
              type="primary"
              size="mini"
              @click="onCreate"
              icon="el-icon-plus"
            >{{ $t("table.add") }}</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div>
      <el-table
        v-loading="listLoading"
        :data="dataTable"
        border
        fit
        size="mini"
        stripe
        highlight-current-row
        max-height="420"
        style="width: 100%"
      >
        <el-table-column
          header-align="center"
          align="center"
          type="index"
          width="80"
          sortable="true"
          :label="$t('table.id')"
        ></el-table-column>

        <el-table-column
          min-width="120px"
          header-align="center"
          :label="$t('projectGroup.product')"
          sortable
          prop="bu"
        ></el-table-column>

        <el-table-column min-width="120px" header-align="center" prop="du" label="DU" sortable></el-table-column>

        <el-table-column min-width="150px" header-align="center" prop="pdu" label="PDU" sortable></el-table-column>

        <el-table-column
          :label="$t('projectGroup.teamName')"
          header-align="center"
          min-width="150"
          sortable
          prop="projectName"
        ></el-table-column>

        <el-table-column label="PM" header-align="center" min-width="110" sortable prop="pmName"></el-table-column>

        <el-table-column
          align="center"
          :label="$t('table.option')"
          width="80"
          header-align="center"
        >
          <template slot-scope="scope">
            <el-button
              type="text"
              size="mini"
              icon="el-icon-setting"
              title="团队设置"
              @click="teamSettings(scope.row.projectID)"
            ></el-button>
            <el-button
              v-if="false"
              type="text"
              size="mini"
              icon="el-icon-edit"
              title="编辑"
              @click="projectEdit(scope.row)"
            ></el-button>
            <el-button
              type="text"
              size="mini"
              icon="el-icon-delete"
              title="删除"
              @click="projectDelete(scope.row.projectID)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-row type="flex" justify="end">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        layout="total, slot, prev, pager, next"
        :total="totalRecord"
        prev-text="上一页"
        next-text="下一页"
      ></el-pagination>
    </el-row>
    <el-dialog :title="dialogTitle" width="30%" :visible.sync="dialogVisible">
      <el-form
        :model="newForm"
        ref="newForm"
        :rules="rules"
        label-width="100px"
        class="pg-form-wrap"
      >
        <el-form-item label="产品线" prop="product">
          <el-select
            style="width:220px"
            v-model="newForm.product"
            size="mini"
            placeholder="产品线"
            @change="newProductChange"
          >
            <el-option
              v-for="item in newForm.productOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="DU" prop="du">
          <el-select
            style="width:220px"
            v-model="newForm.du"
            size="mini"
            placeholder="DU"
            @change="newDUChange"
          >
            <el-option
              v-for="item in newForm.duOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="PDU" prop="pdu">
          <el-select
            style="width:220px"
            v-model="newForm.pdu"
            size="mini"
            placeholder="PDU"
            @change="newPDUChange"
          >
            <el-option
              v-for="item in newForm.pduOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目组名称" prop="teamName">
          <el-input style="width:220px" v-model="newForm.teamName" size="mini" placeholder="项目组名称"></el-input>
        </el-form-item>
        <el-form-item label="PM" prop="employeeId">
          <el-select style="width:220px" v-model="newForm.employeeId" size="mini" placeholder="PM">
            <el-option
              v-for="item in newForm.employeeOptions"
              :key="item.staffID"
              :label="item.staffName"
              :value="item.staffID"
            >
              <span style="float: left">{{ item.staffName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.staffID }}</span>
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="onSave();" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  data() {
    return {
      listLoading: false,
      dialogTitle: "",
      dialogVisible: false,
      pageSize: 10,
      dataTable: [],
      totalRecord: 0,
      currentPage: 1,
      activeTab: "PO",
      projectID: "",
      rules: {
        product: [
          { required: true, message: "请选择产品线", trigger: "change" }
        ],
        du: [{ required: true, message: "请选择DU", trigger: "change" }],
        pdu: [{ required: true, message: "请选择PDU", trigger: "change" }],
        employeeId: [
          { required: true, message: "请选择PM", trigger: "change" }
        ],
        teamName: [
          { required: true, message: "请输入项目组名称", trigger: "blur" },
          { min: 3, max: 50, message: "长度在 3 到 50 个字符", trigger: "blur" }
        ]
      }
    };
  },
  mounted() {
    var vm = this;
    this.getPGProductInfo();
    this.getNewFormProductInfo();
    this.getProjectGroupInfo({ pageSize: vm.pageSize })
      .then(() => {
        vm.listLoading = false;
      })
      .catch(() => {
        vm.listLoading = false;
      });
  },
  computed: {
    ...mapState({
      dataSource: state => state.projectGroupStore.dataSource,
      searchForm: state => state.projectGroupStore.searchForm,
      newForm: state => state.projectGroupStore.newForm
    })
  },
  watch: {
    dataSource(data) {
      this.dataTable = data.datas;
      this.totalRecord = data.totleSize;
    }
  },
  methods: {
    ...mapActions([
      "getPGProductInfo",
      "getPGDU",
      "getPGPDUList",
      "getProjectGroupInfo",
      "getNewFormProductInfo",
      "getNewFormDU",
      "getNewFormPDUList",
      "queryProjectManagers"
    ]),
    onSearchForm(arg, curPage) {
      let vm = this;
      vm.listLoading = true;
      vm.currentPage = curPage || 1;
      this.getProjectGroupInfo({
        pageNo: vm.currentPage,
        pageSize: vm.pageSize
      }).then(() => {
        vm.listLoading = false;
      });
    },
    onCreate() {
      this.newForm.product = "";
      this.newForm.du = "";
      this.newForm.pdu = "";
      this.newForm.teamName = "";
      this.newForm.employeeName = "";
      this.newForm.employeeId = "";
      this.dialogTitle = "添加项目组";
      this.dialogVisible = true;
    },
    productChange() {
      this.getPGDU();
    },
    changeDU() {
      this.getPGPDUList();
    },
    newProductChange() {
      this.getNewFormDU();
    },
    newDUChange() {
      this.getNewFormPDUList();
    },
    newPDUChange() {
      this.queryProjectManagers();
    },
    handleCurrentChange(val) {
      let vm = this;
      vm.currentPage = val;
      vm.onSearchForm(null, val);
    },
    teamSettings(projectId) {
      console.log(this.$store.getters.visitedViews);
      this.$router.push({ path: "teamSettings/" + projectId });
    },
    projectEdit(row) {
      this.newForm.product = row.product;
      this.newForm.du = row.du;
      this.newForm.pdu = row.pdu;
      this.newForm = row.teamName;
      this.newForm = row.pm;
      this.dialogTitle = "编辑项目组";
      this.dialogVisible = true;
    },
    projectDelete(id) {
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delProjectInfo", id).then(res => {
          if (res.success) {
            vm.$message.success("项目删除成功");
            vm.onSearchForm(null, vm.currentPage);
            let view = vm.$store.getters.visitedViews.find(
              item =>
                item.path.indexOf("projectManagement/teamSettings/" + id) >= 0
            );
            vm.$store.dispatch("delView", view);
          } else {
            vm.$message.error("项目删除失败");
          }
        });
      });
    },
    onSave() {
      let vm = this;
      vm.$refs.newForm.validate(valid => {
        if (valid) {
          let formData = {
            pmID: vm.newForm.employeeId,
            pmName: vm.newForm.employeeOptions.find(
              item => item.staffID === vm.newForm.employeeId
            ).staffName,
            bu: vm.newForm.product,
            du: vm.newForm.du,
            pdu: vm.newForm.pdu,
            projectName: vm.newForm.teamName
          };
          vm.$store.dispatch("addProjectInfo", formData).then(res => {
            if (res.success) {
              vm.$message.success("项目组添加成功");
              vm.onSearchForm(null, vm.currentPage);
            } else {
              vm.$message.error("项目组添加失败");
            }
            vm.dialogVisible = false;
          });
        } else {
          return false;
        }
      });
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss">
.pg-form-wrap .el-form-item {
  margin-bottom: 15px;
}
.team-setting {
  .el-dialog__body {
    padding: 10px 20px;
  }
}
</style>
