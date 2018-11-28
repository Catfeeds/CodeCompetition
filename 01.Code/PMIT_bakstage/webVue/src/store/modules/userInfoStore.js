import { loginByUserName, logout, getUserInfo } from "@/api/userInfoApi";
import { getToken, setToken, removeToken } from "@/utils/auth";

const user = {
  state: {
    user: "",
    status: "",
    code: "",
    token: getToken(),
    name: "",
    avatar: "",
    introduction: "",
    roles: [],
    setting: {
      articlePlatform: []
    }
  },

  mutations: {
    setToken: (state, token) => {
      state.token = token;
    },
    setIntroduction: (state, introduction) => {
      state.introduction = introduction;
    },
    setName: (state, name) => {
      state.name = name;
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
            commit("setToken", data.token);
            setToken(response.data.token);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },

    // 获取用户信息
    getUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getUserInfo(state.token)
          .then(response => {
            console.log(response);
            if (!response.data) {
              // 由于mockjs 不支持自定义状态码只能这样hack
              reject("error");
            }
            const data = response.data;
            if (data.roles && data.roles.length > 0) {
              // 验证返回的roles是否是一个非空数组
              commit("setRoles", data.roles);
            } else {
              reject("getInfo: roles must be a non-null array !");
            }
            commit("setName", data.name);
            commit("setAvatar", data.avatar);
            commit("setIntroduction", data.introduction);
            resolve(response);
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
    }
  }
};
export default user;
