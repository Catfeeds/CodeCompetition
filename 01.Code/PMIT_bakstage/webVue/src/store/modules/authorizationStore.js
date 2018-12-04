import api from "@/api/authorizationApi";
const authorization = {
  state: {},
  mutations: {},
  actions: {
    getRoleList() {
      return new Promise((resolve, reject) => {
        api
          .getRoleList()
          .then(response => {
            resolve(response.data.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getRoleInfoById(commit, id) {
      return new Promise((resolve, reject) => {
        api
          .getRoleInfoById(id)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    addRoleInfo(commit, roleInfo) {
      return new Promise((resolve, reject) => {
        api
          .addRoleInfo(roleInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delRoleInfo(commit, roleId) {
      return new Promise((resolve, reject) => {
        api
          .delRoleInfo(roleId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editRoleInfo(commit, roleInfo) {
      return new Promise((resolve, reject) => {
        api
          .editRoleInfo(roleInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getLoginUserList(commit, pageInfo) {
      return new Promise((resolve, reject) => {
        api
          .getLoginUserList(pageInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getLoginUserById(commit, employeeID) {
      return new Promise((resolve, reject) => {
        api
          .getLoginUserById(employeeID)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    addLoginUser(commit, userInfo) {
      return new Promise((resolve, reject) => {
        api
          .addLoginUser(userInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delLoginUser(commit, userId) {
      return new Promise((resolve, reject) => {
        api
          .delLoginUser(userId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editLoginUser(commit, userInfo) {
      return new Promise((resolve, reject) => {
        api
          .editLoginUser(userInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    searchEmployeeInfo(commit, keyword) {
      return new Promise((resolve, reject) => {
        api
          .searchEmployeeInfo(keyword)
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
export default authorization;
