import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import elmentui from "element-ui";
import "normalize.css"; // A modern alternative to CSS resets
import "element-ui/lib/theme-chalk/index.css";
import Cookies from "js-cookie";

import "@/styles/index.scss"; // global css
import i18n from "./lang"; // Internationalization
import "./icons"; // icon
import "./mock/index";
Vue.config.productionTip = false;
Vue.use(elmentui);
router.beforeEach((to, from, next) => {
  store.dispatch("GetUserInfo");
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (Cookies.get("status") !== "logined") {
      next({
        path: "/login",
        query: { redirect: to.fullPath } // 把要跳转的地址作为参数传到下一步
      });
    } else {
      next();
    }
  } else {
    if (to.query && to.query.redirect) {
      if (Cookies.get("status") === "logined") {
        next({ path: to.query.redirect });
      } else {
        next();
      }
    } else {
      next(); // 确保一定要调用 next()
    }
  }
});
new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount("#app");
