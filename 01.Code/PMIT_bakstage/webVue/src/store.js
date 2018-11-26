import Vue from "vue";
import Vuex from "vuex";
import app from "./store/modules/app";
import errorLog from "./store/modules/errorLog";
import tagsView from "./store/modules/tagsView";
import user from "./store/modules/user";
import getters from "./store/getters";
import permission from "./store/modules/permission";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    app,
    errorLog,
    tagsView,
    user,
    permission
  },
  getters
});
