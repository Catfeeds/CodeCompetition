<template>
  <div class="app-container score-display">
    <el-form :inline="true" :model="searchForm" class="search-condition">
      <el-form-item>
        <el-select
          size="mini"
          v-model="searchForm.series"
          clearable
          placeholder="所属系列"
          style="width:120px"
        >
          <el-option
            v-for="item in searchForm.seriesOptions"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="searchForm.property"
          clearable
          size="mini"
          placeholder="属性"
          style="width:110px;"
        >
          <el-option
            v-for="item in searchForm.propertypeOptions"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="searchForm.product"
          clearable
          size="mini"
          placeholder="所属产品线"
          style="width:120px"
        >
          <el-option
            v-for="item in searchForm.productOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="searchForm.trainName"
          size="mini"
          clearable
          placeholder="培训名称"
          style="width:120px"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchForm.scoreTime"
          size="mini"
          type="datetimerange"
          range-separator="至"
          format="yyyy-MM-dd HH:mm"
          value-format="yyyy-MM-dd HH:mm"
          start-placeholder="得分开始时间"
          end-placeholder="得分结束时间"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          size="mini"
          icon="el-icon-search"
          @click="handleFilter"
        >{{ $t("table.search") }}</el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          class="filter-item"
          type="primary"
          size="mini"
          icon="el-icon-download"
          @click="handleExport"
        >{{ $t("button.export") }}</el-button>
      </el-form-item>
      <el-form-item>
        <el-upload
          class="filter-item"
          action="system/exceloperation/importScore"
          :before-upload="handleUpload"
          :on-success="handleSuccess"
          :on-error="handleError"
          :show-file-list="false"
          accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
        >
          <el-button size="mini" icon="el-icon-upload2" type="primary">{{ $t("button.import") }}</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button
          class="filter-item"
          type="primary"
          size="mini"
          icon="el-icon-download"
          @click="handleTemplate"
        >{{ $t("button.template") }}</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-table
        v-loading="listLoading"
        :data="dataTable"
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
          type="index"
        ></el-table-column>

        <el-table-column
          width="100px"
          header-align="center"
          label="软通工号"
          sortable="custom"
          prop="employeeID"
        ></el-table-column>

        <el-table-column
          width="100px"
          header-align="center"
          label="员工姓名"
          sortable="custom"
          prop="employeeName"
        ></el-table-column>
        <el-table-column
          width="90px"
          header-align="center"
          label="属性"
          sortable="custom"
          prop="types"
        ></el-table-column>
        <el-table-column
          min-width="90"
          header-align="center"
          label="得分时间"
          sortable="custom"
          prop="scoreTime"
        >
          <template slot-scope="scope">
            <span>{{ scope.row.scoreTime | formatDate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="80"
          header-align="center"
          label="名称"
          sortable="custom"
          prop="affairName"
        ></el-table-column>
        <el-table-column
          width="110"
          header-align="center"
          label="所属系列"
          sortable="custom"
          prop="series"
        ></el-table-column>
        <el-table-column
          width="120"
          header-align="center"
          label="所属产品线"
          sortable="custom"
          prop="bu"
        ></el-table-column>
        <el-table-column
          min-width="70"
          header-align="center"
          label="得分"
          sortable="custom"
          prop="score"
        >
          <template slot-scope="scope">
            <el-input-number
              v-if="scope.row.isEdit"
              style="width:80px"
              v-model="scope.row.score"
              size="mini"
              controls-position="right"
              :min="1"
              :max="100"
              :step="1"
            ></el-input-number>
            <span v-else>{{scope.row.score}}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="80"
          header-align="center"
          label="成本中心"
          sortable="custom"
          prop="costCenter"
        ></el-table-column>
        <el-table-column
          width="110"
          header-align="center"
          label="最后修改人"
          sortable="custom"
          prop="modifier"
        ></el-table-column>
        <el-table-column
          width="80"
          header-align="center"
          align="center"
          :label="$t('table.option')"
        >
          <template slot-scope="scope">
            <el-button
              v-if="!scope.row.isEdit"
              type="text"
              size="mini"
              icon="el-icon-edit"
              title="编辑"
              @click="handleEdit(scope.row);"
            ></el-button>
            <el-button
              v-if="!scope.row.isEdit"
              type="text"
              size="mini"
              icon="el-icon-delete"
              title="删除"
              @click="handleDel(scope.row);"
            ></el-button>
            <el-button
              v-if="scope.row.isEdit"
              type="text"
              size="mini"
              icon="el-icon-circle-plus-outline"
              title="保存"
              @click="handleSave(scope.row);"
            ></el-button>
            <el-button
              v-if="scope.row.isEdit"
              type="text"
              size="mini"
              icon="el-icon-remove-outline"
              title="取消"
              @click="handleFilter(null, page);"
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
    </div>
    <div style="display:none">
      <form
        ref="templateForm"
        target="downloadFrame"
        id="downloadTemplate"
        action="system/exceloperation/download/scoreTemplate"
        method="post"
      ></form>
      <iframe id="downloadFrame" name="downloadFrame"></iframe>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";
import { formatDate } from "@/utils/date";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm");
    }
  },
  mounted() {
    this.getScoreProduct();
    this.getScoreSeries();
    this.handleFilter();
  },
  data() {
    return {
      listLoading: false,
      dataTable: [],
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
    ...mapState({
      searchForm: state => state.scoreDisplayStore.searchForm,
      dataSource: state => state.scoreDisplayStore.scoreData
    })
  },
  watch: {
    dataSource(data) {
      this.dataTable = data.list;
      this.page.totalRecord = data.total;
    }
  },
  methods: {
    ...mapActions(["getScoreProduct", "getScoreSeries", "getScoreDataList"]),
    handleFilter(arg, curPage) {
      let vm = this;
      if (!curPage) {
        vm.page.currentPage = 1;
      }
      vm.listLoading = true;
      vm.getScoreDataList(vm.page)
        .then(() => {
          vm.listLoading = false;
        })
        .catch(() => {
          vm.listLoading = false;
        });
    },
    handleCurrentChange(val) {
      if(this.dataTable.find(item=>item.isEdit)) {
        this.$message.warning("存在正在编辑的行，请先保存");
        return;
      }
      this.page.currentPage = val;
      this.handleFilter(null, this.page);
    },
    handleEdit(row) {
      if(this.dataTable.find(item=>item.isEdit)) {
        this.$message.warning("存在正在编辑的行，请先保存");
        return;
      }
      row.isEdit = true;
    },
    handleSave(row) {},
    handleDel(row) {
      if(this.dataTable.find(item=>item.isEdit)) {
        this.$message.warning("存在正在编辑的行，请先保存");
        return;
      }
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delScoreInfo", row).then(res => {
          if (res.success) {
            vm.$message.success("删除成功");
            vm.handleFilter(null, vm.page);
          } else {
            vm.$message.error("删除失败");
          }
        });
      });
    },
    handleSort(column) {
      if(this.dataTable.find(item=>item.isEdit)) {
        this.$message.warning("存在正在编辑的行，请先保存");
        return;
      }
      if (column.prop) {
        this.page.sortColumn = column.prop;
        this.page.sortType = column.order === "descending" ? "desc" : "asc";
        this.handleFilter(null, this.page);
      }
    },
    handleExport() {
      this.$message.info("功能正在完善中。。。");
    },
    handleUpload(file) {
      if(this.dataTable.find(item=>item.isEdit)) {
        this.$message.warning("存在正在编辑的行，请先保存");
        return;
      }
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

<style rel="stylesheet/scss" lang="scss" scope>
.cell-zero div {
  background-color: red;
  color: #fff;
}
.score-display {
  .search-condition {
    .el-date-editor--datetimerange.el-input__inner {
      width: 270px;
    }
    .el-range-editor.el-input__inner {
      padding: 3px 0px 3px 10px;
    }
    .el-date-editor .el-range-separator {
      padding: 0px;
    }
  }
}
</style>
