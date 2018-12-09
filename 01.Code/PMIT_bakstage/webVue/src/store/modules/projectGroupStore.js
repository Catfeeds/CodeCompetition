import api from "@/api/projectGroupApi";
const projectGroup = {
  state: {
    searchForm: {
      product: "",
      du: "",
      pdu: "",
      teamName: "",
      pm: "",
      productOptions: [],
      duOptions: [],
      pduOptions: []
    },
    dataSource: [],
    newForm: {
      product: "",
      du: "",
      pdu: "",
      teamName: "",
      employeeName: "",
      employeeId: "",
      productOptions: [],
      duOptions: [],
      pduOptions: []
    }
  },
  mutations: {
    updatePGProductData(state, value) {
      state.searchForm.productOptions = value || [];
      state.searchForm.product = "";
      state.searchForm.duOptions = value || [];
      state.searchForm.du = "";
      state.searchForm.pduOptions = value || [];
      state.searchForm.pdu = "";
    },
    updatePGDUData(state, value) {
      state.searchForm.duOptions = value || [];
      state.searchForm.du = "";
      state.searchForm.pduOptions = value || [];
      state.searchForm.pdu = "";
    },
    updatePGPDUData(state, value) {
      state.searchForm.pduOptions = value || [];
      state.searchForm.pdu = "";
    },
    updateNewFormProductData(state, value) {
      state.newForm.productOptions = value || [];
      state.newForm.product = "";
      state.newForm.duOptions = value || [];
      state.newForm.du = "";
      state.newForm.pduOptions = value || [];
      state.newForm.pdu = "";
    },
    updateNewFormDUData(state, value) {
      state.newForm.duOptions = value || [];
      state.newForm.du = "";
      state.newForm.pduOptions = value || [];
      state.newForm.pdu = "";
    },
    updateNewFormPDUData(state, value) {
      state.newForm.pduOptions = value || [];
      state.newForm.pdu = "";
    },
    updateDataSource(state, value) {
      state.dataSource = value || [];
    }
  },
  actions: {
    getPGProductInfo({ dispatch, commit }) {
      return dispatch("getProductInfo").then(data => {
        commit("updatePGProductData", data);
        return dispatch("getPGDU");
      });
    },
    getPGDU({ dispatch, state, commit }) {
      if (!state.searchForm.product) {
        commit("updatePGDUData", []);
        commit("updatePGPDUData", []);
        return;
      }
      return dispatch("getDUInfo", { bu: state.searchForm.product }).then(
        data => {
          commit("updatePGDUData", data);
          return dispatch("getPGPDUList");
        }
      );
    },
    getPGPDUList({ dispatch, commit, state }) {
      if (!state.searchForm.du) {
        commit("updatePGPDUData", []);
        return;
      }
      return dispatch("getPDUInfo", {
        bu: state.searchForm.product,
        du: state.searchForm.du
      }).then(data => {
        commit("updatePGPDUData", data);
      });
    },
    getNewFormProductInfo({ dispatch, commit }) {
      return dispatch("getProductInfo").then(data => {
        commit("updateNewFormProductData", data);
        return dispatch("getNewFormDU");
      });
    },
    getNewFormDU({ dispatch, state, commit }) {
      if (!state.newForm.product) {
        commit("updateNewFormDUData", []);
        commit("updateNewFormPDUData", []);
        return;
      }
      return dispatch("getDUInfo", { bu: state.newForm.product }).then(data => {
        commit("updateNewFormDUData", data);
        return dispatch("getNewFormPDUList");
      });
    },
    getNewFormPDUList({ dispatch, commit, state }) {
      if (!state.newForm.du) {
        commit("updateNewFormPDUData", []);
        return;
      }
      return dispatch("getPDUInfo", {
        bu: state.newForm.product,
        du: state.newForm.du
      }).then(data => {
        commit("updateNewFormPDUData", data);
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
          bu: state.searchForm.product,
          du: state.searchForm.du,
          pdu: state.searchForm.pdu,
          projectName: state.searchForm.teamName,
          pmName: state.searchForm.pm,
          pageNo: (pageInfo && pageInfo.pageNo) || 1
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
    }
  }
};
export default projectGroup;
