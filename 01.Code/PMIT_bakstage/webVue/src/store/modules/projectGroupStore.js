import api from "@/api/projectGroupApi";
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
    UPDATE_PRODUCT: (state, value) => {
      state.selectedProduct = value;
    }
  },
  actions: {
    getProductInfo({ commit }) {
      return new Promise((resolve, reject) => {
        api
          .getProductInfo()
          .then(response => {
            const data = response.data;
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
            commit("setDUList", response.data);
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
            commit("setPDUList", response.data);
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
