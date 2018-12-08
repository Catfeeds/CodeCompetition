import {
  loginByUserName,
  logout,
  getUserInfo,
  updatePassword
} from "@/api/userInfoApi";
import { getToken, setToken, removeToken } from "@/utils/auth";

const user = {
  state: {
    status: "",
    code: "",
    token: getToken(),
    employeeName: "",
    employeeId: "",
    avatar: "",
    roles: [],
    setting: {
      articlePlatform: []
    }
  },

  mutations: {
    setToken: (state, token) => {
      state.token = token;
    },
    setName: (state, name) => {
      state.employeeName = name;
    },
    setId: (state, id) => {
      state.employeeId = id;
    },
    setAvatar: (state, avatar) => {
      state.avatar = avatar;
    },
    setRoles: (state, roles) => {
      state.roles = roles;
    }
  },

  actions: {
    // 用户名登录
    loginByUserName({ commit }, userInfo) {
      const userName = userInfo.userName.trim();
      return new Promise((resolve, reject) => {
        loginByUserName(userName, userInfo.password)
          .then(response => {
            const data = response.data;
            commit("setToken", userName);
            setToken(userName);
            resolve(data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },

    // 获取用户信息
    getUserInfo({ commit }, employeeId) {
      return new Promise((resolve, reject) => {
        getUserInfo(employeeId)
          .then(res => {
            if (!res.data.success) {
              // 由于mockjs 不支持自定义状态码只能这样hack
              reject("error");
            }
            const data = res.data.data;
            commit("setName", data.employeeName);
            commit("setId", data.employeeID);
            commit("setAvatar", data.picture);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    // 登出
    logOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token)
          .then(() => {
            commit("setToken", "");
            commit("setRoles", []);
            removeToken();
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit("setToken", "");
        removeToken();
        resolve();
      });
    },
    updatePassword(commit, params) {
      return new Promise((resolve, reject) => {
        updatePassword(params)
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
export default user;
