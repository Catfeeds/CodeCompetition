<template>
  <div>
    <el-row type="flex" justify="end">
      <el-button type="primary" size="mini" @click="handleAdd">新增</el-button>
    </el-row>
    <el-table
      v-loading="roleTab.loading"
      :data="roleTab.tableData"
      border
      fit
      size="mini"
      stripe
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
      <el-table-column align="center" width="200" header-align="center" :label="$t('table.option')">
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
  </div>
</template>
<script>
export default {
  data() {
    return {
      roleTab: {
        loading: false,
        tableData: []
      }
    };
  }
};
</script>

