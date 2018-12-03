import api from "@/api/trainingApi";
export default {
  state: {},
  mutations: {},
  actions: {
    getTrainingList(commit, page) {
      return new Promise((resolve, reject) => {
        api
          .getTrainingList(page)
          .then(response => {
            resolve(response.data.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getTrainingById(commit, trainId) {
      return new Promise((resolve, reject) => {
        api
          .getTrainingById(trainId)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    saveTrainingInfo(commit, trainInfo) {
      return new Promise((resolve, reject) => {
        api
          .saveTrainingInfo(trainInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delTraining(commit, trainId) {
      return new Promise((resolve, reject) => {
        api
          .delTraining(trainId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    querySeries() {
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
    querySystem() {
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
