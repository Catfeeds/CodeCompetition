<template>
  <div class="role-container">
    <el-form :model="roleForm" size="mini" ref="roleForm" :rules="rules">
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

        <el-table-column
          min-width="150px"
          header-align="center"
          label="所属体系"
          prop="system"
          :sortable="!isAdd"
        >
          <template slot-scope="scope">
            <el-form-item label prop="system" v-if="scope.row.isAdd">
              <el-select
                v-model="roleForm.system"
                placeholder="请选择"
                style="width:130px;"
              >
                <el-option v-for="item in systemOptions" :key="item" :label="item" :value="item"></el-option>
              </el-select>
            </el-form-item>
            <span v-else>{{scope.row.system}}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          header-align="center"
          label="角色名称"
          prop="roleName"
          :sortable="!isAdd"
        >
          <template slot-scope="scope">
            <el-form-item label prop="roleName" v-if="scope.row.isAdd">
              <el-input v-model="roleForm.roleName" autocomplete="off" required maxlength="64" style="width:130px;"></el-input>
            </el-form-item>
            <span v-else>{{scope.row.roleName}}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="100px"
          header-align="center"
          label="创建人"
          prop="creatorName"
          :sortable="!isAdd"
        ></el-table-column>
        <el-table-column
          min-width="150px"
          header-align="center"
          label="最后更新人"
          prop="updateStaffName"
          :sortable="!isAdd"
        ></el-table-column>
        <el-table-column
          min-width="150px"
          header-align="center"
          label="最后更新时间"
          prop="updateTime"
          :sortable="!isAdd"
        >
          <template slot-scope="scope">
            <span v-if="!scope.row.isAdd">{{ scope.row.updateTime | formatDate }}</span>
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
              @click="handleDel(scope.row.roleId);"
            ></el-button>
            <el-button
              v-if="scope.row.isAdd"
              type="text"
              size="mini"
              icon="el-icon-circle-plus-outline"
              title="保存"
              @click="handleSubmit(scope.row);"
            ></el-button>
            <el-button
              v-if="scope.row.isAdd"
              type="text"
              size="mini"
              icon="el-icon-remove-outline"
              title="取消"
              @click="getRoleList();"
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
      isEdit: false,
      isAdd: false,
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
    ...mapActions(["getRoleInfo", "getRoleOption", "getSystemInfo"]),
    getRoleList() {
      let vm = this;
      vm.loading = true;
      vm.isAdd = false;
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
      if (this.validTableStatus()) {
        return;
      }
      this.isEdit = true;
      this.isAdd = true;
      this.roleForm.roleId = rowData.roleId;
      this.roleForm.roleName = rowData.roleName;
      this.roleForm.system = rowData.system;
      rowData.isAdd = true;
    },
    handleDel(roleId) {
      if (this.validTableStatus()) {
        return;
      }
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
            vm.getRoleOption();
          } else {
            vm.$message.error(res.message);
          }
        });
      });
    },
    handleFilter() {
      if (this.validTableStatus()) {
        return;
      }
      this.page.currentPage = 1;
      this.getRoleList();
    },
    handleAdd() {
      if (this.validTableStatus()) {
        return;
      }
      this.roleForm.roleName = "";
      this.roleForm.system = "";
      this.isEdit = false;
      this.isAdd = true;
      this.tableData.unshift({ isAdd: true });
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
          vm.isAdd = false;
          vm.$store.dispatch("saveRoleInfo", formData).then(res => {
            if (res.success) {
              vm.$message.success(res.message);
              vm.getRoleList();
              vm.getRoleOption();
            } else {
              vm.$message.error(res.message);
            }
          });
        } else {
          return false;
        }
      });
    },
    validTableStatus() {
      if (this.tableData.find(item => item.isAdd)) {
        this.$message.warning("表格存在正在编辑的数据，请先保存");
        return true;
      }
      return false;
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
