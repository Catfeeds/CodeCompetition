import api from "@/api/affairsApi";
export default {
  state: {},
  mutations: {},
  actions: {
    getAffairsList(commit, condition) {
      return new Promise((resolve, reject) => {
        api
          .getAffairsList(condition)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getAffairsInfoById(commit, affairId) {
      return new Promise((resolve, reject) => {
        api
          .getAffairsInfoById(affairId)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    addAffairsInfo(commit, affairInfo) {
      return new Promise((resolve, reject) => {
        api
          .addAffairsInfo(affairInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editAffairsInfo(commit, affairInfo) {
      return new Promise((resolve, reject) => {
        api
          .editAffairsInfo(affairInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delAffairsInfo(commit, affairId) {
      return new Promise((resolve, reject) => {
        api
          .delAffairsInfo(affairId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    addDimensionInfo(commit, dimensionInfo) {
      return new Promise((resolve, reject) => {
        api
          .addDimensionInfo(dimensionInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getDimensionInfo(commit, affairId) {
      return new Promise((resolve, reject) => {
        api
          .getDimensionInfo(affairId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    }
  }
};
