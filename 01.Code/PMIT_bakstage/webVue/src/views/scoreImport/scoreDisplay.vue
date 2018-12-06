<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label>
        <el-select v-model="product" size="mini" placeholder="产品线" @change="productChange">
          <el-option v-for="item in productOptions" :key="item" :label="item" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label>
        <el-select v-model="area" size="mini" placeholder="区域" @change="areaChange">
          <el-option v-for="item in areaOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label>
        <el-select v-model="area" size="mini" placeholder="交付部">
          <el-option v-for="item in pduOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" style="width: 100%" max-height="350"
      :cell-class-name="cellClassFn">
      <el-table-column fixed type="index" width="60"></el-table-column>
      <el-table-column
        v-for="(pItem,index) in headers"
        :key="index"
        :label="pItem.name"
        :prop="pItem.id"
        header-align="center"
        align="center"
        width="100"
      >
        <el-table-column v-if="pItem.courses && pItem.courses.length>0"
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
        :page-size="100"
        layout="total, slot, prev, pager, next"
        :total="total"
        prev-text="上一页"
        next-text="下一页">
      </el-pagination>
    </el-row>
  </div>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";

export default {
  mounted() {
    this.getSDProductInfo();
    this.onSubmit();
  },
  data() {
    return {
      headers: [],
      tableData: [],
      currentPage:1,
      pageSize:1,
      total:100,
    };
  },

  computed: {
    ...mapGetters(['getSDParams']),
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
      this.currentPage = 1;
      this.total = data.datas.length;
      this.headers = data.trains;
      this.handleSizeChange();
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
    onSubmit() {
      var params = this.getSDParams();
      this.$store.dispatch("getTrainingScore1", params);
    },
    handleSizeChange() {
      this.tableData = this.dataSource.datas.slice(
        (this.currentPage - 1) * 100,
        this.currentPage * 100 + 1
      );
    },
    cellClassFn(obj){
      return obj.row[obj.column.property]==0?'cell-zero':'';
    }
  }
};
</script>

<style>
.cell-zero div{
  background-color: red;
  color: #FFF;
}
</style>
