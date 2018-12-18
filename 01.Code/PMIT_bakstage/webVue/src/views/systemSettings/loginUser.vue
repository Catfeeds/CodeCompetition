<template>
  <div class="app-container login-user-container">
    <el-row type="flex" justify="end">
      <el-button type="primary" size="mini" @click="handleAdd">新增</el-button>
    </el-row>
    <el-row>
      <el-form :model="loginForm" size="mini" ref="loginForm" :rules="rules">
        <el-table
          v-loading="loading"
          :data="tableData"
          border
          fit
          size="mini"
          stripe
          max-height="375"
          highlight-current-row
          style="width: 100%;margin:15px 0px;"
          @sort-change="handleSort"
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
            label="姓名"
            :sortable="sortable"
            prop="employeeName"
          >
            <template slot-scope="scope">
              <el-form-item label prop="employeeName" v-if="scope.row.isAdd">
                <el-autocomplete
                  v-model="loginForm.employeeName"
                  :trigger-on-focus="false"
                  :fetch-suggestions="searchEmployee"
                  placeholder="请输入员工姓名进行查询"
                  @select="handleSelectName"
                  style="width:180px"
                >
                  <i class="el-icon-delete el-input__icon" slot="suffix" @click="handleClear"></i>
                  <template slot-scope="{ item }">
                    <div style="float:left;margin-right:15px;">{{ item.employeeName }}</div>
                    <span style="margin-right:15px">{{ item.employeeID }}</span>
                    <span>{{ item.pdu }}</span>
                  </template>
                </el-autocomplete>
              </el-form-item>
              <span v-else>{{scope.row.employeeName}}</span>
            </template>
          </el-table-column>

          <el-table-column
            min-width="150px"
            header-align="center"
            label="工号"
            :sortable="sortable"
            prop="employeeID"
          >
            <template slot-scope="scope">
              <el-form-item label prop="employeeId" v-if="scope.row.isAdd">
                <el-autocomplete
                  v-model="loginForm.employeeId"
                  :trigger-on-focus="false"
                  :fetch-suggestions="searchEmployee"
                  placeholder="请输入员工工号进行查询"
                  @select="handleSelectId"
                  style="width:180px"
                >
                  <i class="el-icon-delete el-input__icon" slot="suffix" @click="handleClear"></i>
                  <template slot-scope="{ item }">
                    <div style="float:left;margin-right:15px;">{{ item.employeeName }}</div>
                    <span style="margin-right:15px">{{ item.employeeID }}</span>
                    <span>{{ item.pdu }}</span>
                  </template>
                </el-autocomplete>
              </el-form-item>
              <span v-else>{{scope.row.employeeID}}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="200px"
            header-align="center"
            label="部门"
            :sortable="sortable"
            prop="pdu"
          >
            <template slot-scope="scope">
              <el-form-item label prop="department" v-if="scope.row.isAdd">
                <el-input v-model="loginForm.department" readonly></el-input>
              </el-form-item>
              <span v-else>{{scope.row.pdu}}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="100px"
            header-align="center"
            label="岗位"
            :sortable="sortable"
            prop="positionRole"
          >
            <template slot-scope="scope">
              <el-form-item label prop="post" v-if="scope.row.isAdd">
                <el-input v-model="loginForm.post" readonly></el-input>
              </el-form-item>
              <span v-else>{{scope.row.positionRole}}</span>
            </template>
          </el-table-column>
          <el-table-column
            min-width="150px"
            header-align="center"
            label="系统角色"
            :sortable="sortable"
            prop="roleName"
          >
            <template slot-scope="scope">
              <el-form-item label prop="systemRole" v-if="scope.row.isAdd||scope.row.isEdit">
                <el-select
                  v-model="loginForm.systemRole"
                  size="mini"
                  placeholder="请选择系统角色"
                  style="width:150px;"
                >
                  <el-option
                    v-for="item in roleOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-form-item>
              <span v-else>{{scope.row.roleName}}</span>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            width="80"
            header-align="center"
            :label="$t('table.option')"
          >
            <template slot-scope="scope">
              <el-button
                v-if="!scope.row.isAdd&&!scope.row.isEdit"
                type="text"
                size="mini"
                icon="el-icon-edit"
                title="编辑"
                @click="handleEdit(scope.row);"
              ></el-button>
              <el-button
                v-if="!scope.row.isAdd&&!scope.row.isEdit"
                type="text"
                size="mini"
                icon="el-icon-delete"
                title="删除"
                @click="handleDel(scope.row.employeeID);"
              ></el-button>
              <el-button
                v-if="scope.row.isAdd||scope.row.isEdit"
                type="text"
                size="mini"
                icon="el-icon-circle-plus-outline"
                title="保存"
                @click="onSave(scope.row);"
              ></el-button>
              <el-button
                v-if="scope.row.isAdd||scope.row.isEdit"
                type="text"
                size="mini"
                icon="el-icon-remove-outline"
                title="取消"
                @click="getLogiUserList();"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
    </el-row>
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
  </div>
</template>

<script>
export default {
  data() {
    let vm = this;
    let validaEmployee = (rule, value, callback)=>{
      if(!value) {
        callback(new Error(rule.emptyMsg));
      }
      if(!vm.loginForm.department) {
        callback(new Error(rule.existMsg));
      }
      callback();
    }
    return {
      isAdd: false,
      loading: false,
      sortable: "custom",
      tableData: [],
      roleOptions: [],
      searchForm: {
        keyword: ""
      },
      loginForm: {
        employeeName: "",
        employeeId: "",
        department: "",
        post: "",
        systemRole: ""
      },
      rules: {
        employeeName: [
          { required: true, validator: validaEmployee, emptyMsg:"请输入姓名", existMsg:"输入的姓名不存在", trigger: "blur" }
        ],
        employeeId: [
          { required: true, validator: validaEmployee, emptyMsg:"请输入工号", existMsg:"输入的工号不存在", trigger: "blur" }
        ],
        systemRole: [
          { required: true, message: "请选择系统角色", trigger: "change" }
        ]
      },
      page: {
        currentPage: 1,
        totalRecord: 0,
        pageSize: 10,
        sortColumn: "employeeName",
        sortType: "asc"
      }
    };
  },
  mounted() {
    this.page.currentPage = 1;
    this.getLogiUserList();
    this.$store.dispatch("getSysRoleList").then(res => {
      if (res.success) {
        this.roleOptions = res.data.map(item => {
          return {
            label: item.roleName,
            value: item.roleId
          };
        });
      } else {
        this.roleOptions = [];
      }
    });
  },
  methods: {
    getLogiUserList() {
      let vm = this;
      vm.isAdd = false;
      vm.loading = true;
      let pageInfo = {
        currPage: vm.page.currentPage,
        pageSize: vm.page.pageSize,
        sortColumn: vm.page.sortColumn,
        sortType: vm.page.sortType
      };
      vm.$store
        .dispatch("getLoginUserList", pageInfo)
        .then(data => {
          if (data.success) {
            vm.tableData = data.data.list;
            vm.page.totalRecord = data.data.total;
          }
          vm.loading = false;
        })
        .catch(error => {
          console.log(error);
          vm.loading = false;
        });
    },
    handleCurrentChange(val) {
      this.page.currentPage = val;
      this.getLogiUserList();
    },
    handleSort(column) {
      if (column.prop) {
        this.page.sortColumn = column.prop;
        this.page.sortType = column.order === "descending" ? "desc" : "asc";
        this.getLogiUserList();
      }
    },
    handleDel(id) {
      if (this.validTableStatus()) {
        return;
      }
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delLoginUser", id).then(res => {
          if (!res.code) {
            vm.$message.success("删除成功");
            vm.getLogiUserList();
          } else {
            vm.$message.error("删除失败");
          }
        });
      });
    },
    handleEdit(rowData) {
      if (this.validTableStatus()) {
        return;
      }
      let vm = this;
      rowData.isEdit = true;
      vm.$store.dispatch("getLoginUserById", rowData.employeeID).then(res => {
        if (res.success) {
          vm.loginForm.employeeName = res.data.employeeName;
          vm.loginForm.employeeId = res.data.employeeID;
          vm.loginForm.department = res.data.pdu;
          vm.loginForm.post = res.data.positionRole;
          vm.loginForm.systemRole = res.data.roleName;
        }
      });
    },
    handleAdd() {
      if (this.validTableStatus()) {
        return;
      }
      this.sortable = false;
      this.isAdd = true;
      this.loginForm.employeeName = "";
      this.loginForm.employeeId = "";
      this.loginForm.department = "";
      this.loginForm.post = "";
      this.loginForm.systemRole = "";
      this.tableData.unshift({ isAdd: true });
    },
    handleClear() {
      this.loginForm.employeeId = "";
      this.loginForm.employeeName = "";
      this.loginForm.post = "";
      this.loginForm.department = "";
    },
    onSave(row) {
      let vm = this;
      vm.$refs.loginForm.validate(valid => {
        if (valid) {
          let requestName = "addLoginUser";
          let formData = {
            employeeID: vm.loginForm.employeeId,
            employeeName: vm.loginForm.employeeName,
            roleID: vm.loginForm.systemRole
          };
          if (!vm.isAdd) {
            requestName = "editLoginUser";
            formData = {
              employeeID: vm.loginForm.employeeId,
              roleID: vm.loginForm.systemRole
            };
          }
          vm.isAdd = false;
          row.isAdd = false;
          row.isEdit = false;
          vm.$store.dispatch(requestName, formData).then(res => {
            if (res.success) {
              vm.$message.success(res.message);
              vm.getLogiUserList();
            } else {
              vm.$message.error(res.message);
            }
          });
        } else {
          return false;
        }
      });
    },
    searchEmployee(queryString, callbackFun) {
      if (!queryString) {
        callbackFun([]);
        return;
      }
      let vm = this;
      vm.$store
        .dispatch("searchEmployeeInfo", queryString)
        .then(res => {
          if (res.success) {
            callbackFun(res.data);
          } else {
            callbackFun([]);
          }
        })
        .catch(error => {
          vm.$message.error(error);
          callbackFun([]);
        });
    },
    handleSelectName(item) {
      this.loginForm.employeeName = item.employeeName;
      this.loginForm.employeeId = item.employeeID;
      this.loginForm.department = item.pdu;
      this.loginForm.post = item.positionRole;
    },
    handleSelectId(item) {
      this.handleSelectName(item);
    },
    validTableStatus() {
      if (this.tableData.find(item => item.isAdd || item.isEdit)) {
        this.$message.warning("表格存在正在编辑的数据，请先保存");
        return true;
      }
      return false;
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login-user-container {
  .el-autocomplete {
    width: 289px;
  }
  .el-select {
    width: 289px;
  }
}
</style>
