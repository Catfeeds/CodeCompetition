import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import elmentui from 'element-ui'
import VueI18n from "vue-i18n";
Vue.config.productionTip = false;
Vue.use(elmentui)
Vue.use(VueI18n)
const i18n = new VueI18n({
  locale: 'CN',    // 语言标识
  messages: {
    en: {
      message: {
        hello: 'hello world'
      }
    },
    cn: {
      message: {
        hello: '你好、世界'
      }
    }
  },
})

new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount("#app");
