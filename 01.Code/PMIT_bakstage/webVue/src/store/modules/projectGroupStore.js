import { getProductInfo, getDUInfo, getPDUInfo } from "@/api/projectGroupApi";
const projectGroup = {
  state: {
    product: [],
    du: [],
    pdu: []
  },
  mutations: {
    SET_PRODUCT: (state, product) => {
      state.product = product;
    },
    SET_DU: (state, du) => {
      state.du = du;
    },
    SET_PDU: (state, pdu) => {
      state.pdu = pdu;
    }
  },
  actions: {
    GetProductInfo({ commit }) {
      return new Promise((resolve, reject) => {
        getProductInfo()
          .then(response => {
            const data = response.data;
            commit("SET_PRODUCT", data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    GetDUInfo({ commit }, product) {
      return new Promise((resolve, reject) => {
        getDUInfo(product)
          .then(response => {
            commit("SET_DU", response.data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    GetPDUInfo({ commit }, product, du) {
      return new Promise((resolve, reject) => {
        getPDUInfo(product, du)
          .then(response => {
            commit("SET_PDU", response.data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    }
  }
};
export default projectGroup;
