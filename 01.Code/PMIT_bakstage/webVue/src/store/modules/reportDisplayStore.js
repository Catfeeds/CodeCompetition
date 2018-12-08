import api from "@/api/reportDisplayApi";

let state = {
  selectedProduct: "",
  selectedArea: "",
  selectedPDU: "",
  productData: [],
  areaData: [],
  pduData: [],
  selectedEType: 0,
  rateData: [],
  departmentStatisData: [],
  positionStatisData: [],
  trainingStatisData: [],
  ageStatisData: [],
  employeeData: {}
};

let getters = {
  getParams(state) {
    return function() {
      return {
        bu: state.selectedProduct,
        workPlaceArea: state.selectedArea,
        pdu: state.selectedPDU,
        isBacknone: state.selectedEType == 0
      };
    };
  }
};
let mutations = {
  setRDSelectedProduct(state, val) {
    state.selectedProduct = val || "";
  },
  setRDSelectedArea(state, val) {
    state.selectedArea = val || "";
  },
  setRDSelectedPDU(state, val) {
    state.selectedPDU = val || "";
  },
  setProductData(state, val) {
    state.productData = val || [];
    // state.productData.unshift("");
    state.selectedProduct = "";
  },
  setRDAreaData(state, val) {
    state.areaData = val || [];
    // state.areaData.unshift("");
    state.selectedArea = "";
  },
  setRDPDUData(state, val) {
    state.pduData = val || [];
    // state.pduData.unshift("");
    state.selectedPDU = "";
  },
  setSelectedEType(state, val) {
    state.selectedEType = val || "";
  },
  setRateData(state, val) {
    state.rateData = val || [];
    state.selectedEType = state.rateData[0] || "";
  },
  setDepartmentStatisData(state, val) {
    state.departmentStatisData = val || [];
  },
  setPositionStatisData(state, val) {
    state.positionStatisData = val || [];
  },
  setTrainingStatisData(state, val) {
    state.trainingStatisData = val || [];
  },
  setAgeStatisData(state, val) {
    state.ageStatisData = val || [];
  },
  setEmployeeData(state, val) {
    state.employeeData = val || {};
  }
};

let actions = {
  getRDProductInfo({ dispatch, state, commit }) {
    return api.getMainstayLevel().then(function(ret) {
      commit("setProductData", ret && ret.data.data);
      if (state.selectedProduct) {
        return dispatch("getRDAreas");
      }
    });
  },
  getRDAreas({ dispatch, state, commit }) {
    return api.getMainstayLevel({ bu: state.selectedProduct }).then(ret => {
      commit("setRDAreaData", ret && ret.data && ret.data.data);
      if (state.selectedArea) {
        return dispatch("getRDPDUList");
      }
    });
  },
  getRDPDUList({ commit, state }) {
    return api
      .getMainstayLevel({
        bu: state.selectedProduct,
        workPlaceArea: state.selectedArea
      })
      .then(ret => {
        commit("setRDPDUData", ret && ret.data && ret.data.data);
      });
  },
  getRate({ commit, dispatch }, param) {
    return api.getRate(param).then(ret => {
      commit("setRateData", ret && ret.data && ret.data.data);
      if (ret && ret.data) {
        dispatch("getStatisticsChartData");
      }
    });
  },
  getStatisticsChartData({ dispatch, getters }) {
    var param = getters.getParams();
    dispatch("getStatisByDepartment", param);
    dispatch("getStatisByPosition", param);
    dispatch("getStatisByTranining", param);
    dispatch("getStatisByAge", param);
    dispatch("getRDEmployee", param);
  },
  getStatisByDepartment({ commit }, param) {
    return api.getStatisByDepartment(param).then(ret => {
      commit("setDepartmentStatisData", ret && ret.data && ret.data.data);
    });
  },
  getStatisByPosition({ commit }, param) {
    return api.getStatisByPosition(param).then(ret => {
      commit("setPositionStatisData", ret && ret.data && ret.data.data);
    });
  },
  getStatisByTranining({ commit }, param) {
    return api.getStatisByTranining(param).then(ret => {
      commit("setTrainingStatisData", ret && ret.data && ret.data.data);
    });
  },
  getStatisByAge({ commit }, param) {
    return api.getStatisByAge(param).then(ret => {
      commit("setAgeStatisData", ret && ret.data && ret.data.data);
    });
  },
  getRDEmployee({ commit }, params) {
    if(params){
        params.pageNo = params.pageNo || 1;
        params.staffID = params.staffID || '';
        params.staffName = params.staffName || '';
    }
    return api.getEmployee(params).then(ret => {
      commit("setEmployeeData", ret && ret.data && ret.data.data);
    });
  }
};
export default {
  state,
  getters,
  mutations,
  actions
};
