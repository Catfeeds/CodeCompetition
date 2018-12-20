<template>
  <div class="app-container role-container">
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
      max-height="450px"
      style="width: 100%;margin-top:15px;"
    >
      <el-table-column
        header-align="center"
        align="center"
        :label="$t('table.id')"
        type="index"
        width="80"
      ></el-table-column>

      <el-table-column min-width="150px" header-align="center" label="角色名称" prop="roleName">
        <template slot-scope="scope">
          <el-popover placement="right-end" title="菜单项" width="300" trigger="hover">
            <el-tree
              aria-required
              :data="scope.row.menuInfo"
              node-key="id"
              default-expand-all
              :props="defaultProps"
              ref="tree"
            ></el-tree>
            <el-button type="text" slot="reference">{{scope.row.roleName}}</el-button>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column min-width="150px" header-align="center" label="角色描述" prop="description"></el-table-column>
      <el-table-column align="center" :label="$t('table.option')" width="110" header-align="center">
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
            @click="handleDel(scope.row.roleId);"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="30%" :close-on-click-modal="false">
      <el-form :model="roleForm" size="mini" label-width="80px" ref="roleForm" :rules="rules">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleForm.roleName" autocomplete="off" required maxlength="64"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="description">
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
                :default-checked-keys="selectedNodes"
                ref="tree"
              ></el-tree>
            </el-scrollbar>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="submtForm('roleForm');" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    let vm = this;
    let validMenuTree = (rule, value, callback) => {
      vm.roleForm.menuIds = vm.$refs.tree.getCheckedNodes().map(item => {
        return {
          menuId: item.id
        };
      });
      if (vm.roleForm.menuIds.length <= 0) {
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
      selectedNodes: [],
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
        description: [
          { required: true, message: "请输入角色描述", trigger: "blur" }
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
    this.getRoleList();
    this.$store.dispatch("getAllMenuInfo").then(res => {
      console.log(111);
      if (res.success) {
        this.menuTreeData = res.data
          .filter(item => !item.parentId && item.menuId > 1)
          .map(item => {
            return {
              id: item.menuId,
              name: item.note,
              children: res.data
                .filter(menu => menu.parentId === item.menuId)
                .map(menu => {
                  return {
                    id: menu.menuId,
                    name: menu.note
                  };
                })
            };
          });
      } else {
        this.menuTreeData = [];
      }
    });
  },
  methods: {
    getRoleList() {
      let vm = this;
      vm.loading = true;
      vm.$store
        .dispatch("getSysRoleList")
        .then(res => {
          if (res.success) {
            vm.tableData = res.data.map(data => {
              data.menuInfo = data.menuInfos
                .filter(item => !item.parentId && item.menuId > 1)
                .map(item => {
                  return {
                    id: item.menuId,
                    name: item.note,
                    children: data.menuInfos
                      .filter(menu => menu.parentId === item.menuId)
                      .map(menu => {
                        return {
                          id: menu.menuId,
                          name: menu.note
                        };
                      })
                  };
                });
              return data;
            });
          } else {
            vm.tableData = [];
          }
          vm.loading = false;
        })
        .catch(error => {
          console.log(error);
          vm.loading = false;
        });
    },
    handleDel(id) {
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delSysRoleInfo", id).then(res => {
          if (!res.code) {
            vm.$message.success(res.msg);
            vm.getRoleList();
          } else {
            vm.$message.error(res.msg);
          }
        });
      });
    },
    handleEdit(rowData) {
      let vm = this;
      vm.$store.dispatch("getMenuInfoByRoleId", rowData.roleId).then(res => {
        if (res.success) {
          vm.roleForm.roleName = rowData.roleName;
          vm.roleForm.description = rowData.description;
          vm.roleForm.roleId = rowData.roleId;
          vm.dialogTitle = "编辑角色";
          vm.dialogVisible = true;
          if (!vm.$refs.tree) {
            vm.selectedNodes = res.data.map(item => item.menuId);
          } else {
            vm.clearValidate("roleForm");
            vm.$refs.tree.setCheckedNodes(
              res.data.map(item => {
                return { id: item.menuId, name: item.note };
              })
            );
          }
        }
      });
    },
    addRole() {
      this.dialogVisible = true;
      this.dialogTitle = "添加角色";
      this.roleForm.roleName = "";
      this.roleForm.description = "";
      this.roleForm.roleId = -1;
      if (this.$refs.tree) {
        this.clearValidate("roleForm");
        this.$refs.tree.setCheckedKeys([]);
      }
    },
    submtForm(formName) {
      let vm = this;
      vm.$refs[formName].validate(valid => {
        if (valid) {
          let formData = {
            sysRole: {
              roleName: vm.roleForm.roleName,
              description: vm.roleForm.description
            },
            menuInfoList: vm.roleForm.menuIds
          };
          let requestName = "addSysRoleInfo";
          if (vm.roleForm.roleId >= 0) {
            requestName = "editSysRoleInfo";
            formData.sysRole.roleId = vm.roleForm.roleId;
          }
          vm.$store.dispatch(requestName, formData).then(res => {
            if (res.success) {
              vm.$message.success(res.message);
              vm.dialogVisible = false;
              vm.getRoleList();
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
      this.$refs[formName].clearValidate();
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.role-container {
  .el-scrollbar {
    height: 100%;
    .el-scrollbar__wrap {
      overflow-x: hidden;
    }
  }
}
</style>
