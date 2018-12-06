<template>
  <div class="app-container affairs-container">
    <div class="filter-container">
      <el-select
        size="mini"
        v-model="searchForm.product"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="所属产品线"
        @change="changeProduct"
      >
        <el-option
          v-for="item in productOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
      <el-select
        size="mini"
        v-model="searchForm.du"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="所属DU"
        @change="changeDU"
      >
        <el-option
          v-for="item in duOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
      <el-select
        size="mini"
        v-model="searchForm.pdu"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="所属PDU"
      >
        <el-option
          v-for="item in pduOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
      <el-input
        v-model="searchForm.empolyeeId"
        size="mini"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="员工编号"
      ></el-input>
      <el-input
        v-model="searchForm.empolyeeName"
        size="mini"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="员工姓名"
      ></el-input>
      <el-button
        class="filter-item"
        type="primary"
        size="mini"
        icon="el-icon-search"
        @click="handleFilter"
      >{{ $t('table.search') }}</el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        size="mini"
        icon="el-icon-plus"
        @click="handleCreate"
      >{{ $t('table.add') }}</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      border
      fit
      size="mini"
      stripe
      max-height="420"
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column
        header-align="center"
        align="center"
        :label="$t('table.id')"
        width="80"
        type="index"
      ></el-table-column>
      <el-table-column min-width="150px" header-align="center" label="员工编号" sortable prop></el-table-column>
      <el-table-column
        min-width="150px"
        header-align="center"
        label="事务名称"
        sortable
        prop="affairName"
      ></el-table-column>

      <el-table-column min-width="150px" header-align="center" label="所属系列" sortable prop="series"></el-table-column>
      <el-table-column width="120px" header-align="center" label="所属产品线" sortable prop="du"></el-table-column>
      <el-table-column width="110" header-align="center" label="创建人" sortable prop="createBy"></el-table-column>
      <el-table-column min-width="110" header-align="center" label="最后更新人" sortable prop="updateBy"></el-table-column>
      <el-table-column
        min-width="140"
        header-align="center"
        label="最后更新时间"
        sortable
        prop="updateTime"
      >
        <template slot-scope="scope">
          <span>{{scope.row.updateTime | formatDate}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.option')" width="230" header-align="center">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            title="编辑"
            @click="handleEdit(scope.row)"
          ></el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-delete"
            title="删除"
            @click="handleDel(scope.row.affairID)"
          ></el-button>
          <!-- <el-button
            type="primary"
            size="mini"
            icon="el-icon-search"
            title="查看详情"
            @click="handleView(scope.row.affairID)"
          ></el-button>-->
          <router-link :to="'/personnelManagement/empolyeeDetail/1'">
            <el-button type="primary" size="mini" icon="el-icon-search" title="查看详情"></el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>
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
  </div>
</template>

<script>
import { formatDate } from "@/utils/date";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd");
    }
  },
  data() {
    let vm = this;
    return {
      duOptions: [],
      productOptions: [],
      pduOptions: [],
      searchForm: {
        product: "",
        du: "",
        pdu: "",
        employeeId: "",
        employeeName: ""
      },
      list: null,
      listLoading: false,
      page: {
        currentPage: 1,
        pageSize: 100,
        totalRecord: 0,
        sortColumn: "employeeID",
        sortType: "desc"
      }
    };
  },
  mounted() {
    this.getProductInfo();
  },
  methods: {
    getProductInfo() {
      let vm = this;
      vm.$store.dispatch("getProductInfo").then(() => {
        const data = vm.$store.getters.productList;
        if (data) {
          vm.productOptions = data;
        } else {
          vm.productOptions = [];
        }
      });
    },
    getDUInfo(product) {
      let vm = this;
      vm.$store.dispatch("getDUInfo", { bu: product }).then(() => {
        const data = vm.$store.getters.duList;
        if (data) {
          vm.duOptions = data;
        } else {
          vm.duOptions = [];
        }
      });
    },
    getPDUInfo(product, du) {
      let vm = this;
      vm.$store.dispatch("getPDUInfo", { bu: product, du: du }).then(() => {
        const data = vm.$store.getters.pduList;
        if (data) {
          vm.pduOptions = data;
        } else {
          vm.pduOptions = [];
        }
      });
    },
    getEmployeeList() {
      let vm = this;
      let condition = {
        du: vm.searchForm.product,
        series: vm.searchForm.series,
        system: vm.searchForm.system,
        affairName: vm.searchForm.affairsName
      };
      vm.listLoading = true;
      vm.$store
        .dispatch("getAffairsList", condition)
        .then(data => {
          if (data) {
            vm.initList = data;
            vm.list = vm.initList.slice(0, vm.page.pageSize);
            vm.page.totalRecord = data.length;
          } else {
            vm.list = [];
            vm.page.totalRecord = 0;
          }
          vm.listLoading = false;
        })
        .catch(() => {
          vm.list = [];
          vm.listLoading = false;
        });
    },
    changeProduct() {
      this.getDUInfo(this.searchForm.product);
    },
    changeDU() {
      this.searchForm.du = "";
      this.searchForm.pdu = "";
      if (!this.searchForm.product) {
        this.$message.warning("请选择所属产品线");
        return;
      }
      this.getPDUInfo(this.searchForm.product, this.searchForm.du);
    },
    submtForm() {
      let vm = this;
      vm.$refs.affairsForm.validate(valid => {
        if (valid) {
          let formData = {
            du: vm.affairsForm.product,
            series: vm.affairsForm.series,
            system: vm.affairsForm.system,
            affairName: vm.affairsForm.affairsName,
            employeeID: Cookies.get("loginName")
          };
          let methodName = "addAffairsInfo";
          if (vm.isEdit) {
            formData.affairID = vm.affairsForm.affairsId;
            methodName = "editAffairsInfo";
          }
          vm.$store.dispatch(methodName, formData).then(res => {
            if (res.success) {
              vm.$message.success(res.message);
              vm.dialogBaseVisible = false;
              vm.getAffairsList();
            } else {
              vm.$message.error(res.message);
            }
          });
        } else {
          return false;
        }
      });
    },
    clearValidate(formName) {
      if (this.$refs[formName]) {
        this.$refs[formName].clearValidate();
      }
    },
    handleCurrentChange(val) {
      let vm = this;
      vm.page.currentPage = val;
      vm.getEmployeeList();
    },
    handleFilter() {
      this.page.currentPage = 1;
      this.getEmployeeList();
    },
    handleCreate() {
      this.$message.info("功能正在完善中。。。");
    },
    handleDel(id) {
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delAffairsInfo", id).then(res => {
          if (res.success) {
            vm.$message.success(res.message);
            vm.getEmployeeList();
          } else {
            vm.$message.error(res.message);
          }
        });
      });
    },
    handleEdit(rowData) {
      this.isEdit = true;
      this.getProductInfo();
      this.getSeries();
      this.getSystem();
      this.affairsForm.affairsId = rowData.affairID;
      this.affairsForm.affairsName = rowData.affairName;
      this.affairsForm.series = rowData.series;
      this.affairsForm.system = rowData.system;
      this.affairsForm.product = rowData.du;
      this.dialogBaseTitle = "修改考核事务信息";
      this.dialogBaseVisible = true;
      this.clearValidate("affairsForm");
    },
    handleView(affairId) {
      let vm = this;
      // vm.dialogSetTitle = "查看考核事务维度";
      // vm.dialogSetVisible = true;
      // vm.isView = true;
      // vm.getDimensionInfo(affairId);
      vm.$router.push({
        path: "/personnelManagement/empolyeeDetail"
      });
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss">
.affairs-container {
  .el-select {
    width: 249px;
  }
}
</style>
