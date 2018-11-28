const getters = {
  sidebar: state => state.appStore.sidebar,
  language: state => state.appStore.language,
  size: state => state.appStore.size,
  device: state => state.appStore.device,
  visitedViews: state => state.tagsViewStore.visitedViews,
  cachedViews: state => state.tagsViewStore.cachedViews,
  token: state => state.userInfoStore.token,
  avatar: state => state.userInfoStore.avatar,
  name: state => state.userInfoStore.name,
  introduction: state => state.userInfoStore.introduction,
  status: state => state.userInfoStore.status,
  roles: state => state.userInfoStore.roles,
  setting: state => state.userInfoStore.setting,
  permission_routers: state => state.appStore.routers,
  addRouters: state => state.appStore.addRouters,
  productList: state => state.projectGroupStore.productList,
  duList: state => state.projectGroupStore.duList,
  pduList: state => state.projectGroupStore.pduList
};
export default getters;
