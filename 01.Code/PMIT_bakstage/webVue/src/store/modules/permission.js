import { constantRouterMap, asyncRouterMap } from "@/router";
import { getMenuInfo } from "@/api/common";
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

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers;
      state.routers = constantRouterMap.concat(routers);
    }
  },
  actions: {
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

export default permission;
