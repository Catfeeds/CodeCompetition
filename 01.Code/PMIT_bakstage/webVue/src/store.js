import Vue from "vue";
import Vuex from "vuex";
import appStore from "./store/modules/appStore";
import tagsViewStore from "./store/modules/tagsViewStore";
import userInfoStore from "./store/modules/userInfoStore";
import projectGroupStore from "./store/modules/projectGroupStore";
import relationTreeStore from "./store/modules/relationTreeStore";
import authorizationStore from "./store/modules/authorizationStore";
import trainingStore from "./store/modules/trainingStore";
import affairsStore from "./store/modules/affairsStore";
import ruleStore from "./store/modules/ruleStore";
import employeeStore from "./store/modules/employeeStore";
import reportDisplayStore from "./store/modules/reportDisplayStore";
import scoreDisplayStore from "./store/modules/scoreDisplayStore";
import getters from "./store/getters";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    appStore,
    tagsViewStore,
    userInfoStore,
    projectGroupStore,
    relationTreeStore,
    authorizationStore,
    trainingStore,
    affairsStore,
    ruleStore,
    employeeStore,
    reportDisplayStore,
    scoreDisplayStore
  },
  getters
});
