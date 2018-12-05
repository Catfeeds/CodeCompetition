import api from "@/api/ruleApi";
export default {
  state: {},
  mutations: {},
  actions: {
    getRoleList(commit, condition) {
      return new Promise((resolve, reject) => {
        api
          .getRoleList(condition)
          .then(response => {
            resolve(response.data.data);
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
    getRuleList(commit, condition) {
      return new Promise((resolve, reject) => {
        api
          .getRuleList(condition)
          .then(response => {
            resolve(response.data.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    addRuleInfo(commit, ruleInfo) {
      return new Promise((resolve, reject) => {
        api
          .addRuleInfo(ruleInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    editRuleInfo(commit, ruleInfo) {
      return new Promise((resolve, reject) => {
        api
          .editRuleInfo(ruleInfo)
          .then(response => {
            resolve(response.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    delRuleInfo(commit, ruleId) {
      return new Promise((resolve, reject) => {
        api
          .delRuleInfo(ruleId)
          .then(response => {
            resolve(response.data.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    }
  }
};
