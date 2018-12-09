<template>
  <div class="role-container">
    <el-table
      v-loading="loading"
      :data="tableData"
      border
      fit
      size="mini"
      stripe
      max-height="375"
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column
        header-align="center"
        align="center"
        :label="$t('table.id')"
        type="index"
        width="80"
      ></el-table-column>

      <el-table-column min-width="150px" header-align="center" label="所属体系" prop="system" sortable></el-table-column>
      <el-table-column
        min-width="150px"
        header-align="center"
        label="角色名称"
        prop="roleName"
        sortable
      ></el-table-column>
      <el-table-column
        min-width="100px"
        header-align="center"
        label="创建人"
        prop="creatorName"
        sortable
      ></el-table-column>
      <el-table-column
        min-width="150px"
        header-align="center"
        label="最后更新人"
        prop="updateStaffName"
        sortable
      ></el-table-column>
      <el-table-column
        min-width="150px"
        header-align="center"
        label="最后更新时间"
        prop="updateTime"
        sortable
      >
        <template slot-scope="scope">
          <span>{{ scope.row.updateTime | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" width="130" header-align="center" :label="$t('table.option')">
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
            @click="handleDel(scope.row.roleId);"
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
    <el-dialog :title="dialogBaseTitle" :visible.sync="dialogBaseVisible" width="30%">
      <el-form :model="roleForm" size="mini" label-width="120px" ref="roleForm" :rules="rules">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleForm.roleName" autocomplete="off" required maxlength="64"></el-input>
        </el-form-item>
        <el-form-item label="所属体系" prop="system">
          <el-select
            v-model="roleForm.system"
            filterable
            allow-create
            default-first-option
            placeholder="请选择"
          >
            <el-option
              v-for="item in systemOptions"
              :key="item"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogBaseVisible = false;" size="mini">取 消</el-button>
        <el-button type="primary" @click="handleSubmit();" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { formatDate } from "@/utils/date";
import { mapGetters, mapActions, mapState } from "vuex";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }
  },
  props: ["condition"],
  computed: {
    ...mapGetters(["employeeId", "employeeName"]),
    ...mapState({
      systemOptions: state => state.ruleStore.systemOptions
    })
  },
  mounted() {
    this.getSystemInfo();
  },
  data() {
    return {
      loading: false,
      tableData: [],
      initList: [],
      dialogBaseTitle: "添加关键角色",
      dialogBaseVisible: false,
      isEdit: false,
      page: {
        pageNum: 1,
        pageSize: 10,
        totalRecord: 0
      },
      rules: {
        roleName: [
          { required: true, message: "请输入角色名称", trigger: "blur" }
        ],
        system: [
          { required: true, message: "请选择所属体系", trigger: "change" }
        ],
        product: [
          { required: true, message: "请选择产品线", trigger: "change" }
        ]
      },
      roleForm: {
        roleName: "",
        system: "",
        product: ""
      }
    };
  },
  methods: {
    ...mapActions(["getRoleInfo", "getAllRole", "getSystemInfo"]),
    getRoleList() {
      let vm = this;
      vm.loading = true;
      vm.getRoleInfo(vm.condition)
        .then(res => {
          if (res.success) {
            vm.initList = res.data;
            vm.tableData = vm.initList.slice(0, vm.page.pageSize);
            vm.page.totalRecord = res.data.length;
          } else {
            vm.initList = [];
            vm.tableData = [];
            vm.page.totalRecord = 0;
          }
          vm.loading = false;
        })
        .catch(() => {
          vm.initList = [];
          vm.tableData = [];
          vm.page.totalRecord = 0;
          vm.loading = false;
        });
    },
    handleCurrentChange(val) {
      let vm = this;
      vm.page.currentPage = val;
      vm.tableData = vm.initList.slice(
        (val - 1) * vm.page.pageSize,
        val * vm.page.pageSize
      );
    },
    handleEdit(rowData) {
      this.isEdit = true;
      this.roleForm.roleId = rowData.roleId;
      this.roleForm.roleName = rowData.roleName;
      this.roleForm.system = rowData.system;
      this.dialogBaseTitle = "编辑关键角色";
      this.dialogBaseVisible = true;
    },
    handleDel(roleId) {
      let vm = this;
      vm.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        vm.$store.dispatch("delRoleInfo", roleId).then(res => {
          if (res.success) {
            vm.$message.success(res.message);
            vm.getRoleList();
            vm.getAllRole();
          } else {
            vm.$message.error(res.message);
          }
        });
      });
    },
    handleFilter() {
      this.page.currentPage = 1;
      this.getRoleList();
    },
    handleAdd() {
      this.roleForm.roleName = "";
      this.roleForm.system = "";
      this.isEdit = false;
      this.dialogBaseTitle = "添加关键角色";
      this.dialogBaseVisible = true;
    },
    handleSubmit() {
      let vm = this;
      vm.$refs.roleForm.validate(valid => {
        if (valid) {
          let formData = {
            roleName: vm.roleForm.roleName,
            system: vm.roleForm.system,
            creatorId: vm.employeeId
          };
          if (vm.isEdit) {
            formData.roleId = vm.roleForm.roleId;
            formData.updateStaffId = vm.employeeId;
            formData.updateStaffName = vm.employeeName;
          } else {
            formData.roleId = -1;
            formData.creatorName = vm.employeeName;
          }
          vm.$store.dispatch("saveRoleInfo", formData).then(res => {
            if (res.success) {
              vm.$message.success(res.message);
              vm.getRoleList();
              vm.getAllRole();
            } else {
              vm.$message.error(res.message);
            }
            vm.dialogBaseVisible = false;
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
  .el-select {
    width: 249px;
  }
}
</style>
