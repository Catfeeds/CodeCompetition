import api from "@/api/projectGroupApi";
const projectGroup = {
  state: {
    searchForm: {
      bd: "",
      bu: "",
      cu: "",
      teamName: "",
      pm: "",
      bdOptions: [],
      buOptions: [],
      cuOptions: []
    },
    dataSource: [],
    newForm: {
      bd: "",
      bu: "",
      cu: "",
      teamName: "",
      employeeName: "",
      employeeId: "",
      buOptions: [],
      cuOptions: [],
      employeeOptions: []
    }
  },
  mutations: {
    updateBDData(state, value) {
      state.searchForm.bdOptions = value || [];
      state.searchForm.bd = "";
    },
    updateBUData(state, value) {
      state.searchForm.buOptions = value || [];
      state.searchForm.bu = "";
    },
    updateCUData(state, value) {
      state.searchForm.cuOptions = value || [];
      state.searchForm.cu = "";
    },
    updateNewFormBUData(state, value) {
      state.newForm.buOptions = value || [];
      state.newForm.bu = "";
    },
    updateNewFormCUData(state, value) {
      state.newForm.cuOptions = value || [];
      state.newForm.cu = "";
    },
    updateDataSource(state, value) {
      state.dataSource = value || [];
    },
    updateEmployeeData(state, value) {
      state.newForm.employeeOptions = value;
      state.newForm.employeeId = "";
    }
  },
  actions: {
    getDBInfo({ commit }) {
      return api
        .queryProjectLevel({})
        .then(res => {
          if (res.data.success) {
            commit("updateBDData", res.data.data);
          } else {
            commit("updateBDData", []);
          }
        })
        .catch(() => {
          commit("updateBDData", []);
        });
    },
    getBUInfo({ state, commit }) {
      if (!state.searchForm.bd) {
        commit("updateBUData", []);
        commit("updateCUData", []);
        return;
      }
      return api
        .queryProjectLevel({ BD: state.searchForm.bd })
        .then(res => {
          if (res.data.success) {
            commit("updateBUData", res.data.data);
          } else {
            commit("updateBUData", []);
          }
        })
        .catch(() => {
          commit("updateBUData", []);
        });
    },
    getCUInfo({ commit, state }) {
      if (!state.searchForm.bu) {
        commit("updateCUData", []);
        return;
      }
      return api
        .queryProjectLevel({ BU: state.searchForm.bu })
        .then(res => {
          if (res.data.success) {
            commit("updateCUData", res.data.data);
          } else {
            commit("updateCUData", []);
          }
        })
        .catch(() => {
          commit("updateCUData", []);
        });
    },
    getNewFormBU({ state, commit }) {
      if (!state.newForm.bd) {
        commit("updateNewFormBUData", []);
        commit("updateNewFormCUData", []);
        return;
      }
      return api
        .queryProjectLevel({ BD: state.newForm.bd })
        .then(res => {
          if (res.data.success) {
            commit("updateNewFormBUData", res.data.data);
          } else {
            commit("updateNewFormBUData", []);
          }
        })
        .catch(() => {
          commit("updateNewFormBUData", []);
        });
    },
    getNewFormCU({ commit, state }) {
      if (!state.newForm.bu) {
        commit("updateNewFormCUData", []);
        return;
      }
      return api
        .queryProjectLevel({ BU: state.newForm.bu })
        .then(res => {
          if (res.data.success) {
            commit("updateNewFormCUData", res.data.data);
          } else {
            commit("updateNewFormCUData", []);
          }
        })
        .catch(() => {
          commit("updateNewFormCUData", []);
        });
    },
    addProjectInfo(commit, param) {
      return new Promise((reslove, reject) => {
        api
          .addProjectNode(param)
          .then(res => {
            reslove(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getProjectGroupInfo({ commit, state }, pageInfo) {
      return api
        .getProjectGroupInfo({
          BD: state.searchForm.bd,
          BU: state.searchForm.bu,
          CU: state.searchForm.cu,
          projectName: state.searchForm.teamName,
          pmName: state.searchForm.pm,
          pageNo: (pageInfo && pageInfo.pageNo) || 1,
          pageSize: (pageInfo && pageInfo.pageSize) || 10
        })
        .then(res => {
          if (res.data.success) {
            commit("updateDataSource", res.data.data);
          } else {
            commit("updateDataSource", { datas: [], totleSize: 0 });
          }
        });
    },
    delProjectInfo(commit, projectId) {
      return new Promise((reslove, reject) => {
        api
          .delProjectNode(projectId)
          .then(res => {
            reslove(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getProjectGroupInfoById(commit, projectGroupId) {
      return new Promise((reslove, reject) => {
        api
          .getProjectGroupInfoById(projectGroupId)
          .then(res => {
            reslove(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    queryProjectManagers({ commit, state }) {
      return api
        .queryProjectManagers({
          BU: state.newForm.bu
        })
        .then(res => {
          if (res.data.success) {
            commit("updateEmployeeData", res.data.data);
          } else {
            commit("updateEmployeeData", []);
          }
        })
        .catch(() => {
          commit("updateEmployeeData", []);
        });
    }
  }
};
export default projectGroup;
