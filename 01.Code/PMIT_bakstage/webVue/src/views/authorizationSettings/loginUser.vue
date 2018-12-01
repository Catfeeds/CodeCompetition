<template>
  <div class="app-container login-user-container">
    <el-row type="flex" justify="end">
      <el-button type="primary" size="mini" @click="handleAdd">新增</el-button>
    </el-row>
    <el-row>
      <el-table
        v-loading="loading"
        :data="tableData"
        border
        fit
        size="mini"
        stripe
        height="400px"
        highlight-current-row
        style="width: 100%;margin:15px 0px;"
      >
        <el-table-column header-align="center" align="center" :label="$t('table.id')" width="80">
          <template slot-scope="scope">
            <span>{{ scope.row.id }}</span>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" header-align="center" label="姓名" sortable>
          <template slot-scope="scope">
            <span>{{ scope.row.employeeName }}</span>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" header-align="center" label="工号" sortable>
          <template slot-scope="scope">
            <span>{{ scope.row.employeeID }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="200px" header-align="center" label="部门" sortable>
          <template slot-scope="scope">
            <span>{{ scope.row.pdu }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="100px" header-align="center" label="岗位" sortable>
          <template slot-scope="scope">
            <span>{{ scope.row.positionRole }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" header-align="center" label="系统角色" sortable>
          <template slot-scope="scope">
            <span>{{ scope.row.roleName }}</span>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          width="200"
          header-align="center"
          :label="$t('table.option')"
        >
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
              @click="handleEdit(scope.row)"
            >编辑</el-button>
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-delete"
              @click="handleDel(scope.row.employeeID)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" justify="end">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="page.currentPage"
        :page-size="page.pageSize"
        layout="total, slot, prev, pager, next, jumper"
        :total="page.totalPage"
      ></el-pagination>
    </el-row>
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="30%">
      <el-form :model="loginForm" size="mini" label-width="80px" ref="loginForm" :rules="rules">
        <el-form-item label="姓名" prop="employeeName">
          <el-input v-if="isEdit" v-model="loginForm.employeeName" autocomplete="off" disabled></el-input>
          <el-autocomplete v-else
            popper-class="my-autocomplete"
            v-model="loginForm.employeeName"
            :trigger-on-focus="false"
            :fetch-suggestions="searchEmployee"
            placeholder="请输入员工姓名进行查询"
            @select="handleSelectName"
          >
            <i class="el-icon-delete el-input__icon" slot="suffix" @click="handleClear"></i>
            <template slot-scope="{ item }">
              <div class="name">{{ item.employeeName }}</div>
              <span class="addr">{{ item.employeeID }}</span>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="工号" prop="employeeId">
          <el-input v-if="isEdit" v-model="loginForm.employeeId" autocomplete="off" disabled></el-input>
          <el-autocomplete v-else
            popper-class="my-autocomplete"
            v-model="loginForm.employeeId"
            :trigger-on-focus="false"
            :fetch-suggestions="searchEmployee"
            placeholder="请输入员工工号进行查询"
            @select="handleSelectId"
          >
            <i class="el-icon-delete el-input__icon" slot="suffix" @click="handleClear"></i>
            <template slot-scope="{ item }">
              <div class="name">{{ item.employeeName }}</div>
              <span class="addr">{{ item.employeeID }}</span>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="部门" prop="department">
          <el-input v-model="loginForm.department" autocomplete="off" maxlength="128" disabled></el-input>
        </el-form-item>
        <el-form-item label="岗位" prop="post">
          <el-input v-model="loginForm.post" autocomplete="off" maxlength="128" disabled></el-input>
        </el-form-item>
        <el-form-item label="系统角色" prop="systemRole">
          <el-select v-model="loginForm.systemRole" size="mini" placeholder="请选择">
            <el-option
              v-for="item in roleOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
        <el-button type="primary" @click="submtForm('loginForm')" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    let vm = this;
    return {
      isEdit: false,
      dialogTitle: "",
      loading: false,
      dialogVisible: false,
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
          { required: true, message: "请输入名称", trigger: "blur" }
        ],
        employeeId: [
          { required: true, message: "请输入工号", trigger: "blur" }
        ],
        systemRole: [
          { required: true, message: "请选择系统角色", trigger: "change" }
        ]
      },
      page: {
        currentPage: 1,
        totalPage: 0,
        pageSize: 10
      }
    };
  },
  mounted() {
    this.page.currentPage = 1;
    this.getLogiUserList();
    this.$store.dispatch("getRoleList").then(res => {
      this.roleOptions = res.map(item => {
        return {
          label: item.roleName,
          value: item.roleId
        };
      });
    });
  },
  methods: {
    getLogiUserList() {
      let vm = this;
      vm.loading = true;
      vm.$store
        .dispatch("getLoginUserList", vm.page)
        .then(data => {
          if (data.success) {
            vm.tableData = data.data.list.map((item, index) => {
              item.id = index + 1;
              return item;
            });
            vm.page.totalPage = data.data.total;
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
    handleDel(id) {
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
      let vm = this;
      vm.$store.dispatch("getLoginUserById", rowData.employeeID).then(res => {
        if (res.success) {
          vm.loginForm.employeeName = res.data.employeeName;
          vm.loginForm.employeeId = res.data.employeeID;
          vm.loginForm.department = res.data.pdu;
          vm.loginForm.post = res.data.positionRole;
          vm.loginForm.systemRole = res.data.roleName;
          vm.dialogTitle = "编辑登录用户";
          vm.dialogVisible = true;
          vm.isEdit = true;
        }
      });
    },
    handleAdd() {
      this.dialogVisible = true;
      this.dialogTitle = "添加登录用户";
      this.loginForm.employeeName = "";
      this.loginForm.employeeId = "";
      this.loginForm.department = "";
      this.loginForm.post = "";
      this.loginForm.systemRole = "";
      this.isEdit = false;
    },
    handleClear() {
      this.loginForm.employeeId = "";
      this.loginForm.employeeName = "";
      this.loginForm.post = "";
      this.loginForm.department = "";
    },
    submtForm(formName) {
      let vm = this;
      vm.$refs[formName].validate(valid => {
        if (valid) {
          let requestName = "addLoginUser";
          let formData = {
            employeeID: vm.loginForm.employeeId,
            employeeName: vm.loginForm.employeeName,
            roleID: vm.loginForm.systemRole
          };
          if (vm.isEdit) {
            requestName = "editLoginUser";
            formData = {
              employeeID: vm.loginForm.employeeId,
              roleID: vm.loginForm.systemRole
            };
          }
          vm.$store.dispatch(requestName, formData).then(res => {
            if (!res.code) {
              vm.$message.success(res.msg);
              vm.dialogVisible = false;
              vm.getLogiUserList();
            } else {
              vm.$message.error(res.msg);
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

