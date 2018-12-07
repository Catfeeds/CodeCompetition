import api from "@/api/appApi";
import { getArea, getRelationships } from "@/api/relationTreeApi";

let state = {
  // products:[],
  // aeras:[],
  selectedProduct: "",
  selectedArea: ""
};

let mutations = {
  // setProducts(state,val){
  //     state.products = val;
  // },
  // setArea(state,val){
  //     state.aeras = val;
  // },
  setSelectedProduct(state, val) {
    state.selectedProduct = val;
  },
  setSelectedArea(state, val) {
    state.selectedArea = val;
  }
};

let actions = {
  getRSProductInfo() {
    return api.getBusinessRelationship({});
  },
  getRSArea(commit, product) {
    return getArea(product);
  },
  getRelationShips({ state }) {
    return getRelationships({
      product: state.product,
      area: state.area
    });
  }
};
export default {
  state,
  mutations,
  actions
};
