<template>
  <div style="height:250px;position:relative;">
    <!-- <div class="chart-operate">
      <i
        class="el-icon-arrow-up"
        v-if="levelData.length > 0"
        @click="onDrillUp"
        title="展示上一级"
      ></i>
      <i class="el-icon-arrow-down" @click="onDrillDown" title="展示下一级"></i>
    </div> -->
    <div id="statistictChart" style="height:250px;"></div>
  </div>
</template>

<script>
import echarts from "echarts";
import mixinsChart from "../mixinChart";
import mixinResizeChart from "../mixinResizeChart";
import { mapGetters, mapActions, mapState } from "vuex";

export default {
  props: ["data"],
  mixins: [mixinsChart, mixinResizeChart],
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
        xData: [],
        yData: []
      };
      for (var i = 0; i < data.length; i++) {
        obj.xData.push(data[i].name);
        obj.yData.push(data[i].value);
      }
      this.setOptions(obj);
    }
  },
  computed: {
    ...mapGetters(["getParams"]),
    ...mapState({
      chartData: state => state.reportDisplayStore.trainingStatisData
    })
  },
  methods: {
    ...mapActions(["getStatisByTranining"]),
    searchChartData() {
      var obj = this.getParams();
      // obj.id = this.levelData[this.levelData.length-1];
      this.getStatisByTranining(obj);
    },
    initChart() {
      this.chart = echarts.init(document.getElementById("statistictChart"));
    },
    setOptions(obj) {
      var vm = this;
      var option = {
        title: {
          show: true,
          text: "培训情况",
          left: "center"
        },
        grid: {
          top: 40
        },
        tooltip: {
          // triggerOn: "click",
          show: true,
          formatter: "{b0}: {c0}%"
        },
        dataZoom: [{
            type: 'inside',
            start: 0,
            end: Math.ceil((7/(obj.xData.length||1))*100)
        }],
        xAxis: {
          type: "category",
          data: obj.xData, //['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
          axisLabel: {
            interval: 0,
            rotate: 50,
            formatter(val) {
              return vm.splitStrAsc(val || "");
            }
          },
          axisLine: {
            show: false
          },
          splitLine: {
            show: false
          },
          axisTick: {
            show: false
          }
          // axisPointer: {
          //   value: this.gridIndex,
          //   lineStyle: {
          //     color: "#037BF8",
          //     opacity: 0.5,
          //     width: 2
          //   },
          //   label: {
          //     show: false,
          //     backgroundColor: "#037BF8"
          //   },
          //   handle: {
          //     show: true,
          //     color: "#037BF8",
          //     size: 25,
          //     margin: 0
          //   }
          // }
        },
        yAxis: {
          type: "value",
          axisTick: {
            show: false
          },
          axisLabel: {
            formatter: "{value}%"
          },
          axisLine: {
            show: false
          },
          splitLine: {
            lineStyle: {
              type: "dotted"
            }
          }
        },
        series: [
          {
            data: obj.yData, //[120, 200, 150, 80, 70, 110, 130],
            type: "bar",
            symbol: "rect",
            symbolSize: [50, 20],
            hoverAnimation: false,
            barMaxWidth:30,
            label: {
              show: true,
              position: "top",
              formatter: "{c}%"
            },
            itemStyle: {
              normal: {
                color: "#409EFF"
              }
            }
          }
        ]
      };
      this.chart.setOption(option);
    }
  }
};
</script>

<style></style>
