import api from "@/api/authorizationApi";
const authorization = {
  state: {},
  mutations: {},
  actions: {
    getSysRoleList() {
      return new Promise((resolve, reject) => {
        api
          .getSysRoleList()
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getSysRoleInfoById(commit, id) {
      return new Promise((resolve, reject) => {
        api
          .getSysRoleInfoById(id)
          .then(res => {
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    addSysRoleInfo(commit, roleInfo) {
      return new Promise((resolve, reject) => {
        api
          .addSysRoleInfo(roleInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delSysRoleInfo(commit, roleId) {
      return new Promise((resolve, reject) => {
        api
          .delSysRoleInfo(roleId)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editSysRoleInfo(commit, roleInfo) {
      return new Promise((resolve, reject) => {
        api
          .editSysRoleInfo(roleInfo)
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
    },
    backup(commit, param) {
      return new Promise((resolve, reject) => {
        api
          .backup(param)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    restore(commit, fileName) {
      return new Promise((resolve, reject) => {
        api
          .fileName(fileName)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getBackupList(commit, condition) {
      return new Promise((resolve, reject) => {
        api
          .getBackupList(condition)
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
