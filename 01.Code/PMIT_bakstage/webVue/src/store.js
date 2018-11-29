import Vue from "vue";
import Vuex from "vuex";
import appStore from "./store/modules/appStore";
import tagsViewStore from "./store/modules/tagsViewStore";
import userInfoStore from "./store/modules/userInfoStore";
import projectGroupStore from "./store/modules/projectGroupStore";
import relationShipStore from "./store/modules/relationShipStore";
import getters from "./store/getters";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    appStore,
    tagsViewStore,
    userInfoStore,
    projectGroupStore
  },
  getters
});
