<template>
  <div class="app-container start-setting">
    <el-form :inline="true" :model="searchForm" class="search-condition">
      <el-row>
        <el-col :span="3">
          <el-form-item>
            <el-select size="mini" v-model="searchForm.series" clearable placeholder="所属系列">
              <el-option
                v-for="item in searchForm.seriesOptions"
                :key="item"
                :label="item"
                :value="item"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item>
            <el-select v-model="searchForm.courseType" clearable size="mini" placeholder="课程类型">
              <el-option
                v-for="item in searchForm.courseTypeOptions"
                :key="item"
                :label="item"
                :value="item"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item>
            <el-select v-model="searchForm.product" clearable size="mini" placeholder="所属产品线">
              <el-option
                v-for="item in searchForm.productOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item>
            <el-input v-model="searchForm.trainName" size="mini" clearable placeholder="培训名称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="3">
          <el-form-item>
            <el-input v-model="searchForm.lecturer" size="mini" clearable placeholder="讲师姓名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item>
            <el-date-picker
              v-model="searchForm.trainTime"
              size="mini"
              type="datetimerange"
              range-separator="至"
              format="yyyy-MM-dd HH:mm"
              value-format="yyyy-MM-dd HH:mm"
              start-placeholder="培训开始时间"
              end-placeholder="培训结束时间"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item>
            <el-button
              type="primary"
              size="mini"
              @click="handleFilter"
              icon="el-icon-search"
            >{{ $t("table.search") }}</el-button>
            <el-button
              type="primary"
              size="mini"
              @click="handleCreate"
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
          label="开班编号"
          sortable="custom"
          prop="openingID"
        ></el-table-column>
        <el-table-column
          min-width="120px"
          header-align="center"
          label="开班名称"
          sortable="custom"
          prop="openingName"
        ></el-table-column>
        <el-table-column
          width="150px"
          header-align="center"
          label="培训名称"
          sortable="custom"
          prop="trainingName"
        ></el-table-column>
        <el-table-column
          width="100px"
          header-align="center"
          label="讲师"
          sortable="custom"
          prop="lecturer"
        ></el-table-column>
        <el-table-column
          min-width="140"
          header-align="center"
          label="培训时间"
          sortable="custom"
          prop="trainingTime"
        >
          <template slot-scope="scope">
            <span>{{ scope.row.trainingTime | formatDate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          width="110"
          header-align="center"
          label="课程类型"
          sortable="custom"
          prop="trainingType"
        ></el-table-column>
        <el-table-column
          width="110"
          header-align="center"
          label="班级状态"
          sortable="custom"
          prop="openingStatus"
        >
          <template slot-scope="scope">
            <span>{{ statusMap[scope.row.openingStatus] }}</span>
          </template>
        </el-table-column>
        <el-table-column
          width="120"
          header-align="center"
          label="所属产品线"
          sortable="custom"
          prop="bu"
        ></el-table-column>
        <el-table-column
          width="110"
          header-align="center"
          align="center"
          :label="$t('table.option')"
        >
          <template slot-scope="scope">
            <el-button
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
              @click="handleDel(scope.row.openingID);"
            ></el-button>
            <el-button
              type="text"
              size="mini"
              icon="el-icon-view"
              title="查看"
              @click="handleDetail(scope.row);"
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
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="60%">
      <el-form :model="startForm" size="mini" label-width="120px" ref="startForm" :rules="rules">
        <el-row>
          <el-col :span="8">
            <el-form-item label="所属产品线" prop="product">
              <el-input
                v-if="isDetail"
                v-model="startForm.product"
                autocomplete="off"
                required
                maxlength="64"
                readonly
              ></el-input>
              <el-select
                v-model="startForm.product"
                placeholder="请选择"
                @change="getStartTrain"
                v-else
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
          <el-col :span="8">
            <el-form-item label="所属系列" prop="series">
              <el-input
                v-if="isDetail"
                v-model="startForm.series"
                autocomplete="off"
                required
                maxlength="64"
                readonly
              ></el-input>
              <el-select
                v-else
                v-model="startForm.series"
                @change="getStartTrain"
                placeholder="请选择"
              >
                <el-option
                  v-for="item in searchForm.seriesOptions"
                  :key="item"
                  :label="item"
                  :value="item"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="所属体系" prop="system">
              <el-input
                v-if="isDetail"
                v-model="startForm.system"
                autocomplete="off"
                required
                maxlength="64"
                readonly
              ></el-input>
              <el-select
                v-else
                v-model="startForm.system"
                @change="getStartTrain"
                placeholder="请选择"
              >
                <el-option
                  v-for="item in searchForm.systemOptions"
                  :key="item"
                  :label="item"
                  :value="item"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="课程类型" prop="courseType">
              <el-input
                v-if="isDetail"
                v-model="startForm.courseType"
                autocomplete="off"
                required
                maxlength="64"
                readonly
              ></el-input>
              <el-select
                v-model="startForm.courseType"
                @change="getStartTrain"
                placeholder="请选择"
                v-else
              >
                <el-option
                  v-for="item in searchForm.courseTypeOptions"
                  :key="item"
                  :label="item"
                  :value="item"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="培训名称" prop="trainName">
              <el-input
                v-if="isDetail"
                v-model="startForm.trainName"
                autocomplete="off"
                required
                maxlength="64"
                readonly
              ></el-input>
              <el-select v-model="startForm.trainName" placeholder="请选择" v-else>
                <el-option
                  v-for="item in startForm.trainOptions"
                  :key="item.trainingId"
                  :label="item.trainingName"
                  :value="item.trainingName"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开班名称" prop="classSerices">
              <el-input
                v-model="startForm.classSerices"
                autocomplete="off"
                required
                maxlength="64"
                :readonly="isDetail"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="讲师" prop="lecturer">
              <el-input
                v-model="startForm.lecturer"
                autocomplete="off"
                :readonly="isDetail"
                maxlength="50"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="培训时间" prop="trainTime">
              <el-date-picker
                v-model="startForm.trainTime"
                type="datetime"
                size="mini"
                format="yyyy-MM-dd HH:mm"
                :readonly="isDetail"
                prefix-icon="null"
                placeholder="选择培训时间"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="语言" prop="language">
              <el-input
                v-if="isDetail"
                v-model="startForm.language"
                autocomplete="off"
                required
                maxlength="64"
                readonly
              ></el-input>
              <el-select v-model="startForm.language" placeholder="请选择" v-else>
                <el-option
                  v-for="item in startForm.languageOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="开班形式" prop="openClassType">
              <el-input
                v-model="startForm.openClassType"
                autocomplete="off"
                v-if="isDetail"
                readonly
                maxlength="4"
              ></el-input>
              <el-select v-model="startForm.openClassType" placeholder="请选择" v-else>
                <el-option
                  v-for="item in startForm.openClassTypeOptions"
                  :key="item"
                  :label="item"
                  :value="item"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开班地点" prop="startLocation">
              <el-input
                v-model="startForm.startLocation"
                autocomplete="off"
                :readonly="isDetail"
                maxlength="50"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="课件下载地址" prop="coursewareAddr">
              <el-input
                v-model="startForm.coursewareAddr"
                autocomplete="off"
                required
                maxlength="128"
                :readonly="isDetail"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="在线培训地址" prop="trainAddr">
              <el-input
                v-model="startForm.trainAddr"
                autocomplete="off"
                :readonly="isDetail"
                maxlength="128"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="可容纳人数" prop="capacity">
              <el-input
                v-model="startForm.capacity"
                autocomplete="off"
                :readonly="isDetail"
                maxlength="4"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否需要考试" prop="isExam">
              <el-input
                v-if="isDetail"
                v-model="startForm.isExam"
                autocomplete="off"
                required
                maxlength="64"
                readonly
              ></el-input>
              <el-select v-model="startForm.isExam" placeholder="请选择" v-else>
                <el-option
                  v-for="item in startForm.isExamOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="接口人" prop="publisher">
              <el-input
                v-model="startForm.publisher"
                autocomplete="off"
                :readonly="isDetail"
                maxlength="20"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="咨询电话" prop="telephone">
              <el-input
                v-model="startForm.telephone"
                autocomplete="off"
                :readonly="isDetail"
                maxlength="20"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="报名方式" prop="regType">
              <el-input
                v-if="isDetail"
                v-model="startForm.regType"
                autocomplete="off"
                required
                maxlength="64"
                readonly
              ></el-input>
              <el-select v-model="startForm.regType" placeholder="请选择" v-else>
                <el-option
                  v-for="item in startForm.regTypeOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="报名须知" prop="regNote">
              <el-input
                :readonly="isDetail"
                type="textarea"
                v-model="startForm.regNote"
                :autosize="{ minRows: 2, maxRows: 4 }"
                resize="none"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;" size="mini" v-if="!isDetail">取 消</el-button>
        <el-button @click="dialogVisible = false;" size="mini" v-if="isDetail">关 闭</el-button>
        <el-button type="primary" @click="submtForm('startForm');" size="mini" v-if="!isDetail">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from "vuex";
import { formatDate } from "@/utils/date";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm");
    }
  },
  computed: {
    ...mapGetters(["employeeId", "employeeName"])
  },
  data() {
    let validaNumer = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入可容纳人数"));
      }
      let reg = /^\+?[1-9][0-9]*$/;
      if (!reg.test(value)) {
        return callback(new Error("只能输入正整数"));
      }
      return callback();
    };
    let validaLocation = (rule, value, callback) => {
      if (this.startForm.openClassType === "面授") {
        if (!value) {
          return callback(new Error("请输入开班地点"));
        }
      }
      return callback();
    };
    return {
      dataTable: [],
      listLoading: false,
      page: {
        currentPage: 1,
        pageSize: 100,
        totalRecord: 0,
        sortColumn: "openingID",
        sortType: "desc"
      },
      dialogTitle: "添加开班计划",
      dialogVisible: false,
      isEdit: false,
      isDetail: false,
      statusMap: {
        1: "可报名",
        2: "已满员",
        3: "已结束"
      },
      rules: {
        product: [
          { required: true, message: "请选择产品线", trigger: "change" }
        ],
        series: [
          { required: true, message: "请选择所属系列", trigger: "change" }
        ],
        system: [
          { required: true, message: "请选择所属体系", trigger: "change" }
        ],
        courseType: [
          { required: true, message: "请选择课程类型", trigger: "change" }
        ],
        trainName: [
          { required: true, message: "请选择培训名称", trigger: "change" }
        ],
        classSerices: [
          { required: true, message: "请输入开班系列名称", trigger: "blur" }
        ],
        lecturer: [{ required: true, message: "请输入讲师", trigger: "blur" }],
        trainTime: [
          { required: true, message: "请选择培训时间", trigger: "blur" }
        ],
        openClassType: [
          { required: true, message: "请选择开班形式", trigger: "change" }
        ],
        startLocation: [{ validator: validaLocation, trigger: "blur" }],
        capacity: [{ trigger: "blur", validator: validaNumer, required: true }],
        isExam: [
          { required: true, message: "请选择是否需要考试", trigger: "change" }
        ],
        regType: [
          { required: true, message: "请选择报名方式", trigger: "change" }
        ]
      }
    };
  },

  mounted() {
    let vm = this;
    vm.getStartProduct();
    vm.getStartSeries();
    vm.getStartTrain();
    vm.handleFilter(null, vm.page);
  },
  computed: {
    ...mapState({
      dataSource: state => state.openClassStore.tableDataSource,
      searchForm: state => state.openClassStore.searchForm,
      startForm: state => state.openClassStore.startForm
    })
  },
  watch: {
    dataSource(data) {
      this.dataTable = data.list;
      this.page.totalRecord = data.total;
    }
  },
  methods: {
    ...mapActions([
      "getStartSeries",
      "getStartSystem",
      "getStartProduct",
      "getStartTrain",
      "getStartTableData",
      "setStartInfo"
    ]),
    handleCurrentChange(val) {
      let vm = this;
      vm.page.currentPage = val;
      vm.handleFilter(null, vm.page);
    },
    handleSort(column) {
      if (column.prop) {
        this.page.sortColumn = column.prop;
        this.page.sortType = column.order === "descending" ? "desc" : "asc";
        this.handleFilter(null, this.page);
      }
    },
    handleFilter(arg, page) {
      let vm = this;
      if (!page) {
        vm.page.currentPage = 1;
      }
      vm.listLoading = true;
      vm.getStartTableData(vm.page)
        .then(() => {
          vm.listLoading = false;
        })
        .catch(() => {
          vm.listLoading = false;
        });
    },
    handleCreate() {
      let vm = this;
      vm.getStartProduct();
      vm.getStartSeries();
      vm.getStartSystem();
      vm.dialogTitle = "添加开班计划";
      vm.dialogVisible = true;
      vm.isEdit = false;
      vm.isDetail = false;
      vm.$refs.startForm.resetFields();
    },
    submtForm() {
      let vm = this;
      vm.$refs.startForm.validate(valid => {
        if (valid) {
          let methodName = "addStartInfo";
          if (vm.isEdit) {
            methodName = "editStartInfo";
          }
          vm.$store.dispatch(methodName).then(res => {
            if (res.success) {
              vm.$message.success("操作成功");
              vm.handleFilter(null, vm.page);
            } else {
              vm.$message.error("操作失败");
            }
            vm.dialogVisible = false;
          });
        } else {
          return false;
        }
      });
    },
    clearValidate() {
      if (this.$refs.startForm) {
        this.$refs.startForm.clearValidate();
      }
    },
    handleDel(id) {
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delStartInfo", id).then(res => {
          if (res.success) {
            vm.$message.success("操作成功");
            vm.handleFilter(null, vm.page);
          } else {
            vm.$message.error("操作失败");
          }
        });
      });
    },
    handleEdit(rowData) {
      let vm = this;
      vm.getStartProduct();
      vm.getStartSeries();
      vm.getStartSystem();
      vm.dialogTitle = "编辑开班计划";
      vm.dialogVisible = true;
      vm.isDetail = false;
      vm.isEdit = true;
      rowData.isDetail = vm.isDetail;
      vm.setStartInfo(rowData);
      vm.clearValidate();
    },
    handleDetail(rowData) {
      let vm = this;
      vm.dialogTitle = "查看开班计划";
      vm.dialogVisible = true;
      vm.isDetail = true;
      vm.isEdit = false;
      rowData.isDetail = vm.isDetail;
      vm.setStartInfo(rowData);
      vm.clearValidate();
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss" scope>
.start-setting {
  .search-condition {
    .el-col-3 {
      width: 11.5%;
    }
    .el-col-4 {
      width: 15.7%;
      margin-left: 10px;
    }
    .el-date-editor--datetimerange.el-input__inner {
      width: 280px;
    }
    .el-range-editor.el-input__inner {
      padding: 3px 5px;
    }
    .el-range-separator {
      padding: 0px;
    }
  }
  .el-date-editor.el-input {
    width: 141px;
  }
  .el-input--prefix .el-input__inner {
    padding-left: 15px;
    padding-right: 25px;
  }
  .el-input__suffix {
    right: 2px;
  }
}
</style>
