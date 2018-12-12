<template>
  <div>
    <el-form :inline="true">
      <el-row>
        <el-col :span="4">
          <el-form-item>
            <el-select
              v-model="searchForm.poId"
              filterable
              size="mini"
              placeholder="PO"
              @change="changePO"
            >
              <el-option
                v-for="item in searchForm.poOptions"
                :key="item.projectId"
                :label="item.projectName"
                :value="item.projectId"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item>
            <el-button
              type="primary"
              size="mini"
              @click="onCreate"
              icon="el-icon-plus"
            >{{ $t("table.add") }}</el-button>
            <el-button type="primary" @click="onExtendPrePeriod" size="mini">继承上一期</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-table
      :data="tableData"
      border
      fix
      size="mini"
      stripe
      :span-method="arraySpanMethod"
      style="width: 100%"
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
      <el-table-column prop="poRoleName" header-align="center" label="关键角色"></el-table-column>
      <el-table-column prop="amount" header-align="center" label="数量"></el-table-column>
      <el-table-column prop="staffIdAndName" header-align="center" label="角色员工"></el-table-column>
      <el-table-column prop="backupStaffNameAndId" header-align="center" label="备份员工"></el-table-column>
      <el-table-column
        prop="option"
        header-align="center"
        align="center"
        :label="$t('table.option')"
        width="80"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            type="text"
            icon="el-icon-edit"
            title="编辑"
          ></el-button>
          <el-button
            size="mini"
            @click="handleDelete(scope.$index, scope.row)"
            type="text"
            icon="el-icon-delete"
            title="删除"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogVisible"
      width="70%"
      custom-class="key-role-setting"
    >
      <el-row :gutter="12">
        <el-col :span="10">
          <el-card shadow="never">
            <div slot="header" class="clearfix">
              <span>关键角色设置</span>
              <el-button
                v-if="isAddRow"
                type="text"
                size="mini"
                @click="handleAddRoleNum"
                icon="el-icon-plus"
                style="float: right; padding: 0px;"
              >{{ $t("table.add") }}</el-button>
              <el-button
                v-if="!isAddRow"
                type="text"
                size="mini"
                @click="handleSaveRoleNum"
                icon="el-icon-circle-plus-outline"
                style="float: right; padding: 0px;"
              >{{ $t("table.save") }}</el-button>
              <el-button
                v-if="!isAddRow"
                type="text"
                size="mini"
                @click="handleCancelRoleNum"
                icon="el-icon-remove-outline"
                style="float: right; padding: 0px; margin-right:10px;"
              >{{ $t("table.cancel") }}</el-button>
            </div>
            <el-table
              ref="trainTable"
              :data="roleDataSource"
              border
              fit
              size="mini"
              stripe
              height="215"
              tooltip-effect="dark"
              style="width: 100%;"
              cell-class-name="role-number"
            >
              <el-table-column
                type="index"
                sortable
                header-align="center"
                align="center"
                width="50"
                :label="$t('table.id')"
              ></el-table-column>
              <el-table-column header-align="center" label="关键角色" min-width="100">
                <template slot-scope="scope">
                  <el-select
                    v-if="scope.row.isAdd"
                    v-model="scope.row.roleId"
                    size="mini"
                    placeholder="角色"
                  >
                    <el-option
                      v-for="item in searchForm.keyRoleOptions"
                      :key="item.poRoleId"
                      :label="item.poRoleName"
                      :value="item.poRoleId"
                    ></el-option>
                  </el-select>
                  <span v-else>{{scope.row.roleName}}</span>
                </template>
              </el-table-column>
              <el-table-column header-align="center" label="数量" width="100">
                <template slot-scope="scope">
                  <el-input-number
                    v-if="scope.row.isAdd"
                    style="width:80px"
                    v-model="scope.row.roleNum"
                    size="mini"
                    controls-position="right"
                    :min="1"
                    :max="100"
                    :step="1"
                  ></el-input-number>
                  <span v-else>{{scope.row.roleNum}}</span>
                </template>
              </el-table-column>
              <el-table-column
                header-align="center"
                width="80"
                align="center"
                :label="$t('table.option')"
              >
                <template slot-scope="scope">
                  <el-button
                    type="text"
                    v-if="!scope.row.isAdd"
                    size="mini"
                    title="编辑"
                    icon="el-icon-edit"
                    @click="handleRoleRowEdit(scope.row)"
                  ></el-button>
                  <el-button
                    size="mini"
                    type="text"
                    title="删除"
                    icon="el-icon-delete"
                    @click="handleRoleRowDelete(scope.$index, scope.row)"
                  ></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>
        <el-col :span="14">
          <el-card shadow="never">
            <div slot="header" class="clearfix">关键角色关联员工</div>
            <el-table
              ref="affairTable"
              :data="empDataSource"
              border
              fit
              size="mini"
              stripe
              height="215"
              tooltip-effect="dark"
              cell-class-name="role-number"
              style="width: 100%;"
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
              <el-table-column prop="roleName" header-align="center" label="关键角色" width="100"></el-table-column>
              <el-table-column header-align="center" label="角色员工" min-width="100">
                <template slot-scope="scope">
                  <el-select v-model="scope.row.staffId" filterable size="mini" placeholder="请选择">
                    <el-option
                      v-for="item in searchForm.teamStaffOptions"
                      :key="item.staffId"
                      :label="item.staffName"
                      :value="item.staffId"
                    ></el-option>
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column header-align="center" label="备份员工" min-width="110">
                <template slot-scope="scope">
                  <el-select
                    v-model="scope.row.backupStaffId"
                    filterable
                    size="mini"
                    placeholder="请选择"
                  >
                    <el-option
                      v-for="item in searchForm.teamStaffOptions"
                      :key="item.staffId"
                      :label="item.staffName"
                      :value="item.staffId"
                    ></el-option>
                  </el-select>
                </template>
              </el-table-column>
              <el-table-column
                header-align="center"
                width="80"
                align="center"
                :label="$t('table.option')"
              >
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="text"
                    title="删除"
                    icon="el-icon-delete"
                    @click="handleEmpRowDelete(scope.$index, scope.row)"
                  ></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="handleConfirm();" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "keyRole",
  props: ["teamInfo"],
  data() {
    return {
      isAddRow: true,
      isEditRow: false,
      loading: false,
      tableData: [],
      dialogTitle: "",
      dialogVisible: false,
      roleDataSource: [],
      empDataSource: [],
      editRowData: null
    };
  },
  computed: {
    ...mapState({
      searchForm: state => state.keyRoleStore.searchForm,
      keyRoleDataSource: state =>
        state.keyRoleStore.searchForm.keyRoleDataSource
    })
  },
  watch: {
    keyRoleDataSource(data) {
      let hash = {};
      this.tableData = data.map(item => {
        if (!hash[item.poRoleId]) {
          hash[item.poRoleId] = 1;
          item.amount = data.filter(x => x.poRoleId === item.poRoleId).length;
        } else {
          item.amount = 0;
        }
        if (item.staffName && item.staffId) {
          item.staffIdAndName = item.staffName + "(" + item.staffId + ")";
        } else {
          item.staffIdAndName = "";
        }
        if (item.backupStaffName && item.backupStaffId) {
          item.backupStaffNameAndId =
            item.backupStaffName + "(" + item.backupStaffId + ")";
        } else {
          item.backupStaffNameAndId = "";
        }

        return item;
      });
    }
  },
  methods: {
    ...mapActions([
      "getPOList",
      "getKeyRoleTableData",
      "getKeyRoleList",
      "getEmployeeByTeam"
    ]),
    onCreate() {
      this.dialogTitle = "添加关键角色信息";
      this.dialogVisible = true;
      this.getKeyRoleList();
      this.getEmployeeByTeam(this.teamInfo.projectID + "");
      this.roleDataSource = [];
      this.empDataSource = [];
    },
    arraySpanMethod({ row, column, rowIndex, columnIndex }) {
      if (
        column.property === "poRoleName" ||
        column.property === "option" ||
        column.property === "amount"
      ) {
        return {
          rowspan: row.amount,
          colspan: 1
        };
      }
    },
    handleEdit(index, row) {
      this.dialogTitle = "编辑关键角色信息";
      this.getKeyRoleList();
      this.getEmployeeByTeam(this.teamInfo.projectID + "");
      this.dialogVisible = true;
    },
    handleDelete() {},
    handleConfirm() {
      let vm = this;
      let param = {
        projectRoles: vm.empDataSource.map(item => {
          return {
            teamId: vm.teamInfo.projectID,
            projectId: vm.searchForm.poId,
            projectName: vm.searchForm.poOptions.find(
              item => item.projectId === vm.searchForm.poId
            ).projectName,
            staffId: item.staffId,
            poRoleId: item.roleId,
            backupStaffId: item.backupStaffId || ""
          };
        })
      };
      vm.$store
        .dispatch("saveProjectKeyRole", param)
        .then(res => {
          if (res.success) {
            vm.$message.success("关键角色保存成功");
            vm.getKeyRoleTableData();
          } else {
            vm.$message.error("关键角色保存失败");
            vm.keyRoleDataSource = [];
          }
          vm.dialogVisible = false;
        })
        .catch(() => {
          vm.$message.error("关键角色保存失败");
          vm.keyRoleDataSource = [];
          vm.dialogVisible = false;
        });
    },
    onExtendPrePeriod() {
      this.showPrePeriod = true;
    },
    remoteMethod() {},
    changePO() {
      this.getKeyRoleTableData();
    },
    handleAddRoleNum() {
      this.isEditRow = false;
      this.isAddRow = false;
      let role = _.first(this.searchForm.keyRoleOptions);
      let row = {
        roleId: (role && role.poRoleId) || "",
        roleName: (role && role.poRoleName) || "",
        roleNum: 1,
        isAdd: true
      };
      this.roleDataSource.unshift(row);
    },
    handleSaveRoleNum() {
      //查找新增/编辑的行数据
      let newRow = this.roleDataSource.find(item => item.isAdd);
      if (
        this.roleDataSource.find(
          item => item.roleId === newRow.roleId && !item.isAdd
        )
      ) {
        this.$message.warning("需要配置的关键角色已存在，请重新选择关键角色");
        return;
      }
      //根据新增/编辑行的角色ID从关联员工表中匹配数量
      let empList = this.empDataSource.filter(
        item => item.roleId === newRow.roleId
      );
      //如果要新增/编辑的角色数量大于等于关联员工表中匹配的数量，需要在员工表中新增行，否则提示用户手动从关联员工表中删除
      if (empList.length <= newRow.roleNum) {
        for (let i = 0; i < newRow.roleNum - empList.length; i++) {
          let employee = {
            roleId: newRow.roleId,
            roleName: _.find(this.searchForm.keyRoleOptions, {
              poRoleId: newRow.roleId
            }).poRoleName,
            staffId: "",
            staffName: "",
            backupStaffId: "",
            backupStaffName: ""
          };
          this.empDataSource.unshift(employee);
        }
      } else {
        this.$message.warning(
          "需要配置的关键角色数量小于该角色已经配置的员工数量，请手动从关联员工表中删除"
        );
        return;
      }
      this.roleDataSource.map(item => {
        if (item.isAdd) {
          item.roleName = _.find(this.searchForm.keyRoleOptions, {
            poRoleId: item.roleId
          }).poRoleName;
          item.isAdd = false;
        }
        return item;
      });
      this.isAddRow = true;
    },
    handleCancelRoleNum() {
      let vm = this;
      if (vm.isEditRow) {
        vm.roleDataSource.map(item => {
          if (item.isAdd) {
            item.roleId = vm.editRowData.roleId;
            item.roleName = vm.editRowData.roleName;
            item.roleNum = vm.editRowData.roleNum;
            item.isAdd = false;
          }
          return item;
        });
      } else {
        this.roleDataSource = this.roleDataSource.filter(item => !item.isAdd);
      }
      this.isAddRow = true;
    },
    handleRoleRowEdit(row) {
      let vm = this;
      if (vm.roleDataSource.find(item => item.isAdd)) {
        vm.$message.warning("存在正在编辑的行，请先保存");
        return;
      }
      vm.editRowData = _.clone(row);
      row.isAdd = true;
      vm.isAddRow = false;
      vm.isEditRow = true;
    },
    handleRoleRowDelete(index, row) {
      let vm = this;
      if (row.isAdd) {
        vm.roleDataSource = vm.roleDataSource.filter(item => !item.isAdd);
        vm.isAddRow = true;
        return;
      }
      if (vm.roleDataSource.find(item => item.isAdd)) {
        vm.$message.warning("存在正在编辑的行，请先保存");
        return;
      }
      if (vm.empDataSource.find(item => item.roleId === row.roleId)) {
        vm.$confirm("该角色已经配置了员工, 是否继续?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          vm.roleDataSource.splice(index, 1);
          vm.empDataSource = vm.empDataSource.filter(
            item => item.roleId !== row.roleId
          );
        });
      }
    },
    handleEmpRowDelete(index, row) {
      let vm = this;
      if (vm.roleDataSource.find(item => item.isAdd)) {
        vm.$message.warning("关键角色表存在正在编辑的行，请先保存");
        return;
      }
      vm.roleDataSource.map(item => {
        if (item.roleId === row.roleId) {
          item.roleNum--;
        }
        return item;
      });
      vm.empDataSource.splice(index, 1);
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss">
.team-settings {
  .el-table {
    .el-button--mini {
      margin-top: 3px;
      padding: 0px;
      font-size: 18px;
    }
  }
  .key-role-setting {
    .el-dialog__body {
      padding: 10px 20px;
      .el-card__header {
        padding: 10px 20px;
      }
      .el-card__body {
        padding: 10px;
        .role-number {
          .el-button--mini {
            margin-top: 3px;
            padding: 0px;
            font-size: 18px;
          }
        }
      }
    }
  }
}
</style>
