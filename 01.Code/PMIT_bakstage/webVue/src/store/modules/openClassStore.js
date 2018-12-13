import api from "@/api/openClassApi";
import constant from "@/utils/constant";
import { formatDate } from "@/utils/date";
const openClass = {
  state: {
    searchForm: {
      seriesOptions: [],
      series: "",
      courseTypeOptions: constant.COURSE_TYPE,
      courseType: "",
      product: "",
      productOptions: [],
      trainName: "",
      trainTime: "",
      lecturer: ""
    },
    tableDataSource: [],
    startForm: {
      openingID: "",
      product: "",
      series: "",
      system: "",
      systemOptions: [],
      trainOptions: [],
      trainName: "",
      classSerices: "",
      courseType: "",
      lecturer: "",
      trainTime: "",
      languageOptions: [
        { value: 1, label: "中文" },
        { value: 0, label: "英文" }
      ],
      language: "",
      openClassType: "",
      openClassTypeOptions: constant.OPEN_CLASS_TYPE,
      startLocation: "",
      coursewareAddr: "",
      trainAddr: "",
      capacity: "",
      isExamOptions: [{ value: 1, label: "是" }, { value: 0, label: "否" }],
      isExam: "",
      publisher: "",
      telephone: "",
      regType: "",
      regTypeOptions: [
        { value: 1, label: "线上" },
        { value: 0, label: "线下" }
      ],
      regNote: ""
    }
  },
  mutations: {
    updateStartSeriesData(state, value) {
      state.searchForm.seriesOptions = value;
      state.searchForm.series = "";
    },
    updateStartProductData(state, value) {
      state.searchForm.productOptions = value;
      state.searchForm.product = "";
    },
    updateTableDataSourcee(state, value) {
      state.tableDataSource = value;
    },
    updateStartTrainListData(state, value) {
      state.startForm.trainOptions = value;
      state.startForm.trainName = "";
    },
    updateStartSystemData(state, value) {
      state.searchForm.systemOptions = value;
      state.startForm.system = "";
    }
  },
  actions: {
    getStartSeries({ dispatch, commit }) {
      return dispatch("querySeries")
        .then(res => {
          if (res.success) {
            commit("updateStartSeriesData", res.data);
          } else {
            commit("updateStartSeriesData", []);
          }
        })
        .catch(() => {
          commit("updateStartSeriesData", []);
        });
    },
    getStartSystem({ dispatch, commit }) {
      return dispatch("querySystem")
        .then(res => {
          if (res.success) {
            commit("updateStartSystemData", res.data);
          } else {
            commit("updateStartSystemData", []);
          }
        })
        .catch(() => {
          commit("updateStartSystemData", []);
        });
    },
    getStartProduct({ dispatch, commit }) {
      return dispatch("getProductInfo")
        .then(data => {
          commit("updateStartProductData", data);
        })
        .catch(() => {
          commit("updateStartProductData", []);
        });
    },
    getStartTrain({ dispatch, commit, state }) {
      let param = {
        bu: state.startForm.product,
        series: state.startForm.series,
        sort: state.startForm.system,
        trainingName: "",
        classType: state.startForm.courseType
      };
      return dispatch("getTrainingList", param)
        .then(res => {
          if (res.success) {
            commit("updateStartTrainListData", res.data);
          } else {
            commit("updateStartTrainListData", []);
          }
        })
        .catch(() => {
          commit("updateStartTrainListData", []);
        });
    },
    getStartTableData({ commit, state }, pageInfo) {
      return api
        .getOpenClassList({
          bu: state.searchForm.product,
          series: state.searchForm.series,
          trainingName: state.searchForm.trainName,
          trainingType: state.searchForm.courseType,
          lecturer: state.searchForm.lecturer,
          startTime: state.searchForm.trainTime
            ? formatDate(state.searchForm.trainTime[0], "yyyy-MM-dd HH:mm:ss")
            : "",
          endTime: state.searchForm.trainTime
            ? formatDate(state.searchForm.trainTime[1], "yyyy-MM-dd HH:mm:ss")
            : "",
          pageInfo: {
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
    addStartInfo({ state }) {
      return new Promise((resolve, reject) => {
        let param = {
          system: state.startForm.system,
          bu: state.startForm.product,
          series: state.startForm.series,
          trainingName: state.startForm.trainName,
          openingName: state.startForm.classSerices,
          lecturer: state.startForm.lecturer,
          trainingTime: state.startForm.trainTime
            ? formatDate(state.startForm.trainTime, "yyyy-MM-dd hh:mm")
            : "",
          trainingType: state.startForm.courseType,
          language: state.startForm.language,
          classType: state.startForm.openClassType,
          address: state.startForm.startLocation,
          download: state.startForm.coursewareAddr,
          online: state.startForm.trainAddr,
          galleryful: state.startForm.capacity,
          isExam: state.startForm.isExam,
          publisher: state.startForm.publisher,
          telephone: state.startForm.telephone,
          enrollMethod: state.startForm.regType,
          notice: state.startForm.regNote
        };
        api
          .addOpenClassInfo(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editStartInfo({ state }) {
      return new Promise((resolve, reject) => {
        let param = {
          openingID: state.searchForm.openingID,
          system: state.startForm.system,
          bu: state.startForm.product,
          series: state.startForm.series,
          trainingName: state.startForm.trainName,
          openingName: state.startForm.classSerices,
          lecturer: state.startForm.lecturer,
          trainingTime: state.startForm.trainTime
            ? formatDate(state.startForm.trainTime, "yyyy-MM-dd hh:mm")
            : "",
          trainingType: state.startForm.courseType,
          language: state.startForm.language,
          classType: state.startForm.openClassType,
          address: state.startForm.startLocation,
          download: state.startForm.coursewareAddr,
          online: state.startForm.trainAddr,
          galleryful: state.startForm.capacity,
          isExam: state.startForm.isExam,
          publisher: state.startForm.publisher,
          telephone: state.startForm.telephone,
          enrollMethod: state.startForm.regType,
          notice: state.startForm.regNote
        };
        api
          .editOpenClassInfo(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delStartInfo(arg, startId) {
      return new Promise((resolve, reject) => {
        api
          .delOpenClassInfo(startId)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    setStartInfo({ state }, data) {
      let isDetail = data.isDetail;
      state.startForm.system = data.system;
      state.startForm.product = data.bu;
      state.startForm.series = data.series;
      state.startForm.trainName = data.trainingName;
      state.startForm.classSerices = data.openingName;
      state.startForm.lecturer = data.lecturer;
      state.startForm.trainTime = data.trainingTime;
      state.startForm.courseType = data.trainingType;
      state.startForm.language = isDetail
        ? state.startForm.languageOptions.find(
            item => item.value === data.language
          ).label
        : data.language;
      state.startForm.openClassType = data.classType;
      state.startForm.startLocation = data.address;
      state.startForm.coursewareAddr = data.download;
      state.startForm.trainAddr = data.online;
      state.startForm.capacity = data.galleryful;
      state.startForm.isExam = isDetail
        ? state.startForm.isExamOptions.find(item => item.value === data.isExam)
            .label
        : data.isExam;
      state.startForm.publisher = data.publisher;
      state.startForm.telephone = data.telephone;
      state.startForm.regType = isDetail
        ? state.startForm.regTypeOptions.find(
            item => item.value === data.enrollMethod
          ).label
        : data.enrollMethod;
      state.startForm.regNote = data.notice;
    }
  }
};
export default openClass;
