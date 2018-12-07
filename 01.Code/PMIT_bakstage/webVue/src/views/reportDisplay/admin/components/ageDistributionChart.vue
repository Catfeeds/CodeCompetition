<template>
  <div style="height:250px;">
    <div id="ageDistributioniChart" style="height:250px;"></div>
  </div>
</template>

<script>
import echarts from "echarts";
import mixinResizeChart from "../mixinResizeChart";
import { mapGetters, mapActions, mapState } from "vuex";

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
  computed: {
    ...mapGetters(["getParams"]),
    ...mapState({
      chartData: state => state.reportDisplayStore.ageStatisData
    })
  },
  methods: {
    initChart() {
      this.chart = echarts.init(
        document.getElementById("ageDistributioniChart")
      );
    },
    setOptions(obj) {
      var option = {
        title: {
          text: "年龄段分布",
          x: "center"
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
          bottom: 20,
          data: obj.legend //['25以下','25~30','30~40','40以上']
        },
        series: [
          {
            type: "pie",
            radius: "55%",
            center: ["40%", "60%"],
            label: {
              position: "outside",
              formatter: "{d}%"
            },
            labelLine: {
              normal: {
                smooth: 0.2,
                length: 7,
                length2: 7
              }
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
