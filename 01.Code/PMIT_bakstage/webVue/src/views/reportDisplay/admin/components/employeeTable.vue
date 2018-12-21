<template>
  <div>
    <el-form :inline="true">
      <el-form-item label>
        <el-input v-model="eName" size="mini" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label>
        <el-input v-model="eNumber" size="mini" placeholder="请输入工号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          size="mini"
          @click="handleFilterEmployee"
          icon="el-icon-search"
        >{{ $t("table.search") }}</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      style="width: 100%"
      max-height="250"
      border
      fit
      size="mini"
      stripe
      highlight-current-row
      :cell-class-name="cellClassFn"
    >
      <el-table-column fixed type="index" header-align="center" align="center" width="60" :label="$t('table.id')"></el-table-column>
      <el-table-column fixed prop="staffID" header-align="center" label="工号" width="80"></el-table-column>
      <el-table-column fixed prop="staffName" header-align="center" label="姓名" width="100"></el-table-column>
      <el-table-column prop="costCenter" header-align="center" label="成本中心" width="150"></el-table-column>
      <el-table-column prop="sex" header-align="center" label="性别" width="80"></el-table-column>
      <el-table-column prop="hwLevel" header-align="center" label="华为职位" width="120"></el-table-column>
      <el-table-column prop="istLevel" header-align="center" label="软通职位" width="120"></el-table-column>
      <el-table-column prop="cooperationMode" header-align="center" label="合作模式" width="120"></el-table-column>
      <el-table-column prop="positionRole" header-align="center" label="岗位" width="120"></el-table-column>
      <el-table-column prop="employmentDate" header-align="center" label="司龄（月）" width="120"></el-table-column>
      <el-table-column prop="graduationTime" header-align="center" label="工龄（年）" width="120"></el-table-column>
      <el-table-column prop="education" header-align="center" label="学历" width="120"></el-table-column>
      <el-table-column prop="if211" header-align="center" label="是否211" width="80"></el-table-column>
      <el-table-column prop="halfYearAchievements" header-align="center" label="近半年绩效" width="100"></el-table-column>
      <el-table-column prop="currentCredit" header-align="center" label="目前学分" width="80"></el-table-column>
      <el-table-column prop="trainTime" header-align="center" label="培训时间" width="100"></el-table-column>
      <el-table-column
        v-for="(pItem, index) in headers"
        :key="index"
        :label="pItem.name"
        header-align="center"
        align="center"
      >
        <el-table-column
          v-for="item in pItem.courses"
          :prop="item.courseID"
          :key="item.courseID"
          :label="item.courseName"
          width="100"
          header-align="center"
          align="center"
        ></el-table-column>
      </el-table-column>
      <el-table-column prop="totleScore" label="实际总分（40学分）" width="100"></el-table-column>
    </el-table>
    <el-row type="flex" justify="end">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleSizeChange"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, slot,prev, pager, next"
        :total="total"
        prev-text="上一页"
        next-text="下一页"
      ></el-pagination>
    </el-row>
  </div>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";
export default {
  name: "etable",
  data() {
    return {
      eName: "",
      eNumber: "",
      currentPage: 1,
      pageSize: 100,
      total: 0,
      tableData: [],
      headers: []
    };
  },
  computed: {
    ...mapGetters(["getParams"]),
    ...mapState({
      dataSource: state => state.reportDisplayStore.employeeData
    })
  },
  watch: {
    dataSource(data) {
      this.total = data.totleSize || 0;
      this.tableData = data.datas || [];
      this.headers = data.trains || [];
    }
  },
  methods: {
    handleFilterEmployee(arg, curPage) {
      console.log("aaa");
      this.currentPage = curPage || 1;
      var params = this.getParams();
      params.staffID = this.eNumber;
      params.staffName = this.eName;
      params.pageNo = this.currentPage;
      params.pageSize = this.pageSize;
      this.$store.dispatch("getRDEmployee", params);
    },
    handleSizeChange() {
      this.handleFilterEmployee(null, this.currentPage);
    },
    cellClassFn(obj) {
      return obj.row[obj.column.property] == 0 ? "cell-zero" : "";
    }
  }
};
</script>
