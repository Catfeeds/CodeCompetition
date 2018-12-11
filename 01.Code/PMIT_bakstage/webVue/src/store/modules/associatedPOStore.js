import api from "@/api/teamSettingsApi";

const employeeSettings = {
  state: {
    searchForm: {
      product: "",
      du: "",
      pdu: "",
      poID: "",
      poName: "",
      duOptions: [],
      pduOptions: []
    }
  },
  mutations: {
    updatePOFormDUData(state, value) {
      state.searchForm.duOptions = value || [];
    },
    updatePOFormPDUData(state, value) {
      state.searchForm.pduOptions = value || [];
    }
  },
  actions: {
    getAllPOInfo({ state }) {
      return new Promise((resolve, reject) => {
        let param = {
          bu: state.searchForm.product,
          du: state.searchForm.du,
          pdu: state.searchForm.pdu,
          projectId: state.searchForm.poID,
          projectName: state.searchForm.poName
        };
        api
          .getProjectInfo(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getPrjectRelatedPO(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .getPrjectRelatedPO(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getPOFormDU({ dispatch, state, commit }) {
      if (!state.searchForm.product) {
        commit("updatePOFormDUData", []);
        commit("updatePOFormPDUData", []);
        return;
      }
      return dispatch("getDUInfo", { bu: state.searchForm.product }).then(
        data => {
          commit("updatePOFormDUData", data);
          return dispatch("getPOFormPDUList");
        }
      );
    },
    getPOFormPDUList({ dispatch, commit, state }) {
      if (!state.searchForm.du) {
        commit("updatePOFormPDUData", []);
        return;
      }
      return dispatch("getPDUInfo", {
        bu: state.searchForm.product,
        du: state.searchForm.du
      }).then(data => {
        commit("updatePOFormPDUData", data);
      });
    },
    teamRelatedPO(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .teamRelatedPO(param)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delTeamRelatedPO(arg, param) {
      return new Promise((resolve, reject) => {
        api
          .delTeamRelatedPO(param)
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
