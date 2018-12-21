import api from "@/api/personalCenterApi";

export default {
  state: {
    evaluateList: [],
    historyEvaluateList: [],
    evaluateCount: 0
  },
  mutations: {
    updateEvaluateList(state, value) {
      state.evaluateList = value;
    },
    updateHistoryEvaluateList(state, value) {
      state.historyEvaluateList = value;
    },
    updateEvaluateCount(state, value) {
      state.evaluateCount = value || 0;
    }
  },
  actions: {
    getEvaluateListData({ commit }, employeeId) {
      return api
        .getEvaluateList(employeeId)
        .then(res => {
          if (res.data.success) {
            commit("updateEvaluateList", res.data.data);
          } else {
            commit("updateEvaluateList", []);
          }
        })
        .catch(() => {
          commit("updateEvaluateList", []);
        });
    },
    getHistoryEvaluateList({ commit }, employeeId) {
      return api
        .getHistoryEvaluateList(employeeId)
        .then(res => {
          if (res.data.success) {
            commit("updateHistoryEvaluateList", res.data.data);
          } else {
            commit("updateHistoryEvaluateList", []);
          }
        })
        .catch(() => {
          commit("updateHistoryEvaluateList", []);
        });
    },
    saveEvaluateInfo(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .saveEvaluation(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getDimenssionList(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .getDimensionListByAffairId(param.affairId, param.employeeId)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getEvaluateCount({ commit }, employeeId) {
      return api
        .queryEvaluateCount(employeeId)
        .then(res => {
          if (res.data.success) {
            commit("updateEvaluateCount", res.data.data.count);
          } else {
            commit("updateEvaluateCount", 0);
          }
        })
        .catch(() => {
          commit("updateEvaluateCount", 0);
        });
    }
  }
};
