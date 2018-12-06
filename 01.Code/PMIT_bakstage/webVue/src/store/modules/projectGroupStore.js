import api from "@/api/projectGroupApi";
const projectGroup = {
  state: {
    selectedProduct: "",
    selectedDU: "",
    selectedPDU: ""
  },
  mutations: {
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
