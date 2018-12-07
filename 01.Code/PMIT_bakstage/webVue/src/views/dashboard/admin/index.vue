<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label="">
        <el-select
          v-model="product"
          size="mini"
          placeholder="产品线"
          @change="productChange"
        >
          <el-option
            v-for="item in productOptions"
            :key="item"
            :label="item"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="">
        <el-select
          v-model="area"
          size="mini"
          placeholder="区域"
          @change="areaChange"
        >
          <el-option
            v-for="item in areaOptions"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="">
        <el-select v-model="area" size="mini" placeholder="交付部">
          <el-option
            v-for="item in pduOptions"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <el-row>
      <el-col :span="6"> <rate-chart></rate-chart> </el-col>
      <el-col :span="18">
        <el-row>
          <el-col :span="12"> <department-chart></department-chart> </el-col>
          <el-col :span="12"> <post-chart></post-chart> </el-col>
        </el-row>
        <el-row style="margin-top:15px">
          <el-col :span="12"> <training-chart></training-chart> </el-col>
          <el-col :span="12"> <age-chart></age-chart> </el-col>
        </el-row>
      </el-col>
    </el-row>
    <eTable></eTable>
  </div>
</template>

<script>
import { mapMutations, mapActions, mapState } from "vuex";
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
      "getStatisticsChartData"
    ]),
    productChange() {
      this.$store.commit("setRDAreaData", []);
      this.getRDAreas(this.product);
    },
    areaChange() {
      this.$store.commit("setRDPDUData", []);
      this.getRDPDUList(this.area);
    },
    onSubmit() {
      this.getStatisticsChartData();
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
