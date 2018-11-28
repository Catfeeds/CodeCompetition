import Cookies from "js-cookie";
import { constantRouterMap, asyncRouterMap } from "@/router";
import { getMenuInfo } from "@/api/appApi";
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
      let isFlag = menus.some(menu => menu.name == route.name);
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
    //查询菜单
    getMenuInfo({ commit }) {
      return new Promise((resolve, reject) => {
        getMenuInfo()
          .then(response => {
            let accessedRouters = filterAsyncRouter(
              asyncRouterMap,
              response.data
            );
            commit("setRouters", accessedRouters);
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
