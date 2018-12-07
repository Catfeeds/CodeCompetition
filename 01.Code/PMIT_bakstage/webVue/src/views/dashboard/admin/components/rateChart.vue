<template>
  <div style="height:450px;position:relative;">
    <div id="rateChart" style="height:450px;"></div>
  </div>
</template>

<script>
import echarts from "echarts";
import mixinResizeChart from "../mixinResizeChart";
import { mapGetters, mapActions, mapState, mapMutations } from "vuex";

export default {
  props: ["data"],
  mixins: [mixinResizeChart],
  data() {
    return {
      chart: null
    };
  },
  mounted() {
    this.initChart();
    var obj = this.getParams(),
      vm = this;
    delete obj.isBacknone;
    this.getRate(obj);
    this.chart.on("click", function(params) {
      vm.setSelectedEType(params.dataIndex);
      vm.getStatisticsChartData(params.dataIndex);
    });
  },
  computed: {
    ...mapGetters(["getParams"]),
    ...mapState({
      chartData: state => state.reportDisplayStore.rateData
    })
  },
  watch: {
    chartData(data) {
      var obj = {
        legend: [],
        data: data || []
      };
      for (var i = 0; i < data.length; i++) {
        obj.legend.push(data[i].name);
      }
      this.setOptions(obj);
    }
  },
  methods: {
    ...mapActions(["getRate", "getStatisticsChartData"]),
    ...mapMutations(["setSelectedEType"]),
    initChart() {
      this.chart = echarts.init(document.getElementById("rateChart"));
    },
    setOptions(obj) {
      var option = {
        title: {
          text: "骨干与后备人数占比",
          x: "left"
        },
        grid: {
          top: 0,
          left: 0,
          bottom: 0,
          right: 0
        },
        legend: {
          orient: "vertical",
          right: 20,
          bottom: 0,
          data: obj.legend
        },
        series: [
          {
            type: "pie",
            radius: "60%",
            center: ["35%", "60%"],
            selectedOffset: 10,
            label: {
              position: "inside",
              formatter: "{c}"
            },
            data: obj.data
          }
        ]
      };

      this.chart.setOption(option);
    }
  }
};
</script>

<style></style>
