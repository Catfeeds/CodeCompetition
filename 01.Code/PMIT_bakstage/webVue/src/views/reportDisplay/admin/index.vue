<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label>
        <el-select
          v-model="product"
          size="mini"
          clearable
          placeholder="产品线"
          @change="productChange"
        >
          <el-option v-for="item in productOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label>
        <el-select v-model="area" size="mini" placeholder="区域" clearable @change="areaChange">
          <el-option v-for="item in areaOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label>
        <el-select v-model="pdu" size="mini" placeholder="交付部" clearable>
          <el-option v-for="item in pduOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          size="mini"
          @click="handleFilter"
          icon="el-icon-search"
        >{{ $t("table.search") }}</el-button>
      </el-form-item>
    </el-form>
    <el-row>
      <el-col :span="6">
        <rate-chart></rate-chart>
      </el-col>
      <el-col :span="18">
        <el-row>
          <el-col :span="12">
            <department-chart></department-chart>
          </el-col>
          <el-col :span="12">
            <post-chart></post-chart>
          </el-col>
        </el-row>
        <el-row style="margin-top:15px">
          <el-col :span="12">
            <training-chart></training-chart>
          </el-col>
          <el-col :span="12">
            <age-chart></age-chart>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <eTable></eTable>
  </div>
</template>

<script>
import { mapMutations, mapActions, mapState, mapGetters } from "vuex";
import departmentChart from "./components/departmentChart";
import postChart from "./components/postChart";
import rateChart from "./components/rateChart";
import trainingChart from "./components/traniningStatistictChart";
import ageChart from "./components/ageDistributionChart";
import eTable from "./components/employeeTable";

export default {
  name: "DashboardAdmin",
  components: {
    departmentChart,
    postChart,
    rateChart,
    trainingChart,
    ageChart,
    eTable
  },
  data() {
    return {};
  },
  mounted() {
    this.getRDProductInfo();
  },
  computed: {
    ...mapGetters(["getParams"]),
    ...mapState({
      productOptions: state => state.reportDisplayStore.productData,
      areaOptions: state => state.reportDisplayStore.areaData,
      pduOptions: state => state.reportDisplayStore.pduData
    }),
    product: {
      get() {
        return this.$store.state.reportDisplayStore.selectedProduct;
      },
      set(val) {
        this.$store.commit("setRDSelectedProduct", val);
      }
    },
    area: {
      get() {
        return this.$store.state.reportDisplayStore.selectedArea;
      },
      set(val) {
        this.$store.commit("setRDSelectedArea", val);
      }
    },
    pdu: {
      get() {
        return this.$store.state.reportDisplayStore.selectedPDU;
      },
      set(val) {
        this.$store.commit("setRDSelectedPDU", val);
      }
    }
  },
  methods: {
    ...mapActions([
      "getRDProductInfo",
      "getRDAreas",
      "getRDPDUList",
      "getRate"
    ]),
    productChange() {
      this.$store.commit("setRDAreaData", []);
      this.$store.commit("setRDPDUData", []);
      if (this.product) {
        this.getRDAreas(this.product);
      }
    },
    areaChange() {
      this.$store.commit("setRDPDUData", []);
      if (this.area) {
        this.getRDPDUList(this.area);
      }
    },
    handleFilter() {
      this.getRate(this.getParams());
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.dashboard-editor-container {
  padding: 20px 32px;
  background-color: rgb(240, 242, 245);
  height: 100%;
  box-sizing: border-box;
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
.chart-operate {
  position: absolute;
  display: inline-block;
  right: 5px;
  top: 5px;
  z-index: 22;
}
.chart-operate i {
  font-size: 22px;
  cursor: pointer;
}
.chart-operate i:hover {
  color: "#409EFF";
}
</style>
