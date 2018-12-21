import api from "@/api/ruleApi";
import _ from "underscore";
export default {
  state: {
    roleTreeData: [],
    systemOptions: [],
    seriesOptions: [],
    trainDataSource: [],
    affairDataSource: [],
    roleOptions: []
  },
  mutations: {
    updateRoleData(state, value) {
      state.roleTreeData = _.chain(value)
        .pluck("system")
        .uniq()
        .map(function(item, index) {
          return {
            id: index + 1,
            name: item,
            children: value
              .filter(node => node.system === item)
              .map(node => {
                return {
                  id: node.roleId,
                  name: node.roleName
                };
              })
          };
        })
        .value();
    },
    updateSystemData(state, value) {
      state.systemOptions = value;
    },
    updateSeriesData(state, value) {
      state.seriesOptions = value;
    },
    updateTrainData(state, value) {
      state.trainDataSource = value;
    },
    updateAffairData(state, value) {
      state.affairDataSource = value;
    },
    updateRoleOption(state, value) {
      state.roleOptions = value;
    }
  },
  actions: {
    getRoleTreeInfo({ commit }) {
      return new Promise(() => {
        api
          .getRoleList({ system: "", roleName: "" })
          .then(res => {
            if (res.data.success) {
              commit("updateRoleData", res.data.data);
            } else {
              commit("updateRoleData", []);
            }
          })
          .catch(() => {
            commit("updateRoleData", []);
          });
      });
    },
    getRoleOption({ commit }) {
      return api
        .queryRoleList()
        .then(res => {
          if (res.data.success) {
            commit("updateRoleOption", res.data.data);
          } else {
            commit("updateRoleOption", []);
          }
        })
        .catch(() => {
          commit("updateRoleOption", []);
        });
    },
    getSystemInfo({ dispatch, commit }) {
      return dispatch("querySystem")
        .then(res => {
          if (res.success) {
            commit("updateSystemData", res.data);
          } else {
            commit("updateSystemData", []);
          }
        })
        .catch(() => {
          commit("updateSystemData", []);
        });
    },
    getSeriesInfo({ dispatch, commit }) {
      return dispatch("querySeries")
        .then(res => {
          if (res.success) {
            commit("updateSeriesData", res.data);
          } else {
            commit("updateSeriesData", []);
          }
        })
        .catch(() => {
          commit("updateSeriesData", []);
        });
    },
    getAllTrain({ dispatch, commit }, condition) {
      return dispatch("getTrainingList", condition)
        .then(res => {
          if (res.success) {
            commit("updateTrainData", res.data);
          } else {
            commit("updateTrainData", []);
          }
        })
        .catch(() => {
          commit("updateTrainData", []);
        });
    },
    getAllAffair({ dispatch, commit }, condition) {
      return dispatch("getAffairsList", condition)
        .then(res => {
          if (res.success) {
            commit("updateAffairData", res.data);
          } else {
            commit("updateAffairData", []);
          }
        })
        .catch(() => {
          commit("updateAffairData", []);
        });
    },
    getRoleInfo(commit, condition) {
      return new Promise((resolve, reject) => {
        api
          .getRoleList(condition)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    saveRoleInfo(commit, roleInfo) {
      return new Promise((resolve, reject) => {
        api
          .saveRoleInfo(roleInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delRoleInfo(commit, roleId) {
      return new Promise((resolve, reject) => {
        api
          .delRoleInfo(roleId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getRuleList(commit, condition) {
      return new Promise((resolve, reject) => {
        api
          .getRuleList(condition)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    addRuleInfo(commit, ruleInfo) {
      return new Promise((resolve, reject) => {
        api
          .addRuleInfo(ruleInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getRuleInfoById(commit, ruleId) {
      return new Promise((resolve, reject) => {
        api
          .getRuleInfoById(ruleId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delRuleInfo(commit, ruleId) {
      return new Promise((resolve, reject) => {
        api
          .delRuleInfo(ruleId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    }
  }
};
