<template>
  <div class="permission-container">
    <el-tabs>
      <el-tab-pane label="角色设置">
        <el-row type="flex" justify="end">
          <el-button type="primary" size="mini">新增</el-button>
        </el-row>
        <el-table
          v-loading="roleLoading"
          :data="roleList"
          border
          fit
          size="mini"
          stripe
          highlight-current-row
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
              <span>{{ scope.row.roleDesc }}</span>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            :label="$t('table.option')"
            width="120"
            header-align="center"
          >
            <template slot-scope="scope">
              <el-button
                v-if="scope.row.edit"
                type="success"
                size="small"
                icon="el-icon-circle-check-outline"
                @click="confirmEdit(scope.row)"
              >Ok</el-button>
              <el-button
                v-else
                type="primary"
                size="small"
                icon="el-icon-edit"
                @click="scope.row.edit=!scope.row.edit"
              >Edit</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="登录用户管理">
        <el-row type="flex" justify="end">
          <el-button type="primary" size="mini">新增</el-button>
        </el-row>
        <el-table
          v-loading="loginUserLoading"
          :data="loginUserList"
          border
          fit
          size="mini"
          stripe
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
          <el-table-column
            align="center"
            width="120"
            header-align="center"
            :label="$t('table.option')"
          >
            <template slot-scope="scope">
              <el-button
                v-if="scope.row.edit"
                type="success"
                size="small"
                icon="el-icon-circle-check-outline"
                @click="confirmEdit(scope.row)"
              >Ok</el-button>
              <el-button
                v-else
                type="primary"
                size="small"
                icon="el-icon-edit"
                @click="scope.row.edit=!scope.row.edit"
              >Edit</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-row type="flex" justify="end">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="100"
            layout="total, slot, prev, pager, next, jumper"
            :total="totalPage"
          ></el-pagination>
        </el-row>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      roleLoading: false,
      loginUserLoading: false,
      roleList: [],
      loginUserList: [],
      currentPage: 1,
      totalPage: 400
    };
  },
  mounted() {},
  methods: {}
};
</script>

<style rel="stylesheet/scss" lang="scss">
.permission-container {
  margin: 15px;
}
</style>

