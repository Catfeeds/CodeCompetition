import api from "@/api/projectGroupApi";
import _ from "underscore";
const projectGroup = {
  state: {
    productList: [],
    duList: [],
    pduList: [],
    selectedProduct: "",
    selectedDU: "",
    selectedPDU: ""
  },
  mutations: {
    setProductList: (state, product) => {
      state.productList = product;
    },
    setDUList: (state, du) => {
      state.duList = du;
    },
    setPDUList: (state, pdu) => {
      state.pduList = pdu;
    },
    updateProduct: (state, value) => {
      state.selectedProduct = value;
    },
    updateDU: (state, value) => {
      state.selectedDU = value;
    },
    updatePDU: (state, value) => {
      state.selectedPDU = value;
    }
  },
  actions: {
    getProductInfo({ commit }) {
      return new Promise((resolve, reject) => {
        api
          .getProductInfo()
          .then(response => {
            const data = _.map(response.data, function(item) {
              return {
                label: item.name,
                value: item.id
              };
            });
            commit("setProductList", data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getDUInfo({ commit }, product) {
      return new Promise((resolve, reject) => {
        api
          .getDUInfo(product)
          .then(response => {
            const data = _.map(response.data, function(item) {
              return {
                label: item.name,
                value: item.id
              };
            });
            commit("setDUList", data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getPDUInfo({ commit }, product, du) {
      return new Promise((resolve, reject) => {
        api
          .getPDUInfo(product, du)
          .then(response => {
            const data = _.map(response.data, function(item) {
              return {
                label: item.name,
                value: item.id
              };
            });
            commit("setPDUList", data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getProjectGroupInfo(commit, param) {
      return new Promise((resolve, reject) => {
        api
          .getProjectGroupInfo(param)
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
export default projectGroup;
