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
          :action="importAction()"
          :before-upload="handleUpload"
          :on-success="handleSuccess"
          :on-error="handleError"
          :show-file-list="false"
          :disabled="isEdit"
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
      <el-form :model="tableForm" ref="tableForm">
        <el-table
          v-loading="listLoading"
          :data="tableForm.dataTable"
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
            :sortable="sortable"
            prop="employeeID"
          ></el-table-column>

          <el-table-column
            width="100px"
            header-align="center"
            label="员工姓名"
            :sortable="sortable"
            prop="employeeName"
          ></el-table-column>
          <el-table-column
            width="70px"
            header-align="center"
            label="属性"
            :sortable="sortable"
            prop="types"
          ></el-table-column>
          <el-table-column
            min-width="100"
            header-align="center"
            label="得分时间"
            :sortable="sortable"
            prop="scoreTime"
          >
            <template slot-scope="scope">
              <span>{{ scope.row.scoreTime | formatDate }}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="100"
            header-align="center"
            label="名称"
            :sortable="sortable"
            prop="affairName"
          >
            <template slot-scope="scope">
              <span v-if="scope.row.types!=='评价'">{{scope.row.affairName}}</span>
              <el-popover v-else placement="right-end" title="评价详情" width="600" trigger="hover">
                <el-table
                  :data="scope.row.personalTranAndDimeScores"
                  border
                  fit
                  size="mini"
                  stripe
                  max-height="400"
                  tooltip-effect="dark"
                  style="width: 100%;"
                >
                  <el-table-column
                    header-align="center"
                    align="center"
                    :label="$t('table.id')"
                    width="50"
                    type="index"
                  ></el-table-column>
                  <el-table-column
                    prop="dimensionName"
                    header-align="center"
                    label="考核维度"
                    width="150"
                  ></el-table-column>
                  <el-table-column prop="score" header-align="center" label="得分" width="100"></el-table-column>
                  <el-table-column
                    prop="evaluation"
                    header-align="center"
                    label="评价"
                    show-overflow-tooltip
                  ></el-table-column>
                </el-table>
                <el-button type="text" slot="reference">{{scope.row.affairName}}</el-button>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
            width="110"
            header-align="center"
            label="所属系列"
            :sortable="sortable"
            prop="series"
          ></el-table-column>
          <el-table-column
            width="120"
            header-align="center"
            label="所属产品线"
            :sortable="sortable"
            prop="bu"
          ></el-table-column>
          <el-table-column
            min-width="70"
            header-align="center"
            label="得分"
            :sortable="sortable"
            prop="score"
          >
            <template slot-scope="scope">
              <el-form-item
                v-if="scope.row.isEdit"
                :prop="'dataTable.'+scope.$index+'.score'"
                :rules="rules.score"
              >
                <el-input
                  style="width:80px"
                  v-model="scope.row.score"
                  size="mini"
                  controls-position="right"
                ></el-input>
              </el-form-item>
              <span v-else>{{scope.row.score}}</span>
            </template>
          </el-table-column>
          <el-table-column
            v-if="!isEdit"
            min-width="150"
            header-align="center"
            label="成本中心"
            :sortable="sortable"
            prop="costCenter"
          ></el-table-column>
          <el-table-column
            width="110"
            header-align="center"
            label="最后修改人"
            :sortable="sortable"
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
                @click="handleFilter(null, page, true);"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <el-row type="flex" justify="end">
        <el-pagination
          @current-change="handleCurrentChange"
          :current-page="page.currentPage"
          :page-size="page.pageSize"
          layout="total, slot, prev, pager, next"
          :total="page.totalRecord"
          :disabled="isEdit"
          prev-text="上一页"
          next-text="下一页"
        ></el-pagination>
      </el-row>
    </div>
    <el-dialog
      title="修改考核维度得分"
      :visible.sync="dialogSetVisible"
      width="50%"
      :close-on-click-modal="false"
    >
      <el-form :model="tableForm" size="mini" ref="tableForm">
        <el-table
          :data="tableForm.dimensionList"
          border
          fit
          size="mini"
          stripe
          max-height="185"
          tooltip-effect="dark"
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
          <el-table-column prop="dimensionName" header-align="center" label="考核维度" width="150"></el-table-column>
          <el-table-column prop="score" header-align="center" label="得分" width="130">
            <template slot-scope="scope">
              <el-form-item
                label
                :prop="'dimensionList.'+scope.$index+'.score'"
                :rules="rules.score"
              >
                <el-input v-model="scope.row.score" autocomplete="off" maxlength="3"></el-input>
              </el-form-item>
            </template>
          </el-table-column>
          <el-table-column prop="evaluation" header-align="center" label="评价">
            <template slot-scope="scope">
              <el-form-item label prop="evaluation">
                <el-input v-model="scope.row.evaluation" autocomplete="off"></el-input>
              </el-form-item>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleSave(tableForm.scoreInfo, true);" size="mini">保 存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";
import { formatDate } from "@/utils/date";
import { postDownLoadFile } from "@/utils/export";
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
    let validScore = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入得分"));
      }
      if (isNaN(value)) {
        return callback(new Error("只能输入整数和小数"));
      }
      if (Number(value) > 100 || Number(value) < 0) {
        return callback(new Error("只能0~100的整数和小数"));
      }
      return callback();
    };
    return {
      listLoading: false,
      isEdit: false,
      sortable: "custom",
      tableForm: {
        dataTable: [],
        dimensionList: [],
        scoreInfo: null
      },
      page: {
        currentPage: 1,
        pageSize: 100,
        totalRecord: 0,
        sortColumn: "employeeID",
        sortType: "desc"
      },
      dialogSetVisible: false,
      rules: {
        score: [{ required: true, validator: validScore, trigger: "blur" }]
      }
    };
  },

  computed: {
    ...mapGetters(["employeeId", "employeeName"]),
    ...mapState({
      searchForm: state => state.scoreDisplayStore.searchForm,
      dataSource: state => state.scoreDisplayStore.scoreData
    })
  },
  watch: {
    dataSource(data) {
      this.tableForm.dataTable = data.list;
      this.page.totalRecord = data.total;
    }
  },
  methods: {
    ...mapActions(["getScoreProduct", "getScoreSeries", "getScoreDataList"]),
    handleFilter(arg, curPage, isCancel) {
      let vm = this;
      if (!isCancel && vm.tableForm.dataTable.find(item => item.isEdit)) {
        vm.$message.warning("存在正在编辑的行，请先保存");
        return;
      }
      if (!curPage) {
        vm.page.currentPage = 1;
      }
      vm.isEdit = !!isCancel;
      if (isCancel) {
        vm.isEdit = false;
        vm.sortable = "custom";
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
      this.page.currentPage = val;
      this.handleFilter(null, this.page);
    },
    handleEdit(row) {
      if (this.tableForm.dataTable.find(item => item.isEdit)) {
        this.$message.warning("存在正在编辑的行，请先保存");
        return;
      }
      if (row.types !== "评价") {
        row.isEdit = true;
        this.sortable = false;
        this.isEdit = true;
      } else {
        this.tableForm.scoreInfo = row;
        this.dialogSetVisible = true;
        this.tableForm.dimensionList = row.personalTranAndDimeScores;
      }
    },
    handleSave(row, isAffair) {
      let vm = this;
      vm.$refs.tableForm.validate(valid => {
        if (valid) {
          row.isEdit = false;
          vm.isEdit = false;
          vm.sortable = "custom";
          row.modifier = vm.employeeId;
          row.personalTranAndDimeScores.map(item => {
            item.modifier = vm.employeeId;
            return item;
          });
          vm.$store.dispatch("editScoreInfo", row).then(res => {
            if (res.success) {
              vm.$message.success("成绩修改成功");
              vm.handleFilter(null, vm.page);
            } else {
              vm.$message.error("成绩修改失败");
            }
            vm.dialogSetVisible = false;
          });
        } else {
          return false;
        }
      });
    },
    handleDel(row) {
      if (this.tableForm.dataTable.find(item => item.isEdit)) {
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
      if (column.prop) {
        this.page.sortColumn = column.prop;
        this.page.sortType = column.order === "descending" ? "desc" : "asc";
        this.handleFilter(null, this.page);
      }
    },
    importAction() {
      return "system/exceloperation/importScore?user=" + this.employeeId;
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
      postDownLoadFile({
        url: "system/exceloperation/download/scoreTemplate",
        data: {}
      });
    },
    handleExport() {
      let vm = this;
      postDownLoadFile({
        url: "system/exceloperation/exportScore",
        data: {
          bu: vm.searchForm.product,
          series: vm.searchForm.series,
          affairName: vm.searchForm.trainName,
          types: vm.searchForm.property,
          startTime: vm.searchForm.scoreTime
            ? vm.searchForm.scoreTime[0] + ":00"
            : "",
          endTime: vm.searchForm.scoreTime
            ? vm.searchForm.scoreTime[1] + ":00"
            : "",
          pageParam: {
            currPage: 0,
            pageSize: 0,
            sortColumn: "",
            sortType: ""
          }
        }
      });
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
