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
        v-model="searchForm.employeeId"
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
        style="margin-left: 8px;"
        icon="el-icon-search"
        @click="handleFilter"
      >{{ $t("table.search") }}</el-button>
      <el-button v-if="false"
        class="filter-item"
        style="margin-left: 8px;"
        type="primary"
        size="mini"
        icon="el-icon-plus"
        @click="handleCreate"
      >{{ $t("button.add") }}</el-button>
      <el-button
        class="filter-item"
        style="margin-left: 8px;"
        type="primary"
        size="mini"
        icon="el-icon-download"
        @click="handleCreate"
      >{{ $t("button.export") }}</el-button>
      <el-upload
        class="filter-item"
        action="system/exceloperation/importPersonalInfo"
        :before-upload="handleUpload"
        :on-success="handleSuccess"
        :on-error="handleError"
        :show-file-list="false"
        accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
      >
        <el-button
          size="mini"
          style="margin-left: 8px;"
          icon="el-icon-upload2"
          type="primary"
        >{{ $t("button.import") }}</el-button>
      </el-upload>
      <el-button
        class="filter-item"
        style="margin-left: 8px;"
        type="primary"
        size="mini"
        icon="el-icon-download"
        @click="handleTemplate"
      >{{ $t("button.template") }}</el-button>
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
      @sort-change="handleSort"
    >
      <el-table-column
        header-align="center"
        align="center"
        :label="$t('table.id')"
        width="50"
        fixed="left"
        type="index"
      ></el-table-column>
      <el-table-column
        min-width="100px"
        header-align="center"
        label="员工编号"
        sortable="custom"
        fixed="left"
        prop="employeeID"
      ></el-table-column>
      <el-table-column
        min-width="120px"
        header-align="center"
        label="员工姓名"
        sortable="custom"
        fixed="left"
        prop="employeeName"
      ></el-table-column>
      <el-table-column width="80" header-align="center" label="性别" sortable="custom" prop="sex"></el-table-column>
      <el-table-column
        min-width="100"
        header-align="center"
        label="入职日期"
        sortable="custom"
        prop="employmentDate"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.employmentDate | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column
        min-width="100"
        header-align="center"
        label="是否骨干"
        sortable="custom"
        prop="ifBackBone"
      ></el-table-column>
      <el-table-column
        min-width="100"
        header-align="center"
        label="合作模式"
        sortable="custom"
        prop="cooperationMode"
      ></el-table-column>
      <el-table-column
        min-width="100"
        header-align="center"
        label="产品线"
        sortable="custom"
        prop="bu"
      ></el-table-column>
      <el-table-column min-width="120" header-align="center" label="DU" sortable="custom" prop="du"></el-table-column>
      <el-table-column
        min-width="230"
        header-align="center"
        label="PDU"
        sortable="custom"
        prop="pdu"
      ></el-table-column>      
      <el-table-column align="center" :label="$t('table.option')" width="80" header-align="center">
        <template slot-scope="scope">
          <el-button v-if="false"
            type="text"
            size="mini"
            icon="el-icon-edit"
            title="编辑"
            @click="handleEdit(scope.row);"
          ></el-button>
          <el-button
            type="text"
            size="mini"
            icon="el-icon-delete"
            title="删除"
            @click="handleDel(scope.row.employeeID);"
          ></el-button>
          <el-button
            type="text"
            size="mini"
            icon="el-icon-view"
            title="查看详情"
            @click="handleView(scope.row.employeeID);"
          ></el-button>
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
    <div style="display:none">
      <form
        ref="templateForm"
        target="downloadFrame"
        id="downloadTemplate"
        action="system/exceloperation/download/personalInfoTemplate"
        method="post"
      ></form>
      <iframe id="downloadFrame" name="downloadFrame"></iframe>
    </div>
  </div>
</template>

<script>
import { formatDate } from "@/utils/date";
import { mapGetters } from "vuex";
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
      list: [],
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
  computed: {
    ...mapGetters(["employeeId", "employeeName"])
  },
  mounted() {
    this.getProductInfo();
    this.getEmployeeList();
  },
  methods: {
    getProductInfo() {
      let vm = this;
      vm.$store.dispatch("getProductInfo").then(data => {
        vm.productOptions = data;
      });
    },
    getDUInfo(product) {
      let vm = this;
      if (!product) {
        vm.duOptions = [];
        vm.pduOptions = [];
        vm.searchForm.du = "";
        vm.searchForm.pdu = "";
        return;
      }
      vm.$store.dispatch("getDUInfo", { bu: product }).then(data => {
        if (data) {
          vm.duOptions = data;
        } else {
          vm.duOptions = [];
        }
        vm.searchForm.du = "";
        vm.searchForm.pdu = "";
      });
    },
    getPDUInfo(product, du) {
      let vm = this;
      vm.$store.dispatch("getPDUInfo", { bu: product, du: du }).then(data => {
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
        pageInfo: {
          currPage: vm.page.currentPage,
          pageSize: vm.page.pageSize,
          sortColumn: vm.page.sortColumn,
          sortType: vm.page.sortType
        },
        baseStaffInfo: {
          bu: vm.searchForm.product,
          du: vm.searchForm.du,
          pdu: vm.searchForm.pdu,
          employeeID: vm.searchForm.employeeId,
          employeeName: vm.searchForm.employeeName
        }
      };
      vm.listLoading = true;
      vm.$store
        .dispatch("getEmployeeList", condition)
        .then(res => {
          if (res.success) {
            vm.list = res.data.baseStaffInfos;
            vm.page.totalRecord = res.data.listSize;
          } else {
            vm.list = [];
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
            employeeID: vm.employeeId
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
    handleSort(column) {
      if (column.prop) {
        this.page.sortColumn = column.prop;
        this.page.sortType = column.order === "descending" ? "desc" : "asc";
        this.getEmployeeList();
      }
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
        vm.$store.dispatch("delEmployeeInfo", id).then(res => {
          if (res.success) {
            vm.$message.success(res.message);
            vm.getEmployeeList();
          } else {
            vm.$message.error(res.message);
          }
        });
      });
    },
    handleView(id) {
      this.$router.push({ path: "/employeeManagement/employeeDetail/" + id });
    },
    handleEdit(rowData) {
      this.$message.info("功能正在完善中。。。");
    },
    handleUpload(file) {
      var ext = file.name.substring(file.name.lastIndexOf(".") + 1);
      const extension = ext === "xls" || ext === "xlsx";
      const isLt2M = file.size / 1024 / 1024 < 10;
      if (!extension) {
        this.$message({
          message: "上传文件只能是 xls、xlsx格式!",
          type: "warning"
        });
      }
      if (!isLt2M) {
        this.$message({
          message: "上传文件大小不能超过 10MB!",
          type: "warning"
        });
      }
      return extension || (extension2 && isLt2M);
    },
    handleSuccess() {
      this.$message.success("文件导入成功");
      this.page.currentPage = 1;
      this.getEmployeeList();
    },
    handleError() {
      this.$message.error("文件导入失败,请检查文件格式是否合法");
    },
    handleTemplate() {
      this.$refs.templateForm.submit();
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
