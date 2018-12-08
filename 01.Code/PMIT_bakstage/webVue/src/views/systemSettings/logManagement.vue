<template>
  <div class="app-container">
    <div class="filter-container">
      <el-date-picker
        v-model="dateTime"
        size="mini"
        type="datetimerange"
        :picker-options="pickerOptions"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        align="center"
      ></el-date-picker>
      <el-button
        type="primary"
        size="mini"
        style="margin-left:10px"
        icon="el-icon-search"
        @click="handleFilter"
      >{{ $t("table.search") }}</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      border
      fit
      size="mini"
      stripe
      max-height="420"
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column
        header-align="center"
        align="center"
        :label="$t('table.id')"
        width="80"
        type="index"
      ></el-table-column>
      <el-table-column min-width="140" header-align="center" label="操作时间" prop="time">
        <template slot-scope="scope">
          <span>{{ scope.row.time | formatDate }}</span>
        </template>
      </el-table-column>
      <el-table-column width="120px" header-align="center" label="操作账号" prop="account"></el-table-column>
      <el-table-column width="110" header-align="center" label="IP地址" prop="ipaddress"></el-table-column>
      <el-table-column min-width="110" header-align="center" label="URL地址" prop="url"></el-table-column>
      <el-table-column min-width="150" header-align="center" label="参数" prop="param"></el-table-column>
      <el-table-column min-width="150" header-align="center" label="结果信息" prop="message"></el-table-column>
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
  data() {
    return {
      pickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            }
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            }
          }
        ]
      },
      dateTime: "",
      listLoading: false,
      list: [],
      initList: [],
      page: {
        pageNum: 1,
        pageSize: 10,
        totalRecord: 0
      }
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      let vm = this;
      vm.$store
        .dispatch("getLogList", {})
        .then(res => {
          if (res.success) {
            vm.initList = res.data;
            vm.list = vm.initList.slice(0, vm.page.pageSize);
            vm.page.totalRecord = res.data.length;
          } else {
            vm.list = [];
            vm.page.totalRecord = 0;
          }
          vm.listLoading = false;
        })
        .catch(() => {
          vm.list = [];
          vm.listLoading = false;
        });
    },
    handleFilter() {
      this.fetchData();
    },
    handleCurrentChange(val) {
      let vm = this;
      vm.page.currentPage = val;
      vm.list = vm.initList.slice(
        (val - 1) * vm.page.pageSize,
        val * vm.page.pageSize
      );
    }
  }
};
</script>

<style>
</style>
