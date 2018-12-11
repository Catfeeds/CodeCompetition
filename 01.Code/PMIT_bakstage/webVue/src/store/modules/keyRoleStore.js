import api from "@/api/teamSettingsApi";
const employeeSettings = {
  state: {
    searchForm: {
      poId: "",
      poOptions: [],
      keyRoleDataSource: [],
      keyRoleOptions: [],
      teamStaffOptions: []
    }
  },
  mutations: {
    updatePOData(state, value) {
      state.searchForm.poOptions = value;
      if (value && value[0]) {
        state.searchForm.poId = value[0].projectId;
      } else {
        state.searchForm.poId = "";
      }
    },
    updateKeyRoleTableData(state, value) {
      state.searchForm.keyRoleDataSource = value;
    },
    updateKeyRoleListData(state, value) {
      state.searchForm.keyRoleOptions = value;
    },
    updateTeamStaffData(state, value) {
      state.searchForm.teamStaffOptions = value;
    }
  },
  actions: {
    getPOList({ dispatch, commit }, param) {
      dispatch("getPrjectRelatedPO", param)
        .then(res => {
          if (res.success) {
            commit(
              "updatePOData",
              res.data.filter(item => item.knotTime === "未结项")
            );
            return dispatch("getKeyRoleTableData");
          } else {
            commit("updatePOData", []);
          }
        })
        .catch(() => {
          commit("updatePOData", []);
        });
    },
    getKeyRoleTableData({ commit, state }) {
      if (!state.searchForm.poId) {
        commit("updateKeyRoleTableData", []);
      }
      return new Promise(() => {
        api
          .getProjectKeyRoleInfo(state.searchForm.poId)
          .then(res => {
            if (res.data.success) {
              commit("updateKeyRoleTableData", res.data.data);
            } else {
              commit("updateKeyRoleTableData", []);
            }
          })
          .catch(() => {
            commit("updateKeyRoleTableData", []);
          });
      });
    },
    getKeyRoleList({ commit }) {
      return new Promise(() => {
        api
          .getProjectRole()
          .then(res => {
            if (res.data.success) {
              commit("updateKeyRoleListData", res.data.data);
            } else {
              commit("updateKeyRoleListData", []);
            }
          })
          .catch(() => {
            commit("updateKeyRoleListData", []);
          });
      });
    },
    getEmployeeByTeam({ commit }, teamId) {
      return new Promise(() => {
        api
          .getEmployeesByTeam(teamId)
          .then(res => {
            if (res.data.success) {
              commit("updateTeamStaffData", res.data.data);
            } else {
              commit("updateTeamStaffData", []);
            }
          })
          .catch(() => {
            commit("updateTeamStaffData", []);
          });
      });
    },
    saveProjectKeyRole(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .saveProjectRole(param)
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
export default employeeSettings;
