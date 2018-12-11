<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-date-picker
          v-model="projectDate"
          type="daterange"
          range-separator="至"
          size="mini"
          start-placeholder="立项开始日期"
          end-placeholder="立项结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="handleFilter"
          icon="el-icon-search"
          size="mini"
        >{{ $t("table.search") }}</el-button>
        <el-button
          type="primary"
          size="mini"
          @click="handleAdd"
          icon="el-icon-plus"
        >{{ $t("table.add") }}</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      style="width: 100%"
      max-height="250"
      border
      fit
      size="mini"
      stripe
      highlight-current-row
    >
      <el-table-column
        fixed
        type="index"
        sortable
        header-align="center"
        align="center"
        width="60"
        :label="$t('table.id')"
      ></el-table-column>
      <el-table-column prop="bu" sortable header-align="center" label="产品线"></el-table-column>
      <el-table-column prop="du" sortable header-align="center" label="DU"></el-table-column>
      <el-table-column prop="pdu" sortable header-align="center" label="PDU"></el-table-column>
      <el-table-column prop="teamName" sortable header-align="center" label="项目组名称"></el-table-column>
      <el-table-column prop="pmName" sortable header-align="center" label="PM"></el-table-column>
      <el-table-column prop="projectName" sortable header-align="center" label="关联PO名称"></el-table-column>
      <el-table-column header-align="center" sortable label="状态">
        <template slot-scope="scope">{{statusMap[scope.row.status]}}</template>
      </el-table-column>
      <el-table-column prop="startTime" sortable header-align="center" label="立项时间">
        <template slot-scope="scope">
          <span>{{ scope.row.startTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="endTime" sortable header-align="center" label="结项时间">
        <template slot-scope="scope">
          <span>{{ scope.row.endTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column header-align="center" label="操作" width="80">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row type="flex" justify="end">
      <el-pagination
        @current-change="handleSizeChange"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, slot,prev, pager, next"
        :total="total"
        prev-text="上一页"
        next-text="下一页"
      ></el-pagination>
    </el-row>

    <el-dialog title="选择PO信息" :visible.sync="createVisible" width="60%">
      <el-form :model="searchForm" ref="searchForm" inline size="mini">
        <el-form-item prop="product">
          <el-input disabled v-model="teamInfo.bu" style="width:100px;"></el-input>
        </el-form-item>
        <el-form-item prop="du">
          <el-select
            v-model="searchForm.du"
            size="mini"
            clearable
            placeholder="DU"
            @change="changeDU"
            style="width:130px;"
          >
            <el-option
              v-for="item in searchForm.duOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="pdu">
          <el-select
            v-model="searchForm.pdu"
            size="mini"
            clearable
            placeholder="PDU"
            style="width:130px;"
          >
            <el-option
              v-for="item in searchForm.pduOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="po">
          <el-input v-model="searchForm.poID" style="width:130px;" placeholder="合同编号"></el-input>
        </el-form-item>
        <el-form-item prop="poName">
          <el-input v-model="searchForm.poName" style="width:130px;" placeholder="合同名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            @click="handlePOFilter"
            icon="el-icon-search"
            size="mini"
          >{{ $t("table.search") }}</el-button>
        </el-form-item>
      </el-form>
      <el-table
        ref="multiTable"
        :data="PODataSource"
        border
        fit
        size="mini"
        stripe
        height="260"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
        style="width: 100%;"
      >
        <el-table-column type="selection" header-align="center" align="center" width="45"></el-table-column>
        <el-table-column prop="du" header-align="center" label="DU" width="120"></el-table-column>
        <el-table-column prop="pdu" header-align="center" label="PDU" width="120"></el-table-column>
        <el-table-column prop="projectName" header-align="center" label="PO名称" min-width="130"></el-table-column>
        <el-table-column prop="startDate" header-align="center" label="立项时间" width="100">
          <template slot-scope="scope">
            <span>{{ scope.row.startDate | formatDate }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="endDate" header-align="center" label="结项时间" width="100">
          <template slot-scope="scope">
            <span>{{ scope.row.endDate | formatDate }}</span>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="createVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="handleConfirm();" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { formatDate } from "@/utils/date.js";
export default {
  props: ["teamInfo"],
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd");
    }
  },
  data() {
    return {
      projectDate: "",
      tableData: [],
      dataSource: [],
      PODataSource: [],
      pageSize: 100,
      currentPage: 1,
      loading: false,
      total: 0,
      createVisible: false,
      selectIds: [],
      statusMap: {
        "0": "立项",
        "1": "在研",
        "2": "结项中",
        "3": "已结项"
      }
    };
  },
  mounted() {
    if (this.teamInfo) {
      this.handleFilter(null, this.teamInfo);
    }
  },
  watch: {
    teamInfo(data) {
      this.handleFilter(null, data);
    }
  },
  computed: {
    ...mapState({
      searchForm: state => state.associatedPOStore.searchForm
    })
  },
  methods: {
    ...mapActions([
      "getAllPOInfo",
      "getPrjectRelatedPO",
      "getPOFormDU",
      "getPOFormPDUList"
    ]),
    handleFilter(arg, data) {
      let vm = this;
      let param = {
        teamId: data ? data.projectID + "" : vm.teamInfo.projectID + "",
        startDate: "",
        endDate: ""
      };
      if (vm.projectDate) {
        param.startDate = formatDate(vm.projectDate[0], "yyyy-MM-dd");
        param.endDate = formatDate(vm.projectDate[1], "yyyy-MM-dd");
      }
      vm.getPrjectRelatedPO(param).then(res => {
        if (res.success) {
          vm.dataSource = res.data;
        } else {
          vm.dataSource = [];
        }
        vm.handleSizeChange();
      });
    },
    handleSizeChange() {
      var vm = this;
      vm.total = vm.dataSource.length;
      vm.tableData = vm.dataSource.slice(
        (vm.currentPage - 1) * vm.pageSize,
        vm.currentPage * vm.pageSize
      );
    },
    handlePOFilter(row) {
      this.selectIds = [];
      this.getAllPOInfo()
        .then(res => {
          if (res.success) {
            this.PODataSource = res.data;
            if(row) {
              this.$refs.multiTable.toggleRowSelection(row)
            }            
          } else {
            this.PODataSource = [];
          }
        })
        .catch(() => {
          this.PODataSource = [];
        });
    },
    handleAdd() {
      this.searchForm.poID = "";
      this.searchForm.poName = "";
      this.searchForm.product = this.teamInfo.bu;
      this.searchForm.du = this.teamInfo.du;
      this.searchForm.pdu = this.teamInfo.pdu;
      this.createVisible = true;
      this.getPOFormDU();
      this.handlePOFilter();
    },
    handleEdit(index, row) {
      this.createVisible = true;
      this.searchForm.poID = row.projectId;
      this.searchForm.poName = row.projectName;
      this.searchForm.product = row.bu;
      this.searchForm.du = row.du;
      this.searchForm.pdu = row.pdu;
      this.getPOFormDU();
      this.handlePOFilter(row);
    },
    handleProjectApproval(index, row) {},
    handleConfirm() {
      if (this.selectIds.length <= 0) {
        this.$message.waring("请选择要关联的PO信息");
        return;
      }
      this.$store
        .dispatch("teamRelatedPO", {
          teamId: this.teamInfo.projectID + "",
          projectId: this.selectIds[0]
        })
        .then(res => {
          if (res.success) {
            this.$message.success("项目关联PO成功");
            this.handleFilter();
          } else {
            this.$message.console.error("项目关联PO失败");
          }
          this.createVisible = false;
        });
    },
    changeDU() {
      this.searchForm.pdu = "";
      this.getPOFormPDUList();
    },
    handleSelectionChange(selection) {
      this.selectIds = selection.map(item => item.projectId);
    }
  }
};
</script>

<style>
</style>