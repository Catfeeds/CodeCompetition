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
    TOGGLE_SIDEBAR: state => {
      if (state.sidebar.opened) {
        Cookies.set("sidebarStatus", 1);
      } else {
        Cookies.set("sidebarStatus", 0);
      }
      state.sidebar.opened = !state.sidebar.opened;
      state.sidebar.withoutAnimation = false;
    },
    CLOSE_SIDEBAR: (state, withoutAnimation) => {
      Cookies.set("sidebarStatus", 1);
      state.sidebar.opened = false;
      state.sidebar.withoutAnimation = withoutAnimation;
    },
    TOGGLE_DEVICE: (state, device) => {
      state.device = device;
    },
    SET_LANGUAGE: (state, language) => {
      state.language = language;
      Cookies.set("language", language);
    },
    SET_SIZE: (state, size) => {
      state.size = size;
      Cookies.set("size", size);
    },
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers;
      state.routers = constantRouterMap.concat(routers);
    }
  },
  actions: {
    toggleSideBar({ commit }) {
      commit("TOGGLE_SIDEBAR");
    },
    closeSideBar({ commit }, { withoutAnimation }) {
      commit("CLOSE_SIDEBAR", withoutAnimation);
    },
    toggleDevice({ commit }, device) {
      commit("TOGGLE_DEVICE", device);
    },
    setLanguage({ commit }, language) {
      commit("SET_LANGUAGE", language);
    },
    setSize({ commit }, size) {
      commit("SET_SIZE", size);
    },
    //查询菜单
    GetMenuInfo({ commit }) {
      return new Promise((resolve, reject) => {
        getMenuInfo()
          .then(response => {
            let accessedRouters = filterAsyncRouter(
              asyncRouterMap,
              response.data
            );
            commit("SET_ROUTERS", accessedRouters);
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
