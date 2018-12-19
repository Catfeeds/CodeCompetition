<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select
        size="mini"
        v-model="searchForm.system"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="所属体系"
      >
        <el-option
          v-for="item in systemOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
      <el-select
        v-model="searchForm.courseType"
        clearable
        size="mini"
        placeholder="课程类型"
        class="filter-item"
        style="width: 130px"
      >
        <el-option v-for="item in courseTypeOptions" :key="item" :label="item" :value="item"></el-option>
      </el-select>
      <el-select
        v-model="searchForm.product"
        clearable
        size="mini"
        class="filter-item"
        style="width: 130px"
        placeholder="所属产品线"
      >
        <el-option
          v-for="item in productOptions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
      <el-input
        v-model="searchForm.trainName"
        size="mini"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="培训名称"
      ></el-input>
      <el-input
        v-model="searchForm.series"
        size="mini"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="所属系列"
      ></el-input>
      <el-button
        class="filter-item"
        type="primary"
        size="mini"
        icon="el-icon-search"
        @click="handleFilter"
      >{{ $t("table.search") }}</el-button>
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        size="mini"
        icon="el-icon-plus"
        @click="handleCreate"
      >{{ $t("table.add") }}</el-button>
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
        width="50"
        type="index"
      ></el-table-column>
      <el-table-column width="140px" header-align="center" label="培训编号" sortable prop="trainingId"></el-table-column>
      <el-table-column
        min-width="120px"
        header-align="center"
        label="培训名称"
        sortable
        prop="trainingName"
        show-overflow-tooltip
      >
        <template slot-scope="scope">
          <el-popover placement="right-end" title="培训详情" width="480" trigger="hover">
            <el-form size="mini" label-width="120px">
              <el-row>
                <el-col>
                  <el-form-item label="培训编号">
                    <el-input v-model="scope.row.trainingId" readonly></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col>
                  <el-form-item label="培训名称">
                    <el-input v-model="scope.row.trainingName" readonly></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="所属系列">
                    <el-input v-model="scope.row.series" readonly></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="所属体系">
                    <el-input v-model="scope.row.sort" readonly></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="课程类型">
                    <el-input v-model="scope.row.classType" readonly></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="培训类型">
                    <el-input v-model="scope.row.type" readonly></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="培训时长(小时)">
                    <el-input v-model="scope.row.trainingDuration" readonly></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="所属产品线">
                    <el-input v-model="scope.row.bu" readonly></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col>
                  <el-form-item label="培训简介">
                    <el-input
                      readonly
                      type="textarea"
                      v-model="scope.row.trainingDes"
                      :autosize="{ minRows: 2, maxRows: 4 }"
                      resize="none"
                    ></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
            <el-button type="text" slot="reference">{{scope.row.trainingName}}</el-button>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column min-width="100px" header-align="center" label="所属系列" sortable prop="series"></el-table-column>

      <el-table-column min-width="100px" header-align="center" label="所属体系" sortable prop="sort"></el-table-column>

      <el-table-column min-width="100" header-align="center" label="课程类型" sortable prop="type"></el-table-column>

      <el-table-column min-width="110" header-align="center" label="所属产品线" sortable prop="bu"></el-table-column>
      <el-table-column width="90" header-align="center" label="创建人" sortable prop="creatorName"></el-table-column>
      <el-table-column
        min-width="110"
        header-align="center"
        label="最后更新人"
        sortable
        prop="updateName"
      ></el-table-column>
      <el-table-column
        min-width="140"
        header-align="center"
        label="最后更新时间"
        sortable
        prop="createTime"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.option')" width="80" header-align="center">
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
            @click="handleDel(scope.row.trainingId);"
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
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="45%">
      <el-form :model="trainForm" size="mini" label-width="120px" ref="trainForm" :rules="rules">
        <el-row v-if="isEdit">
          <el-col>
            <el-form-item label="培训编号" prop="trainId">
              <el-input v-model="trainForm.trainId" autocomplete="off" readonly></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="培训名称" prop="trainName">
              <el-input v-model="trainForm.trainName" autocomplete="off" required maxlength="64"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属系列" prop="series">
              <el-select
                v-model="trainForm.series"
                filterable
                allow-create
                default-first-option
                placeholder="请选择"
              >
                <el-option
                  v-for="item in seriesOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属体系" prop="system">
              <el-select
                v-model="trainForm.system"
                filterable
                allow-create
                default-first-option
                placeholder="请选择"
              >
                <el-option
                  v-for="item in systemOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="课程类型" prop="courseType">
              <el-select v-model="trainForm.courseType" placeholder="请选择">
                <el-option
                  v-for="item in courseTypeOptions"
                  :key="item"
                  :label="item"
                  :value="item"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="培训类型" prop="trainType">
              <el-select v-model="trainForm.trainType" placeholder="请选择">
                <el-option v-for="item in trainTypeOptions" :key="item" :label="item" :value="item"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="培训时长(小时)" prop="trainTime">
              <el-input v-model="trainForm.trainTime" autocomplete="off" maxlength="4"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属产品线" prop="product">
              <el-select v-model="trainForm.product" placeholder="请选择">
                <el-option
                  v-for="item in productOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="培训简介" prop="description">
          <el-input
            type="textarea"
            v-model="trainForm.description"
            :autosize="{ minRows: 2, maxRows: 4 }"
            resize="none"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="submtForm('trainForm');" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { formatDate } from "@/utils/date";
import constant from "@/utils/constant";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }
  },
  computed: {
    ...mapGetters(["employeeId", "employeeName"])
  },
  data() {
    let validaNumer = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入培训时长"));
      }
      if (isNaN(value)) {
        return callback(new Error("只能输入整数和小数"));
      }
      return callback();
    };
    return {
      systemOptions: [],
      courseTypeOptions: constant.COURSE_TYPE,
      productOptions: [],
      trainTypeOptions: constant.TRAIN_TYPE,
      seriesOptions: [],
      searchForm: {
        system: "",
        courseType: "",
        product: "",
        trainName: "",
        series: ""
      },
      trainForm: {
        trainId: "",
        trainName: "",
        description: "",
        series: "",
        system: "",
        courseType: "",
        trainType: "",
        trainTime: "",
        product: ""
      },
      list: null,
      initList: [],
      listLoading: false,
      page: {
        pageNum: 1,
        pageSize: 10,
        totalRecord: 0
      },
      dialogTitle: "添加培训信息",
      dialogVisible: false,
      isEdit: false,
      rules: {
        trainName: [
          { required: true, message: "请输入培训名称", trigger: "blur" }
        ],
        description: [
          { required: true, message: "请输入培训简介", trigger: "blur" }
        ],
        series: [
          { required: true, message: "请选择/输入所属系列", trigger: "change" }
        ],
        system: [
          { required: true, message: "请选择/输入所属体系", trigger: "change" }
        ],
        courseType: [
          { required: true, message: "请选择课程类型", trigger: "change" }
        ],
        trainType: [
          { required: true, message: "请选择培训类型", trigger: "change" }
        ],
        trainTime: [
          { trigger: "blur", validator: validaNumer, required: true }
        ],
        product: [
          { required: true, message: "请选择产品线", trigger: "change" }
        ]
      }
    };
  },
  mounted() {
    this.getProductInfo();
    this.getSeries();
    this.getSystem();
    this.getTrainingList();
  },
  methods: {
    handleCurrentChange(val) {
      let vm = this;
      vm.page.currentPage = val;
      vm.list = vm.initList.slice(
        (val - 1) * vm.page.pageSize,
        val * vm.page.pageSize
      );
    },
    handleFilter() {
      this.page.currentPage = 1;
      this.getTrainingList();
    },
    getTrainingList() {
      let vm = this;
      let condition = {
        bu: vm.searchForm.product,
        series: vm.searchForm.series,
        sort: vm.searchForm.system,
        trainingName: vm.searchForm.trainName,
        classType: vm.searchForm.courseType
      };
      vm.listLoading = true;
      vm.$store
        .dispatch("getTrainingList", condition)
        .then(res => {
          if (res.success) {
            vm.initList = res.data;
            vm.list = vm.initList.slice(0, vm.page.pageSize);
            vm.page.totalRecord = res.data.length;
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
    handleCreate() {
      this.isEdit = false;
      this.trainForm.trainId = "";
      this.trainForm.trainName = "";
      this.trainForm.description = "";
      this.trainForm.series = "";
      this.trainForm.system = "";
      this.trainForm.courseType = "";
      this.trainForm.trainType = "";
      this.trainForm.trainTime = "";
      this.trainForm.product = "";
      this.dialogTitle = "添加培训信息";
      this.dialogVisible = true;
      this.clearValidate();
      this.getProductInfo();
      this.getSeries();
      this.getSystem();
    },
    submtForm() {
      let vm = this;
      vm.$refs.trainForm.validate(valid => {
        if (valid) {
          let formData = {
            bu: vm.trainForm.product,
            series: vm.trainForm.series,
            sort: vm.trainForm.system,
            trainingName: vm.trainForm.trainName,
            trainingDes: vm.trainForm.description,
            trainingDuration: vm.trainForm.trainTime,
            type: vm.trainForm.trainType,
            classType: vm.trainForm.courseType
          };
          if (vm.isEdit) {
            formData.trainingId = vm.trainForm.trainId;
            formData.updaterId = vm.employeeId;
          } else {
            formData.creatorId = vm.employeeId;
          }
          vm.$store.dispatch("saveTrainingInfo", formData).then(res => {
            if (res.success) {
              vm.$message.success("操作成功");
              vm.dialogVisible = false;
              vm.getTrainingList();
              vm.getSeries();
              vm.getSystem();
            } else if (res.message === "DuplicateName") {
              vm.$message.error("名称已存在");
            } else {
              vm.$message.error("操作失败");
            }
          });
        } else {
          return false;
        }
      });
    },
    clearValidate() {
      if (this.$refs.trainForm) {
        this.$refs.trainForm.clearValidate();
      }
    },
    getProductInfo() {
      let vm = this;
      vm.$store.dispatch("getProductInfo").then(data => {
        if (data) {
          vm.productOptions = data;
        } else {
          vm.productOptions = [];
        }
      });
    },
    getSeries() {
      let vm = this;
      vm.$store.dispatch("querySeries").then(res => {
        if (res.data) {
          vm.seriesOptions = res.data.map(item => {
            return {
              label: item,
              value: item
            };
          });
        } else {
          vm.seriesOptions = [];
        }
      });
    },
    getSystem() {
      let vm = this;
      vm.$store.dispatch("querySystem").then(res => {
        if (res.success) {
          vm.systemOptions = res.data.map(item => {
            return {
              label: item,
              value: item
            };
          });
        } else {
          vm.systemOptions = [];
        }
      });
    },
    handleDel(id) {
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delTraining", id).then(res => {
          if (res.success) {
            vm.$message.success("操作成功");
            vm.getTrainingList();
          } else {
            vm.$message.error("操作失败");
          }
        });
      });
    },
    handleEdit(rowData) {
      this.isEdit = true;
      this.getProductInfo();
      this.getSeries();
      this.getSystem();
      this.trainForm.trainId = rowData.trainingId;
      this.trainForm.trainName = rowData.trainingName;
      this.trainForm.description = rowData.trainingDes;
      this.trainForm.series = rowData.series;
      this.trainForm.system = rowData.sort;
      this.trainForm.courseType = rowData.classType;
      this.trainForm.trainType = rowData.type;
      this.trainForm.trainTime = rowData.trainingDuration;
      this.trainForm.product = rowData.bu;
      this.dialogTitle = "修改培训信息";
      this.dialogVisible = true;
      this.clearValidate();
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss"></style>
