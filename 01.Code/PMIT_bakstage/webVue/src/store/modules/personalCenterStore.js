import api from "@/api/personalCenterApi";

export default {
  state: {
    evaluateList: [],
    historyEvaluateList: []
  },
  mutations: {
    updateEvaluateList(state, value) {
      state.evaluateList = value;
    },
    updateHistoryEvaluateList(state, value) {
      state.historyEvaluateList = value;
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
    }
  }
};
