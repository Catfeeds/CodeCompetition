import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import elmentui from 'element-ui';
import 'normalize.css' // A modern alternative to CSS resets
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global css
import i18n from './lang' // Internationalization
import './icons' // icon
Vue.config.productionTip = false;
Vue.use(elmentui)

new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount("#app");
