export default {
  data() {
    return {
      levelData: [],
      gridIndex: 0
    };
  },
  methods: {
    onDrillUp() {
      if (this.levelData.length > 0) {
        this.levelData.pop();
        this.searchChartData();
      }
    },
    onDrillDown() {
      if (this.chartData.length > 0) {
        var index = this.chart.getOption().xAxis[0].axisPointer.value || 0;
        this.levelData.push(this.chartData[index].name);
        this.searchChartData();
      }
    },
    splitStrDesc(str) {
      var i = str.length,
        code,
        len = 0,
        arr = [];
      for (; i > 0; i--) {
        code = str.charCodeAt(i);
        if (code == 10) {
          //回车换行问题
          len += 2;
        } else if (code < 0x007f) {
          len += 1;
        } else if (code >= 0x0080 && code <= 0x07ff) {
          len += 2;
        } else if (code >= 0x0800 && code <= 0xffff) {
          len += 3;
        }
        arr.push(str[i]);
        if (len > 9) {
          if (i > 1) {
            arr.push("...");
          }
          break;
        }
      }
      return arr.reverse().join("");
    },
    splitStrAsc(str) {
      var i = 0,
        code,
        len = 0,
        arr = [];
      for (; i < str.length; i++) {
        code = str.charCodeAt(i);
        if (code == 10) {
          //回车换行问题
          len += 2;
        } else if (code < 0x007f) {
          len += 1;
        } else if (code >= 0x0080 && code <= 0x07ff) {
          len += 2;
        } else if (code >= 0x0800 && code <= 0xffff) {
          len += 3;
        }
        arr.push(str[i]);
        if (len > 9) {
          if (i < str.length - 1) {
            arr.push("...");
          }
          break;
        }
      }
      return arr.join("");
    }
  }
};
