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
            resolve(response.data);
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
    getLoginUserList() {
      return new Promise((resolve, reject) => {
        api
          .getLoginUserList()
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
    }
  }
};
export default authorization;
