const getters = {
  sidebar: state => state.appStore.sidebar,
  language: state => state.appStore.language,
  size: state => state.appStore.size,
  device: state => state.appStore.device,
  visitedViews: state => state.tagsViewStore.visitedViews,
  cachedViews: state => state.tagsViewStore.cachedViews,
  token: state => state.userInfoStore.token,
  avatar: state =>
    state.userInfoStore.avatar ||
    "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
  employeeName: state => state.userInfoStore.employeeName,
  employeeId: state => state.userInfoStore.employeeId,
  status: state => state.userInfoStore.status,
  roles: state => state.userInfoStore.roles,
  setting: state => state.userInfoStore.setting,
  permission_routers: state => state.appStore.routers,
  addRouters: state => state.appStore.addRouters
};
export default getters;
