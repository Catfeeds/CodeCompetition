<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label>
        <el-select v-model="product" size="mini" clearable placeholder="产品线" @change="productChange">
          <el-option v-for="item in productOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label>
        <el-select v-model="area" size="mini" clearable placeholder="区域" @change="areaChange">
          <el-option v-for="item in areaOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label>
        <el-select v-model="pdu" size="mini" clearable placeholder="交付部">
          <el-option v-for="item in pduOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          size="mini"
          icon="el-icon-search"
          @click="handleFilter"
        >{{ $t("table.search") }}</el-button>
      </el-form-item>
      <el-form-item>
        <el-button
          class="filter-item"
          style="margin-left: 8px;"
          type="primary"
          size="mini"
          icon="el-icon-download"
          @click="handleExport"
        >{{ $t("button.export") }}</el-button>
      </el-form-item>
      <el-form-item>
        <el-upload
          class="filter-item"
          action="system/exceloperation/importScore"
          :before-upload="handleUpload"
          :on-success="handleSuccess"
          :on-error="handleError"
          :show-file-list="false"
          accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
        >
          <el-button
            size="mini"
            style="margin-left: 8px;"
            icon="el-icon-upload2"
            type="primary"
          >{{ $t("button.import") }}</el-button>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button
          class="filter-item"
          style="margin-left: 8px;"
          type="primary"
          size="mini"
          icon="el-icon-download"
          @click="handleTemplate"
        >{{ $t("button.template") }}</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" style="width: 100%"
     border  fit   size="mini" stripe 
      highlight-current-row max-height="350" :cell-class-name="cellClassFn">
      <el-table-column fixed type="index" width="60"></el-table-column>
      <el-table-column label="编号" min-width="80" prop="employeeID" width="60"></el-table-column>
      <el-table-column label="姓名" min-width="80" prop="employeeName" width="60"></el-table-column>
      <el-table-column
        v-for="(pItem, index) in headers"
        :key="index"
        :label="pItem.name"
        :prop="pItem.courses && pItem.courses.length ===1? pItem.courses[0].courseID :pItem.id"
        header-align="center"
        align="center"
        width="100"
      >
        <el-table-column
          v-if="pItem.courses && pItem.courses.length > 1"
            label="总分"
            header-align="center"
            align="center"
            :formatter="calcTotalScore"
          >
          </el-table-column>
        
        <el-table-column
          v-if="pItem.courses && pItem.courses.length > 1"
          v-for="item in pItem.courses"
          :key="item.courseID"
          :label="item.courseName"
          :prop="item.courseID"
          header-align="center"
          align="center"
        ></el-table-column>
      </el-table-column>
    </el-table>
    <el-row type="flex" justify="end">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleSizeChange"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="total, slot, prev, pager, next"
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
  mounted() {
    this.getSDProductInfo();
    this.handleFilter();
  },
  data() {
    return {
      headers: [],
      tableData: [],
      currentPage: 1,
      pageSize: 100,
      total: 0
    };
  },

  computed: {
    ...mapGetters(["getSDParams"]),
    ...mapState({
      productOptions: state => state.scoreDisplayStore.productData,
      areaOptions: state => state.scoreDisplayStore.areaData,
      pduOptions: state => state.scoreDisplayStore.pduData,
      dataSource: state => state.scoreDisplayStore.scoreData
    }),
    product: {
      get() {
        return this.$store.state.scoreDisplayStore.selectedProduct;
      },
      set(val) {
        this.$store.commit("setSDSelectedProduct", val);
      }
    },
    area: {
      get() {
        return this.$store.state.scoreDisplayStore.selectedArea;
      },
      set(val) {
        this.$store.commit("setSDSelectedArea", val);
      }
    },
    pdu: {
      get() {
        return this.$store.state.scoreDisplayStore.selectedPDU;
      },
      set(val) {
        this.$store.commit("setSDSelectedPDU", val);
      }
    }
  },
  watch: {
    dataSource(data) {
      this.tableData = data.allCourseScoreList;
      this.headers = data.columnNameMap;
      this.total= data.size;
    }
  },
  methods: {
    ...mapActions(["getSDProductInfo", "getSDAreas", "getSDPDUList"]),
    productChange() {
      this.$store.commit("setSDAreaData", []);
      this.getSDAreas(this.product);
    },
    areaChange() {
      this.$store.commit("setSDPDUData", []);
      this.getSDPDUList(this.area);
    },
    handleFilter(arg,curPage) {
      this.currentPage =curPage || 1;
      var params = this.getSDParams();
      // params.currPage = this.currentPage;
      // params.pageSize=this.pageSize;
      this.$store.dispatch("getTrainingScore1", params);
    },
    handleSizeChange() {
      this.handleFilter(null,this.currentPage);
    },
    cellClassFn(obj) {
      return obj.row[obj.column.property] == 0 ? "cell-zero" : "";
    },
    handleExport() {
      this.$message.info("功能正在完善中。。。");
    },
    handleTemplate() {
      this.$message.info("功能正在完善中。。。");
    },
    handleUpload(file) {
      var ext = file.name.substring(file.name.lastIndexOf(".") + 1);
      const extension = ext === "xls" || ext === "xlsx";
      const isLt2M = file.size / 1024 / 1024 < 10;
      if (!extension) {
        this.$message({
          message: "上传文件只能是 xls、xlsx格式!",
          type: "warning"
        });
      }
      if (!isLt2M) {
        this.$message({
          message: "上传文件大小不能超过 10MB!",
          type: "warning"
        });
      }
      return extension || (extension2 && isLt2M);
    },
    handleSuccess() {
      this.$message.success("文件导入成功");
      this.page.currentPage = 1;
      this.getEmployeeList();
    },
    handleError(){
      this.$message.error("文件导入失败,请检查文件格式是否合法");
    },
    calcTotalScore(arg){
      console.log(arg);
    }
  }
};
</script>

<style>
.cell-zero div {
  background-color: red;
  color: #fff;
}
</style>
