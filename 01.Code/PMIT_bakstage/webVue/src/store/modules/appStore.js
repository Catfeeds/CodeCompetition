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
      opened: !!+Cookies.get("sidebarStatus"),
      withoutAnimation: false
    },
    device: "desktop",
    language: Cookies.get("language") || "zh",
    size: Cookies.get("size") || "medium",
    routers: constantRouterMap,
    addRouters: [],
    productList: [],
    duList: [],
    pduList: [],
    areaList: []
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
          .then(res => {
            if (res.data.success) {
              let accessedRouters = filterAsyncRouter(
                asyncRouterMap,
                res.data.data
              );
              commit("setRouters", accessedRouters);
              resolve(accessedRouters);
            } else {
              resolve([]);
            }
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
            resolve(response.data);
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
            resolve(res.data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getProductInfo() {
      return new Promise((resolve, reject) => {
        api
          .getBU({})
          .then(res => {
            let data = [];
            if (res.data.success) {
              data = res.data.data.map(item => {
                return {
                  label: item,
                  value: item
                };
              });
            }
            resolve(data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getDUInfo(commit, param) {
      return new Promise((resolve, reject) => {
        api
          .getDU(param.bu)
          .then(res => {
            let data = [];
            if (res.data.success) {
              data = res.data.data.map(item => {
                return {
                  label: item,
                  value: item
                };
              });
            }
            resolve(data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getPDUInfo(commit, param) {
      return new Promise((resolve, reject) => {
        api
          .getPDU(param.bu, param.du)
          .then(res => {
            let data = [];
            if (res.data.success) {
              data = res.data.data.map(item => {
                return {
                  label: item,
                  value: item
                };
              });
            }
            resolve(data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getAreaInfo(commit, param) {
      return new Promise((resolve, reject) => {
        api
          .getBusinessRelationship(param)
          .then(res => {
            let data = [];
            if (res.data.success) {
              data = res.data.data.map(item => {
                return {
                  label: item,
                  value: item
                };
              });
            }
            resolve(data);
          })
          .catch(error => {
            reject(error);
          });
      });
    },
    getLogList(commit, condition) {
      let logData = {
        success: true,
        data: [
          {
            time: new Date("2018-12-08 17:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-08 15:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-08 14:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-08 12:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-08 10:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-07 19:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-04 15:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-04 15:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-04 15:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-04 15:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-04 15:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-04 15:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-04 15:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          },
          {
            time: new Date("2018-12-04 15:11:26"),
            account: "阮海涛",
            ipaddress: "10.60.86.24",
            url: "",
            param: "",
            message: "操作成功"
          }
        ]
      };
      return new Promise((resolve, reject) => {
        resolve(logData);
        // api
        //   .getLogList(condition)
        //   .then(() => {
        //     resolve(logData);
        //   })
        //   .catch(error => {
        //     reject(error);
        //   });
      });
    }
  }
};

export default app;
