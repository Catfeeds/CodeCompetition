import Cookies from "js-cookie";
import { constantRouterMap, asyncRouterMap } from "@/router";
import api from "@/api/appApi";
/**
 * 递归过滤异步路由表，根据后台返回的菜单列表过滤后的路由表
 * @param routes asyncRouterMap
 * @param menus
 */
function filterAsyncRouter(routes, menus) {
  const res = [];
  routes.forEach(route => {
    const tmp = { ...route };
    if (tmp.children) {
      tmp.children = filterAsyncRouter(tmp.children, menus);
      if (tmp.children.length > 0) {
        res.push(tmp);
      }
    } else {
      let isFlag = menus.some(menu => menu.menuName == route.name);
      if (isFlag) {
        res.push(tmp);
      }
    }
  });
  return res;
}

const app = {
  state: {
    sidebar: {
      opened: !+Cookies.get("sidebarStatus"),
      withoutAnimation: false
    },
    device: "desktop",
    language: Cookies.get("language") || "zh",
    size: Cookies.get("size") || "medium",
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    toggleSideBar: state => {
      if (state.sidebar.opened) {
        Cookies.set("sidebarStatus", 1);
      } else {
        Cookies.set("sidebarStatus", 0);
      }
      state.sidebar.opened = !state.sidebar.opened;
      state.sidebar.withoutAnimation = false;
    },
    closeSideBar: (state, withoutAnimation) => {
      Cookies.set("sidebarStatus", 1);
      state.sidebar.opened = false;
      state.sidebar.withoutAnimation = withoutAnimation;
    },
    toggleDevice: (state, device) => {
      state.device = device;
    },
    setLanguage: (state, language) => {
      state.language = language;
      Cookies.set("language", language);
    },
    setSize: (state, size) => {
      state.size = size;
      Cookies.set("size", size);
    },
    setRouters: (state, routers) => {
      state.addRouters = routers;
      state.routers = constantRouterMap.concat(routers);
    },
    setProductList: (state, product) => {
      state.productList = product;
    },
    setDUList: (state, du) => {
      state.duList = du;
    },
    setPDUList: (state, pdu) => {
      state.pduList = pdu;
    },
    setAreaList: (state, area) => {
      state.areaList = area;
    }
  },
  actions: {
    toggleSideBar({ commit }) {
      commit("toggleSideBar");
    },
    closeSideBar({ commit }, { withoutAnimation }) {
      commit("closeSideBar", withoutAnimation);
    },
    toggleDevice({ commit }, device) {
      commit("toggleDevice", device);
    },
    setLanguage({ commit }, language) {
      commit("setLanguage", language);
    },
    setSize({ commit }, size) {
      commit("setSize", size);
    },
    //根据登录用户ID查询菜单
    getMenuInfoByEmployeeId({ commit }, role) {
      return new Promise((resolve, reject) => {
        api
          .getMenuInfoByEmployeeId(role)
          .then(response => {
            let accessedRouters = filterAsyncRouter(
              asyncRouterMap,
              response.data.data
            );
            commit("setRouters", accessedRouters);
            resolve(accessedRouters);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    //根据角色ID查询菜单
    getMenuInfoByRoleId(commit, role) {
      return new Promise((resolve, reject) => {
        api
          .getMenuInfoByRoleId(role)
          .then(response => {
            resolve(response.data.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    //查询所有菜单信息
    getAllMenuInfo() {
      return new Promise((resolve, reject) => {
        api
          .getAllMenuInfo()
          .then(res => {
            resolve(res.data.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getProductInfo({ commit }) {
      return new Promise((resolve, reject) => {
        api
          .getProductInfo()
          .then(res => {
            let data = [];
            if (res.data.data) {
              data = res.data.data.map(item => {
                return {
                  label: item,
                  value: item
                };
              });
            }
            commit("setProductList", data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getDUInfo({ commit }, product) {
      return new Promise((resolve, reject) => {
        api
          .getDUInfo(product)
          .then(res => {
            let data = [];
            if (res.data.data) {
              data = res.data.data.map(item => {
                return {
                  label: item,
                  value: item
                };
              });
            }
            commit("setDUList", data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getPDUInfo({ commit }, product, du) {
      return new Promise((resolve, reject) => {
        api
          .getPDUInfo(product, du)
          .then(res => {
            let data = [];
            if (res.data.data) {
              data = res.data.data.map(item => {
                return {
                  label: item,
                  value: item
                };
              });
            }
            commit("setPDUList", data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getAreaInfo({ commit }, product, du, pdu) {
      return new Promise((resolve, reject) => {
        api
          .getAreaInfo(product, du, pdu)
          .then(res => {
            let data = [];
            if (res.data.data) {
              data = res.data.data.map(item => {
                return {
                  label: item,
                  value: item
                };
              });
            }
            commit("setAreaList", data);
            resolve();
          })
          .catch(error => {
            reject(error);
          });
      });
    }
  }
};

export default app;
