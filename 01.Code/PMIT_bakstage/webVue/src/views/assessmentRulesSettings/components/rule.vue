<template>
  <div>
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
        label="所属产品线"
        prop="product"
        sortable
      ></el-table-column>
      <el-table-column min-width="100px" header-align="center" label="所属角色" prop="role" sortable></el-table-column>
      <el-table-column min-width="100px" header-align="center" label="职级" prop="rank" sortable></el-table-column>
      <el-table-column min-width="100px" header-align="center" label="创建人" prop="creator" sortable></el-table-column>
      <el-table-column
        min-width="150px"
        header-align="center"
        label="最后更新人"
        prop="updator"
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
          <span>{{scope.row.updateTime | formatDate}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" width="130" header-align="center" :label="$t('table.option')">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            title="编辑"
            @click="handleEdit(scope.row)"
          ></el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-delete"
            title="删除"
            @click="handleDel(scope.row.ruleId)"
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
  </div>
</template>
<script>
import { formatDate } from "@/utils/date";
export default {
  filters: {
    formatDate(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }
  },
  props: ["condition"],
  data() {
    return {
      loading: false,
      tableData: [],
      initList: [],
      page: {
        pageNum: 1,
        pageSize: 10,
        totalRecord: 0
      }
    };
  },
  methods: {
    getRuleList() {
      let vm = this;
      vm.loading = true;
      vm.$store
        .dispatch("getRuleList", vm.condition)
        .then(res => {
          if (res) {
            vm.initList = res;
            vm.tableData = vm.initList.slice(0, vm.page.pageSize);
            vm.page.totalRecord = res.length;
          } else {
            vm.tableData = [];
            vm.page.totalRecord = 0;
          }
          vm.loading = false;
        })
        .catch(() => {
          vm.tableData = [];
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
    handleEdit(rowData) {},
    handleDel(ruleId) {},
    handleFilter() {
      this.page.currentPage = 1;
      this.getRuleList();
    },
    handleAdd() {}
  }
};
</script>

