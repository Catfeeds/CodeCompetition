import api from "@/api/employeeApi";
export default {
  state: {},
  mutations: {},
  actions: {
    getEmployeeList(commit, condition) {
      return new Promise((resolve, reject) => {
        api
          .getEmployeeList(condition)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getEmployeeInfoById(commit, employeeID) {
      return new Promise((resolve, reject) => {
        api
          .getEmployeeInfoById(employeeID)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editEmployeeInfo(commit, employeeInfo) {
      return new Promise((resolve, reject) => {
        api
          .editEmployeeInfo(employeeInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delEmployeeInfo(commit, employeeID) {
      return new Promise((resolve, reject) => {
        api
          .delEmployeeInfo(employeeID)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    addEmployeeInfo(commit, employeeInfo) {
      return new Promise((resolve, reject) => {
        api
          .addEmployeeInfo(employeeInfo)
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
