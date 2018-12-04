import api from "@/api/affairsApi";
export default {
  state: {},
  mutations: {},
  actions: {
    getAffairsList(commit, page) {
      return new Promise((resolve, reject) => {
        api
          .getAffairsList(page)
          .then(response => {
            resolve(response.data.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getAffairsInfoById(commit, trainId) {
      return new Promise((resolve, reject) => {
        api
          .getAffairsInfoById(trainId)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    addAffairsInfo(commit, trainInfo) {
      return new Promise((resolve, reject) => {
        api
          .addAffairsInfo(trainInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editAffairsInfo(commit, trainInfo) {
      return new Promise((resolve, reject) => {
        api
          .editAffairsInfo(trainInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delAffairsInfo(commit, trainId) {
      return new Promise((resolve, reject) => {
        api
          .delAffairsInfo(trainId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    queryAffairsSeries() {
      return new Promise((resolve, reject) => {
        api
          .querySeries()
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    queryAffairsSystem() {
      return new Promise((resolve, reject) => {
        api
          .querySystem()
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
