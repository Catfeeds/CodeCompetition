<template>
  <div class="loginUser-container">
    <el-row type="flex" justify="end">
      <el-button type="primary" size="mini" @click="handleAdd">新增</el-button>
    </el-row>
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
      <el-table-column header-align="center" :label="$t('table.id')" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" label="姓名" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" label="工号" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.roleDesc }}</span>
        </template>
      </el-table-column>
      <el-table-column min-width="150px" header-align="center" label="部门" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.roleDesc }}</span>
        </template>
      </el-table-column>
      <el-table-column min-width="150px" header-align="center" label="岗位" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.roleDesc }}</span>
        </template>
      </el-table-column>
      <el-table-column min-width="150px" header-align="center" label="系统角色" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.roleDesc }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" width="200" header-align="center" :label="$t('table.option')">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            @click="handleEdit(scope.row.roleId)"
          >编辑</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-delete"
            @click="handleDel(scope.row.roleId)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row type="flex" justify="end">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[100, 200, 300, 400]"
        :page-size="100"
        layout="total, slot, prev, pager, next, jumper"
        :total="totalPage"
      ></el-pagination>
    </el-row>
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="30%">
      <el-form :model="loginForm" size="mini" label-width="80px" ref="loginForm" :rules="rules">
        <el-form-item label="姓名" prop="userName">
          <el-input v-model="loginForm.userName" autocomplete="off" required maxlength="64"></el-input>
        </el-form-item>
        <el-form-item label="工号" prop="userId">
          <el-input v-model="loginForm.userId" autocomplete="off" maxlength="128"></el-input>
        </el-form-item>
        <el-form-item label="部门" prop="department">
          <el-select v-model="loginForm.department" size="mini" placeholder="请选择" @change="changeDU">
              <el-option
                v-for="item in deptOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="岗位" prop="post">
          <el-select v-model="loginForm.post" size="mini" placeholder="请选择" @change="changeDU">
              <el-option
                v-for="item in postOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="系统角色" prop="systemRole">
          <el-select v-model="loginForm.systemRole" size="mini" placeholder="请选择" @change="changeDU">
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
      currentPage: 1,
      totalPage: 0,
      dialogTitle: "",
      loading: false,
      dialogVisible: false,
      tableData: [],
      deptOptions: [],
      roleOptions: [],
      postOptions: [],
      loginForm: {
        userName: "",
        userId: "",
        department: "",
        post: "",
        systemRole: ""
      },
      rules: {
        userName: [
          { required: true, message: "请输入名称", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "请输入工号", trigger: "blur" }
        ],
        department: [
          { required: true, message: "请选择部门", trigger: "change" }
        ],
        post: [
          { required: true, message: "请选择岗位", trigger: "change" }
        ],
        systemRole: [
          { required: true, message: "请选择系统角色", trigger: "change" }
        ]
      }
    };
  },
  mounted() {
    this.loading = true;
    this.$store
      .dispatch("getLoginUserList")
      .then(data => {
        this.tableData = data;
        this.loading = false;
      })
      .catch(error => {
        console.log(error);
        this.loading = false;
      });
  },
  methods: {
    handleCurrentChange(val) {

    },
    handleDel(id) {
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delRoleInfo", id).then(res => {
          if (!res.data.code) {
            vm.$message({
              type: "success",
              message: "删除成功!"
            });
          } else {
            vm.$message({
              type: "error",
              message: "删除失败!"
            });
          }
        });
      });
    },
    handleEdit(id) {
      let vm = this;
      vm.$store.dispatch("getRoleInfoById", id).then(res => {
        if (res.data) {
          vm.roleForm.roleName = res.data.roleName;
          vm.roleForm.description = res.data.description;
          vm.dialogTitle = "编辑角色";
          vm.dialogVisible = true;
        }
      });
    },
    handleAdd() {
      this.dialogVisible = true;
      this.dialogTitle = "添加登录用户";
      this.roleForm.roleName = "";
      this.roleForm.description = "";
      this.$refs.tree.setCheckedKeys([]);
    },
    submtForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$store.dispatch("addRoleInfo", this.roleForm).then(res => {
            if (res) {
              this.$message.success("角色添加成功");
            }
          });
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.loginUser-container {
  margin: 15px;
}
</style>

