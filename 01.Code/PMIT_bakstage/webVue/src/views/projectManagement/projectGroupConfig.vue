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
      <el-form :model="newForm" ref="newForm" :rules="rules">
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
            :label="$t('table.id')"
          ></el-table-column>

          <el-table-column
            min-width="120px"
            header-align="center"
            :label="$t('projectGroup.product')"
            :sortable="!isAdd"
            prop="bu"
          >
            <template slot-scope="scope">
              <el-form-item prop="product" v-if="scope.row.isAdd">
                <el-select
                  style="width:150px"
                  v-model="newForm.product"
                  size="mini"
                  placeholder="产品线"
                  @change="newProductChange"
                >
                  <el-option
                    v-for="item in searchForm.productOptions"
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
            min-width="120px"
            header-align="center"
            prop="du"
            label="DU"
            :sortable="!isAdd"
          >
            <template slot-scope="scope">
              <el-form-item prop="du" v-if="scope.row.isAdd">
                <el-select
                  style="width:150px"
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
              <span v-else>{{scope.row.du}}</span>
            </template>
          </el-table-column>

          <el-table-column
            min-width="150px"
            header-align="center"
            prop="pdu"
            label="PDU"
            :sortable="!isAdd"
          >
            <template slot-scope="scope">
              <el-form-item prop="pdu" v-if="scope.row.isAdd">
                <el-select
                  style="width:150px"
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
              <span v-else>{{scope.row.pdu}}</span>
            </template>
          </el-table-column>

          <el-table-column
            :label="$t('projectGroup.teamName')"
            header-align="center"
            min-width="150"
            :sortable="!isAdd"
            prop="projectName"
          >
            <template slot-scope="scope">
              <el-form-item prop="teamName" v-if="scope.row.isAdd">
                <el-input
                  style="width:150px"
                  v-model="newForm.teamName"
                  size="mini"
                  placeholder="项目组名称"
                ></el-input>
              </el-form-item>
              <span v-else>{{scope.row.projectName}}</span>
            </template>
          </el-table-column>

          <el-table-column
            label="PM"
            header-align="center"
            min-width="110"
            :sortable="!isAdd"
            prop="pmName"
          >
            <template slot-scope="scope">
              <el-form-item v-if="scope.row.isAdd" prop="employeeId">
                <el-select
                  style="width:130px"
                  v-model="newForm.employeeId"
                  size="mini"
                  placeholder="PM"
                >
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
              <span v-else>{{scope.row.pmName}}</span>
            </template>
          </el-table-column>

          <el-table-column
            align="center"
            :label="$t('table.option')"
            width="80"
            header-align="center"
          >
            <template slot-scope="scope">
              <el-button
                v-if="!scope.row.isAdd"
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
                v-if="!scope.row.isAdd"
                type="text"
                size="mini"
                icon="el-icon-delete"
                title="删除"
                @click="projectDelete(scope.row.projectID)"
              ></el-button>
              <el-button
                v-if="scope.row.isAdd"
                type="text"
                size="mini"
                icon="el-icon-circle-plus-outline"
                title="保存"
                @click="onSave(scope.row);"
              ></el-button>
              <el-button
                v-if="scope.row.isAdd"
                type="text"
                size="mini"
                icon="el-icon-remove-outline"
                title="取消"
                @click="onSearchForm(null, page, true);"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
    </div>
    <el-row type="flex" justify="end">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        layout="total, slot, prev, pager, next"
        :total="totalRecord"
        :disabled="isAdd"
        prev-text="上一页"
        next-text="下一页"
      ></el-pagination>
    </el-row>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  data() {
    return {
      listLoading: false,
      pageSize: 10,
      dataTable: [],
      totalRecord: 0,
      currentPage: 1,
      activeTab: "PO",
      projectID: "",
      isAdd: false,
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
      this.dataTable = data.datas || [];
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
    onSearchForm(arg, curPage, isCancel) {
      if (!isCancel && this.validTableStatus()) {
        return;
      }
      let vm = this;
      vm.isAdd = isCancel ? false : !!isCancel;
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
      if (this.validTableStatus()) {
        return;
      }
      this.isAdd = true;
      this.newForm.product = "";
      this.newForm.du = "";
      this.newForm.pdu = "";
      this.newForm.teamName = "";
      this.newForm.employeeName = "";
      this.newForm.employeeId = "";
      this.dataTable.unshift({ isAdd: true });
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
      if (this.validTableStatus()) {
        return;
      }
      this.$router.push({ path: "teamSettings/" + projectId });
    },
    projectEdit(row) {
      this.newForm.product = row.product;
      this.newForm.du = row.du;
      this.newForm.pdu = row.pdu;
      this.newForm = row.teamName;
      this.newForm = row.pm;
    },
    projectDelete(id) {
      if (this.validTableStatus()) {
        return;
      }
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
              item => item.path.indexOf("/teamSettings/" + id) >= 0
            );
            vm.$store.dispatch("delView", view);
          } else {
            vm.$message.error("项目删除失败");
          }
        });
      });
    },
    onSave(row) {
      let vm = this;
      vm.$refs.newForm.validate(valid => {
        if (valid) {
          row.isAdd = false;
          vm.isAdd = false;
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
          });
        } else {
          return false;
        }
      });
    },
    validTableStatus() {
      if (this.dataTable.find(item => item.isAdd)) {
        this.$message.warning("表格存在正在编辑的数据，请先保存");
        return true;
      }
      return false;
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
