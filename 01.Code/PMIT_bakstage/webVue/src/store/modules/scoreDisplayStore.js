import api from "@/api/reportDisplayApi";
import scoreApi from "@/api/scoreDisplayApi";

let state = {
  selectedProduct: "",
  selectedArea: "",
  selectedPDU: "",
  productData: [],
  areaData: [],
  pduData: [],
  scoreData: []
};

let getters = {
  getSDParams(state) {
    return function() {
      return {
        bu: state.selectedProduct,
        region: state.selectedArea,
        pdu: state.selectedPDU
      };
    };
  }
};
let mutations = {
  setSDSelectedProduct(state, val) {
    state.selectedProduct = val || "";
  },
  setSDSelectedArea(state, val) {
    state.selectedArea = val || "";
  },
  setSDSelectedPDU(state, val) {
    state.selectedPDU = val || "";
  },
  setSDProductData(state, val) {
    state.productData = val || [];
    // state.productData.unshift("");
    state.selectedProduct = "";
  },
  setSDAreaData(state, val) {
    state.areaData = val || [];
    // state.areaData.unshift("");
    state.selectedArea ="";
  },
  setSDPDUData(state, val) {
    state.pduData = val || [];
    // state.pduData.unshift("");
    state.selectedPDU = "";
  },
  setScoreData(state, val) {
    state.scoreData = val || {};
  }
};

let actions = {
  getSDProductInfo({ dispatch, state, commit }) {
    return api.getMainstayLevel().then(function(ret) {
      commit("setSDProductData", ret && ret.data.data);
      if (state.selectedProduct) {
        return dispatch("getSDAreas");
      }
    });
  },
  getSDAreas({ dispatch, state, commit }) {
    return api.getMainstayLevel({ bu: state.selectedProduct }).then(ret => {
      commit("setSDAreaData", ret && ret.data && ret.data.data);
      if (state.selectedArea) {
        return dispatch("getSDPDUList");
      }
    });
  },
  getSDPDUList({ commit, state }) {
    return api
      .getMainstayLevel({
        bu: state.selectedProduct,
        workPlaceArea: state.selectedArea
      })
      .then(ret => {
        commit("setSDPDUData", ret && ret.data && ret.data.data);
      });
  },
  getTrainingScore1({ commit }, param) {
    return scoreApi.getTrainingScore(param).then(ret => {
      commit("setScoreData", ret && ret.data && ret.data.data);
    });
  }
};
export default {
  state,
  getters,
  mutations,
  actions
};
