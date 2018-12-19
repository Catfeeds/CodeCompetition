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
    <el-form :model="affairsForm" size="mini" ref="affairsForm" :rules="rules">
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
          width="60"
          type="index"
        ></el-table-column>
        <el-table-column
          v-if="!isAdd"
          key="1"
          width="135px"
          header-align="center"
          label="事务编号"
          :sortable="!isAdd"
          prop="affairID"
        ></el-table-column>
        <el-table-column
          min-width="120px"
          header-align="center"
          label="事务名称"
          :sortable="!isAdd"
          prop="affairName"
          show-overflow-tooltip
        >
          <template slot-scope="scope">
            <el-form-item label prop="affairsName" v-if="scope.row.isAdd">
              <el-input
                v-model="affairsForm.affairsName"
                autocomplete="off"
                required
                maxlength="64"
                style="width:130px"
              ></el-input>
            </el-form-item>
            <el-popover v-else placement="right-end" title="考核维度" width="600" trigger="hover">
              <el-table
                :data="scope.row.examinationDimensionList"
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
                <el-table-column prop="dimensionName" header-align="center" label="考核维度" width="150"></el-table-column>
                <el-table-column prop="mark" header-align="center" label="分数" width="100"></el-table-column>
                <el-table-column
                  prop="explanation"
                  header-align="center"
                  label="考核点说明"
                  show-overflow-tooltip
                ></el-table-column>
              </el-table>
              <el-button type="text" slot="reference">{{scope.row.affairName}}</el-button>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          min-width="110px"
          header-align="center"
          label="所属体系"
          :sortable="!isAdd"
          prop="system"
        >
          <template slot-scope="scope">
            <el-form-item label prop="system" v-if="scope.row.isAdd">
              <el-select
                v-model="affairsForm.system"
                filterable
                allow-create
                default-first-option
                placeholder="请选择"
                style="width:150px"
              >
                <el-option
                  v-for="item in systemOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <span v-else>{{scope.row.system}}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="110px"
          header-align="center"
          label="所属系列"
          :sortable="!isAdd"
          prop="series"
        >
          <template slot-scope="scope">
            <el-form-item label prop="series" v-if="scope.row.isAdd">
              <el-select
                v-model="affairsForm.series"
                filterable
                allow-create
                default-first-option
                placeholder="请选择"
                style="width:150px"
              >
                <el-option
                  v-for="item in seriesOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <span v-else>{{scope.row.series}}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="110px"
          header-align="center"
          label="所属产品线"
          :sortable="!isAdd"
          prop="bu"
        >
          <template slot-scope="scope">
            <el-form-item label prop="product" v-if="scope.row.isAdd">
              <el-select v-model="affairsForm.product" placeholder="请选择" style="width:150px">
                <el-option
                  v-for="item in productOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <span v-else>{{scope.row.bu}}</span>
          </template>
        </el-table-column>
        <el-table-column
          v-if="!isAdd"
          key="6"
          width="110"
          header-align="center"
          label="创建人"
          :sortable="!isAdd"
          prop="createBy"
        ></el-table-column>
        <el-table-column
          v-if="!isAdd"
          key="7"
          min-width="110"
          header-align="center"
          label="最后更新人"
          :sortable="!isAdd"
          prop="updateBy"
        ></el-table-column>
        <el-table-column
          v-if="!isAdd"
          key="8"
          min-width="140"
          header-align="center"
          label="最后更新时间"
          :sortable="!isAdd"
          prop="updateTime"
        >
          <template slot-scope="scope">
            <span v-if="!scope.row.isAdd">{{ scope.row.updateTime | formatDate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          :label="$t('table.option')"
          width="110"
          header-align="center"
        >
          <template slot-scope="scope">
            <el-button
              v-if="!scope.row.isAdd"
              type="text"
              size="mini"
              icon="el-icon-edit"
              title="编辑"
              @click="handleEdit(scope.row);"
            ></el-button>
            <el-button
              v-if="!scope.row.isAdd"
              type="text"
              size="mini"
              icon="el-icon-delete"
              title="删除"
              @click="handleDel(scope.row.affairID);"
            ></el-button>
            <el-button
              v-if="!scope.row.isAdd"
              type="text"
              size="mini"
              icon="el-icon-setting"
              title="考核事务维度设置"
              @click="handleSet(scope.row.affairID);"
            ></el-button>
            <el-button
              v-if="scope.row.isAdd"
              type="text"
              size="mini"
              icon="el-icon-circle-plus-outline"
              title="保存"
              @click="submtForm(scope.row);"
            ></el-button>
            <el-button
              v-if="scope.row.isAdd"
              type="text"
              size="mini"
              icon="el-icon-remove-outline"
              title="取消"
              @click="handleFilter(arg, page, true);"
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
        :disabled="isAdd"
        layout="total, slot, prev, pager, next"
        :total="page.totalRecord"
        prev-text="上一页"
        next-text="下一页"
      ></el-pagination>
    </el-row>
    <el-dialog
      :title="dialogSetTitle"
      :visible="dialogSetVisible"
      width="50%"
      @close="dialogSetVisible = false;"
    >
      <el-row>
        <el-button
          v-if="!isView&&!isDimensionAdd"
          type="primary"
          size="mini"
          icon="el-icon-delete"
          @click="handleDelDimension"
        >{{ $t("table.delete") }}</el-button>
        <el-button
          v-if="!isView&&!isDimensionAdd"
          style="margin-left: 10px;"
          type="primary"
          size="mini"
          icon="el-icon-plus"
          @click="handleAddDimension"
        >{{ $t("table.add") }}</el-button>
        <el-button
          v-if="!isView&&isDimensionAdd"
          type="primary"
          size="mini"
          icon="el-icon-circle-plus-outline"
          @click="handleSaveDimension"
        >{{ $t("table.save") }}</el-button>
        <el-button
          v-if="!isView&&isDimensionAdd"
          style="margin-left: 10px;"
          type="primary"
          size="mini"
          icon="el-icon-remove-outline"
          @click="getDimensionInfo(dimensionForm.affairsId)"
        >{{ $t("table.cancel") }}</el-button>
      </el-row>
      <el-form :model="dimensionForm" size="mini" ref="dimensionForm" :rules="rules">
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
          <el-table-column type="selection" width="55" v-if="!isView"></el-table-column>
          <el-table-column
            header-align="center"
            align="center"
            :label="$t('table.id')"
            width="80"
            type="index"
          ></el-table-column>
          <el-table-column prop="dimension" header-align="center" label="考核维度" width="150">
            <template slot-scope="scope">
              <el-form-item label prop="dimension" v-if="scope.row.isAdd">
                <el-input v-model="dimensionForm.dimension" autocomplete="off"></el-input>
              </el-form-item>
              <span v-else>{{scope.row.dimension}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="score" header-align="center" label="分数" width="100">
            <template slot-scope="scope">
              <el-form-item label prop="score" v-if="scope.row.isAdd">
                <el-input v-model="dimensionForm.score" autocomplete="off" maxlength="3"></el-input>
              </el-form-item>
              <span v-else>{{scope.row.score}}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="description"
            header-align="center"
            label="考核点说明"
            show-overflow-tooltip
          >
            <template slot-scope="scope">
              <el-form-item label prop="description" v-if="scope.row.isAdd">
                <el-input v-model="dimensionForm.description" autocomplete="off"></el-input>
              </el-form-item>
              <span v-else>{{scope.row.description}}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogSetVisible = false;" size="mini" v-if="isView">关 闭</el-button>
        <el-button type="primary" @click="handleSave();" size="mini" v-else>确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { formatDate } from "@/utils/date";
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
      productOptions: [],
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
        currentPage: 1,
        pageSize: 10,
        totalRecord: 0
      },
      dialogSetTitle: "考核事务维度设置",
      dialogSetVisible: false,
      isDimensionAdd: false,
      isAdd: false,
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
        if (res.data) {
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
    getAffairsList() {
      let vm = this;
      let condition = {
        bu: vm.searchForm.product,
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
      vm.isDimensionAdd = false;
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
    submtForm(row) {
      let vm = this;
      vm.$refs.affairsForm.validate(valid => {
        if (valid) {
          let formData = {
            bu: vm.affairsForm.product,
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
          vm.isAdd = false;
          vm.listLoading = true;
          vm.$store.dispatch(methodName, formData).then(res => {
            if (res.success) {
              vm.$message.success(res.message);
              vm.getAffairsList();
              vm.getSeries();
              vm.getSystem();
            } else {
              vm.$message.error(res.message);
            }
            vm.listLoading = false;
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
    handleFilter(arg, page, isCancel) {
      if (!isCancel && this.validTableStatus()) {
        return;
      }
      this.isAdd = isCancel ? false : !!isCancel;
      if (!page) {
        this.page.currentPage = 1;
      }
      this.getAffairsList();
    },
    handleCreate() {
      if (this.validTableStatus()) {
        return;
      }
      this.isAdd = true;
      this.isEdit = false;
      this.affairsForm.affairsId = "";
      this.affairsForm.affairsName = "";
      this.affairsForm.series = "";
      this.affairsForm.system = "";
      this.affairsForm.product = "";
      this.getProductInfo();
      this.getSeries();
      this.getSystem();
      this.list.unshift({ isAdd: true });
    },
    handleDel(id) {
      let vm = this;
      if (vm.validTableStatus()) {
        return;
      }
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
      if (this.validTableStatus()) {
        return;
      }
      this.getProductInfo();
      this.getSeries();
      this.getSystem();
      this.affairsForm.affairsId = rowData.affairID;
      this.affairsForm.affairsName = rowData.affairName;
      this.affairsForm.series = rowData.series;
      this.affairsForm.system = rowData.system;
      this.affairsForm.product = rowData.bu;
      this.isAdd = true;
      this.isEdit = true;
      rowData.isAdd = true;
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
      if (vm.dimensionList.find(item => item.isAdd)) {
        vm.$message.warning("表格存在正在编辑的数据，请先保存");
        return;
      }
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
      vm.dimensionForm.dimension = "";
      vm.dimensionForm.score = "";
      vm.dimensionForm.coefficient = "";
      vm.dimensionForm.description = "";
      vm.isDimensionAdd = true;
      vm.dimensionList.unshift({ isAdd: true });
    },
    handleSaveDimension() {
      let vm = this;
      vm.$refs.dimensionForm.validate(valid => {
        if (valid) {
          vm.isDimensionAdd = false;
          vm.dimensionList.push({
            id: vm.dimensionList.length + 1,
            dimension: vm.dimensionForm.dimension,
            score: vm.dimensionForm.score,
            description: vm.dimensionForm.description
          });
          vm.dimensionList = vm.dimensionList.filter(item => !item.isAdd);
        } else {
          return false;
        }
      });
    },
    handleSelectionChange(selection) {
      this.selectedIds = selection.map(item => item.id);
    },
    validTableStatus() {
      if (this.list.find(item => item.isAdd)) {
        this.$message.warning("表格存在正在编辑的数据，请先保存");
        return true;
      }
      return false;
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
