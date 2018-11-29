<template>
  <div class="role-container">
    <el-row type="flex" justify="end">
      <el-button type="primary" size="mini" @click="addRole">新增</el-button>
    </el-row>
    <el-table
      v-loading="loading"
      :data="tableData"
      border
      fit
      size="mini"
      stripe
      highlight-current-row
      height="450px"
      style="width: 100%;margin-top:15px;"
    >
      <el-table-column header-align="center" :label="$t('table.id')" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" label="角色名称" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" label="角色描述" sortable>
        <template slot-scope="scope">
          <span>{{ scope.row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.option')" width="200" header-align="center">
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
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="30%">
      <el-form :model="roleForm" size="mini" label-width="80px" ref="roleForm" :rules="rules">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleForm.roleName" autocomplete="off" required maxlength="64"></el-input>
        </el-form-item>
        <el-form-item label="角色描述">
          <el-input v-model="roleForm.description" autocomplete="off" maxlength="128"></el-input>
        </el-form-item>
        <el-form-item label="菜单项" prop="menuTree">
          <div style="height:150px;">
            <el-scrollbar>
              <el-tree
                aria-required
                :data="menuTreeData"
                show-checkbox
                node-key="id"
                default-expand-all
                :props="defaultProps"
                ref="tree"
              ></el-tree>
            </el-scrollbar>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
        <el-button type="primary" @click="submtForm('roleForm')" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    let vm = this;
    let validMenuTree = (rule, value, callback) => {
      vm.roleForm.meunIds = vm.$refs.tree.getCheckedNodes().map(item => {
        return item.id;
      });
      if (vm.roleForm.meunIds.length <= 0) {
        callback(new Error(rule.message));
      } else {
        callback();
      }
    };
    return {
      dialogTitle: "",
      loading: false,
      dialogVisible: false,
      tableData: [],
      roleForm: {
        roleName: "",
        description: "",
        menuIds: []
      },
      defaultProps: {
        children: "children",
        label: "name"
      },
      rules: {
        roleName: [
          { required: true, message: "请输入角色名称", trigger: "blur" }
        ],
        menuTree: [
          {
            required: true,
            message: "请选项菜单项",
            validator: validMenuTree,
            trigger: "change"
          }
        ]
      },
      menuTreeData: []
    };
  },
  mounted() {
    this.loading = true;
    this.$store
      .dispatch("getRoleList")
      .then(data => {
        this.tableData = data;
        this.loading = false;
      })
      .catch(error => {
        console.log(error);
        this.loading = false;
      });
    this.$store.dispatch("getAllMenuInfo").then(data => {
      this.menuTreeData = data
        .filter(item => !item.parentId)
        .map(item => {
          return {
            id: item.menuId,
            name: item.note,
            children: data
              .filter(menu => menu.parentId === item.menuId)
              .map(menu => {
                return {
                  id: menu.menuId,
                  name: menu.note
                };
              })
          };
        });
    });
  },
  methods: {
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
      vm.$store.dispatch("getRoleInfoById", id).then(res=>{
        if(res.data) {
          vm.roleForm.roleName = res.data.roleName;
          vm.roleForm.description = res.data.description;
          vm.dialogTitle = "编辑角色";
          vm.dialogVisible = true;
        }
      })
    },
    addRole() {
      this.dialogVisible = true;
      this.dialogTitle = "添加角色";
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
.role-container {
  margin: 15px;
  .el-scrollbar {
    height: 100%;
    .el-scrollbar__wrap {
      overflow-x: hidden;
    }
  }
}
</style>

