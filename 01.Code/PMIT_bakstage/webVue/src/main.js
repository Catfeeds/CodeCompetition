import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import elmentui from "element-ui";
import NProgress from "nprogress";
import "nprogress/nprogress.css";
import "normalize.css"; // A modern alternative to CSS resets
import "element-ui/lib/theme-chalk/index.css";
import Cookies from "js-cookie";
// import { getToken } from "@/utils/auth"; // getToken from cookie
import "@/styles/index.scss"; // global css
import i18n from "./lang"; // Internationalization
import "./icons"; // icon
Vue.config.productionTip = false;
NProgress.configure({ showSpinner: false }); // NProgress Configuration
Vue.use(elmentui);
router.beforeEach((to, from, next) => {
  if (Cookies.get("loginName")) {
    store.dispatch("getUserInfo", Cookies.get("loginName"));
  }
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (Cookies.get("status") !== "logined") {
      next({
        path: "/login",
        query: { redirect: to.fullPath } // 把要跳转的地址作为参数传到下一步
      });
    } else {
      NProgress.start();
      next();
    }
  } else {
    if (to.query && to.query.redirect) {
      if (Cookies.get("status") === "logined") {
        next({ path: to.query.redirect });
      } else {
        NProgress.start();
        next();
      }
    } else {
      NProgress.start();
      next(); // 确保一定要调用 next()
    }
  }
});
//const whiteList = ["/login", "/auth-redirect"]; // no redirect whitelist

// router.beforeEach((to, from, next) => {
//   NProgress.start(); // start progress bar
//   if (getToken()) {
//     // determine if there has token
//     /* has token*/
//     if (to.path === "/login") {
//       next({ path: "/" });
//       NProgress.done(); // if current page is dashboard will not trigger	afterEach hook, so manually handle it
//     } else {
//       if (Cookies.get("loginName")) {
//         store
//           .dispatch("getMenuInfoByEmployeeId", Cookies.get("loginName"))
//           .then(res => {
//             // 根据roles权限生成可访问的路由表
//             router.addRoutes(res); // 动态添加可访问路由表
//             next({ ...to, replace: true }); // hack方法 确保addRoutes已完成 ,set the replace: true so the navigation will not leave a history record
//           });
//       } else {
//         next();
//       }
//     }
//   } else {
//     /* has no token*/
//     if (whiteList.indexOf(to.path) !== -1) {
//       // 在免登录白名单，直接进入
//       next();
//     } else {
//       next(`/login?redirect=${to.path}`); // 否则全部重定向到登录页
//       NProgress.done(); // if current page is login will not trigger afterEach hook, so manually handle it
//     }
//   }
// });
router.afterEach(() => {
  NProgress.done();
});
new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount("#app");
