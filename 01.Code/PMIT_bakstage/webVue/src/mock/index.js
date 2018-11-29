import Mock from "mockjs";
import loginAPI from "./login";
import commonAPI from "./common";
import remoteSearchAPI from "./remoteSearch";
import transactionAPI from "./transaction";
import projectGrupAPI from "./projectGroupMock";

// 修复在使用 MockJS 情况下，设置 withCredentials = true，且未被拦截的跨域请求丢失 Cookies 的问题
// https://github.com/nuysoft/Mock/issues/300
Mock.XHR.prototype.proxy_send = Mock.XHR.prototype.send;
Mock.XHR.prototype.send = function() {
  if (this.custom.xhr) {
    this.custom.xhr.withCredentials = this.withCredentials || false;
  }
  this.proxy_send(...arguments);
};

// Mock.setup({
//   timeout: '350-600'
// })

// 登录相关
Mock.mock(/\/auth\/loginIn/, "post", loginAPI.loginByUserName);
Mock.mock(/\/login\/logout/, "post", loginAPI.logout);
Mock.mock(/\/user\/info\.*/, "get", loginAPI.getUserInfo);

// 菜单相关
Mock.mock(/\/getMenuInfo/, "get", commonAPI.getMenu);

// 搜索相关
Mock.mock(/\/search\/user/, "get", remoteSearchAPI.searchUser);

// 账单相关
Mock.mock(/\/transaction\/list/, "get", transactionAPI.getList);
//项目组设置相关
Mock.mock(
  /\/projectGroup\/getProductInfo/,
  "get",
  projectGrupAPI.getProductInfo
);
Mock.mock(/\/projectGroup\/getDUInfo/, "get", projectGrupAPI.getDUInfo);
Mock.mock(/\/projectGroup\/getPDUInfo/, "get", projectGrupAPI.getPDUctInfo);
Mock.mock(
  /\/projectGroup\/getProjectGroupInfo/,
  "get",
  projectGrupAPI.getProjectGroupInfo
);
export default Mock;
