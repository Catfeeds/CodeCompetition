import api from "@/api/scoreDisplayApi";
import constant from "@/utils/constant";
import { formatDate } from "@/utils/date";
console.log(api);
let state = {
  searchForm: {
    series: "",
    seriesOptions: [],
    property: "",
    propertypeOptions: constant.SCORE_PROPERTY,
    product: "",
    productOptions: [],
    trainName: "",
    scoreTime: ""
  },
  scoreData: []
};

let mutations = {
  updateScoreSeriesData(state, value) {
    state.searchForm.seriesOptions = value;
    state.searchForm.series = "";
  },
  updateScoreProductData(state, value) {
    state.searchForm.productOptions = value;
    state.searchForm.product = "";
  },
  updateTableDataSourcee(state, value) {
    state.scoreData = value;
  }
};

let actions = {
  getScoreProduct({ dispatch, commit }) {
    return dispatch("getProductInfo")
      .then(data => {
        commit("updateScoreProductData", data);
      })
      .catch(() => {
        commit("updateScoreProductData", []);
      });
  },
  getScoreSeries({ dispatch, commit }) {
    return dispatch("querySeries")
      .then(res => {
        if (res.success) {
          commit("updateScoreSeriesData", res.data);
        } else {
          commit("updateScoreSeriesData", []);
        }
      })
      .catch(() => {
        commit("updateStartSeriesData", []);
      });
  },
  getScoreDataList({ commit, state }, pageInfo) {
    return api
      .getScoreList({
        bu: state.searchForm.product,
        series: state.searchForm.series,
        affairName: state.searchForm.trainName,
        types: state.searchForm.property,
        startTime: state.searchForm.scoreTime
          ? formatDate(state.searchForm.scoreTime[0], "yyyy-MM-dd HH:mm:ss")
          : "",
        endTime: state.searchForm.scoreTime
          ? formatDate(state.searchForm.scoreTime[1], "yyyy-MM-dd HH:mm:ss")
          : "",
        pageParam: {
          currPage: pageInfo ? pageInfo.currentPage : 1,
          pageSize: pageInfo ? pageInfo.pageSize : 100,
          sortColumn: pageInfo ? pageInfo.sortColumn : "",
          sortType: pageInfo ? pageInfo.sortType : ""
        }
      })
      .then(res => {
        if (res.data.success) {
          commit("updateTableDataSourcee", res.data.data);
        } else {
          commit("updateTableDataSourcee", { list: [], total: 0 });
        }
      });
  },
  delScoreInfo(arg, param) {
    return new Promise((resolve, reject) => {
      api
        .delScoreInfo(param)
        .then(res => {
          resolve(res.data);
        })
        .catch(error => {
          reject(error);
        });
    });
  },
  editScoreInfo(arg, param) {
    return new Promise((resolve, reject) => {
      api
        .editScoreInfo(param)
        .then(res => {
          resolve(res.data);
        })
        .catch(error => {
          reject(error);
        });
    });
  },
  getScoreInfoById(arg, param) {
    return new Promise((resolve, reject) => {
      api
        .getScoreList(param)
        .then(res => {
          resolve(res.data);
        })
        .catch(error => {
          reject(error);
        });
    });
  }
};
export default {
  state,
  mutations,
  actions
};
