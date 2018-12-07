<template>
  <div class="app-container affairs-container">
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
        v-model="searchForm.affairsName"
        size="mini"
        clearable
        class="filter-item"
        style="width: 130px"
        placeholder="事务名称"
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
        >{{ $t("table.search") }}</el-button
      >
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        size="mini"
        icon="el-icon-plus"
        @click="handleCreate"
        >{{ $t("table.add") }}</el-button
      >
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
      <el-table-column
        min-width="150px"
        header-align="center"
        label="所属体系"
        sortable
        prop="system"
      ></el-table-column>
      <el-table-column
        min-width="150px"
        header-align="center"
        label="事务名称"
        sortable
        prop="affairName"
      ></el-table-column>

      <el-table-column
        min-width="150px"
        header-align="center"
        label="所属系列"
        sortable
        prop="series"
      ></el-table-column>
      <el-table-column
        width="120px"
        header-align="center"
        label="所属产品线"
        sortable
        prop="du"
      ></el-table-column>
      <el-table-column
        width="110"
        header-align="center"
        label="创建人"
        sortable
        prop="createBy"
      ></el-table-column>
      <el-table-column
        min-width="110"
        header-align="center"
        label="最后更新人"
        sortable
        prop="updateBy"
      ></el-table-column>
      <el-table-column
        min-width="140"
        header-align="center"
        label="最后更新时间"
        sortable
        prop="updateTime"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.updateTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        :label="$t('table.option')"
        width="230"
        header-align="center"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            title="编辑"
            @click="handleEdit(scope.row);"
          ></el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-delete"
            title="删除"
            @click="handleDel(scope.row.affairID);"
          ></el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-setting"
            title="考核事务维度设置"
            @click="handleSet(scope.row.affairID);"
          ></el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-search"
            title="考核事务维度查看"
            @click="handleView(scope.row.affairID);"
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
    <el-dialog
      :title="dialogBaseTitle"
      :visible.sync="dialogBaseVisible"
      width="30%"
    >
      <el-form
        :model="affairsForm"
        size="mini"
        label-width="120px"
        ref="affairsForm"
        :rules="rules"
      >
        <el-form-item label="事务编号" prop="affairsId" v-if="isEdit">
          <el-input
            v-model="affairsForm.affairsId"
            autocomplete="off"
            disabled
          ></el-input>
        </el-form-item>
        <el-form-item label="事务名称" prop="affairsName">
          <el-input
            v-model="affairsForm.affairsName"
            autocomplete="off"
            required
            maxlength="64"
          ></el-input>
        </el-form-item>
        <el-form-item label="所属系列" prop="series">
          <el-select
            v-model="affairsForm.series"
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
        <el-form-item label="所属体系" prop="system">
          <el-select
            v-model="affairsForm.system"
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
        <el-form-item label="所属产品线" prop="product">
          <el-select v-model="affairsForm.product" placeholder="请选择">
            <el-option
              v-for="item in productOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogBaseVisible = false;" size="mini"
          >取 消</el-button
        >
        <el-button type="primary" @click="submtForm();" size="mini"
          >确 定</el-button
        >
      </div>
    </el-dialog>
    <el-dialog
      :title="dialogSetTitle"
      :visible="dialogSetVisible"
      width="50%"
      @close="dialogSetVisible = false;"
    >
      <el-row>
        <el-button
          v-if="!isView"
          type="primary"
          size="mini"
          icon="el-icon-search"
          @click="handleDelDimension"
          >{{ $t("table.delete") }}</el-button
        >
        <el-button
          v-if="!isView"
          style="margin-left: 10px;"
          type="primary"
          size="mini"
          icon="el-icon-plus"
          @click="handleAddDimension"
          >{{ $t("table.add") }}</el-button
        >
      </el-row>
      <el-table
        ref="multipleTable"
        :data="dimensionList"
        border
        fit
        size="mini"
        stripe
        max-height="185"
        tooltip-effect="dark"
        highlight-current-row
        @selection-change="handleSelectionChange"
        style="width: 100%; margin:10px 0px"
      >
        <el-table-column
          type="selection"
          width="55"
          v-if="!isView"
        ></el-table-column>
        <el-table-column
          header-align="center"
          align="center"
          :label="$t('table.id')"
          width="80"
          type="index"
        ></el-table-column>
        <el-table-column
          prop="dimension"
          header-align="center"
          label="考核维度"
          width="150"
        ></el-table-column>
        <el-table-column
          prop="score"
          header-align="center"
          label="分数"
          width="100"
        ></el-table-column>
        <el-table-column
          prop="description"
          header-align="center"
          label="考核点说明"
          show-overflow-tooltip
        ></el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogSetVisible = false;" size="mini" v-if="isView"
          >关 闭</el-button
        >
        <el-button type="primary" @click="handleSave();" size="mini" v-else
          >保 存</el-button
        >
      </div>
    </el-dialog>
    <el-dialog
      :title="dialogDimensionTitle"
      :visible.sync="dialogDimensionVisible"
      width="30%"
    >
      <el-form
        :model="dimensionForm"
        size="mini"
        label-width="120px"
        ref="dimensionForm"
        :rules="rules"
      >
        <el-form-item label="考核维度" prop="dimension">
          <el-input
            v-model="dimensionForm.dimension"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="分数" prop="score">
          <el-input
            v-model="dimensionForm.score"
            autocomplete="off"
            required
            maxlength="3"
          ></el-input>
        </el-form-item>
        <el-form-item label="考核点说明" prop="description">
          <el-input
            type="textarea"
            v-model="dimensionForm.description"
            :autosize="{ minRows: 2, maxRows: 4 }"
            resize="none"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogDimensionVisible = false;" size="mini"
          >取 消</el-button
        >
        <el-button type="primary" @click="handleSaveDimension();" size="mini"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import { formatDate } from "@/utils/date";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }
  },
  data() {
    let vm = this;
    let validaNumer = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入分数"));
      }
      if (isNaN(value)) {
        return callback(new Error("只能输入整数和小数"));
      }
      return callback();
    };
    let validaName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入考核维度"));
      }
      if (vm.dimensionList.find(item => item.dimension === value)) {
        return callback(new Error("考核维度重复"));
      }
      return callback();
    };
    return {
      systemOptions: [],
      courseTypeOptions: [
        { label: "公开课", value: "公开课" },
        { label: "专业课", value: "专业课" }
      ],
      productOptions: [{ label: "2012", value: "2012" }],
      trainTypeOptions: [
        { label: "选修", value: "选修" },
        { label: "必修", value: "必修" }
      ],
      seriesOptions: [],
      searchForm: {
        system: "",
        product: "",
        affairsName: "",
        series: ""
      },
      affairsForm: {
        affairsId: "",
        affairsName: "",
        series: "",
        system: "",
        product: ""
      },
      dimensionForm: {
        affairsId: "",
        dimension: "",
        score: "",
        coefficient: "",
        description: ""
      },
      list: null,
      initList: [],
      listLoading: false,
      dimensionList: [],
      page: {
        pageNum: 1,
        pageSize: 10,
        totalRecord: 0
      },
      dialogBaseTitle: "添加考核事务信息",
      dialogBaseVisible: false,
      dialogSetTitle: "考核事务维度设置",
      dialogSetVisible: false,
      dialogDimensionTitle: "添加考试事务维度",
      dialogDimensionVisible: false,
      isEdit: false,
      isView: false,
      multipleTable: [],
      selectedIds: [],
      rules: {
        affairsName: [
          { required: true, message: "请输入培训名称", trigger: "blur" }
        ],
        series: [
          { required: true, message: "请选择/输入所属系列", trigger: "change" }
        ],
        system: [
          { required: true, message: "请选择/输入所属体系", trigger: "change" }
        ],
        product: [
          { required: true, message: "请选择产品线", trigger: "change" }
        ],
        dimension: [{ required: true, validator: validaName, trigger: "blur" }],
        score: [{ required: true, validator: validaNumer, trigger: "blur" }]
      }
    };
  },
  mounted() {
    this.getProductInfo();
    this.getSeries();
    this.getSystem();
    this.getAffairsList();
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
    getSeries() {
      let vm = this;
      vm.$store.dispatch("queryAffairsSeries").then(res => {
        if (res.data) {
          vm.seriesOptions = res.data.map(item => {
            return {
              label: item.series,
              value: item.series
            };
          });
        } else {
          vm.seriesOptions = [];
        }
      });
    },
    getSystem() {
      let vm = this;
      vm.$store.dispatch("queryAffairsSystem").then(res => {
        if (res.data) {
          vm.systemOptions = res.data.map(item => {
            return {
              label: item.system,
              value: item.system
            };
          });
        } else {
          vm.systemOptions = [];
        }
      });
    },
    getAffairsList() {
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
    getDimensionInfo(affairId) {
      let vm = this;
      vm.$store
        .dispatch("getDimensionInfo", affairId)
        .then(res => {
          if (res.success) {
            vm.dimensionList = res.data.map((item, index) => {
              return {
                id: index + 1,
                dimensionId: item.dimensionID,
                dimension: item.dimensionName,
                score: item.mark,
                description: item.explanation
              };
            });
          } else {
            vm.dimensionList = [];
          }
        })
        .catch(() => {
          vm.dimensionList = [];
        });
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
      vm.list = vm.initList.slice(
        (val - 1) * vm.page.pageSize,
        val * vm.page.pageSize
      );
    },
    handleFilter() {
      this.page.currentPage = 1;
      this.getAffairsList();
    },
    handleCreate() {
      this.isEdit = false;
      this.affairsForm.affairsId = "";
      this.affairsForm.affairsName = "";
      this.affairsForm.series = "";
      this.affairsForm.system = "";
      this.affairsForm.product = "";
      this.dialogBaseTitle = "添加考核事务信息";
      this.dialogBaseVisible = true;
      this.clearValidate("affairsForm");
      this.getProductInfo();
      this.getSeries();
      this.getSystem();
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
            vm.getAffairsList();
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
      vm.dialogSetTitle = "查看考核事务维度";
      vm.dialogSetVisible = true;
      vm.isView = true;
      vm.getDimensionInfo(affairId);
    },
    handleSet(affairId) {
      let vm = this;
      vm.dimensionForm.affairsId = affairId;
      vm.dialogSetTitle = "考核事务维度设置";
      vm.dialogSetVisible = true;
      vm.isView = false;
      vm.multipleTable = [];
      vm.getDimensionInfo(affairId);
    },
    handleSave() {
      let vm = this;
      if (vm.dimensionList.length <= 0) {
        vm.$message.warning("请添加考核维度");
        return;
      }
      let param = {
        examinationAffair: { affairID: vm.dimensionForm.affairsId },
        examinationDimensionList: vm.dimensionList.map(item => {
          return {
            dimensionName: item.dimension,
            mark: item.score,
            explanation: item.description
          };
        })
      };
      vm.$store
        .dispatch("addDimensionInfo", param)
        .then(res => {
          if (res.success) {
            vm.$message.success(res.message);
          } else {
            vm.$message.error(res.message);
          }
          vm.dialogSetVisible = false;
        })
        .catch(() => {
          vm.dialogSetVisible = false;
        });
    },
    handleDelDimension() {
      let vm = this;
      if (vm.selectedIds.length <= 0) {
        vm.$message.warning("请选择要删除的数据");
        return;
      }
      vm.dimensionList = vm.dimensionList.filter(item => {
        return vm.selectedIds.indexOf(item.id) < 0;
      });
    },
    handleAddDimension() {
      let vm = this;
      vm.dialogDimensionVisible = true;
      vm.dimensionForm.dimension = "";
      vm.dimensionForm.score = "";
      vm.dimensionForm.coefficient = "";
      vm.dimensionForm.description = "";
      vm.clearValidate("dimensionForm");
    },
    handleSaveDimension() {
      let vm = this;
      vm.$refs.dimensionForm.validate(valid => {
        if (valid) {
          vm.dimensionList.push({
            id: vm.dimensionList.length + 1,
            dimension: vm.dimensionForm.dimension,
            score: vm.dimensionForm.score,
            description: vm.dimensionForm.description
          });
          vm.dialogDimensionVisible = false;
        } else {
          return false;
        }
      });
    },
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.id);
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
