import Vue from "vue";
import Vuex from "vuex";
import appStore from "./store/modules/appStore";
import tagsViewStore from "./store/modules/tagsViewStore";
import userInfoStore from "./store/modules/userInfoStore";
import projectGroupStore from "./store/modules/projectGroupStore";
import authorizationStore from "./store/modules/authorizationStore";
import trainingStore from "./store/modules/trainingStore";
import affairsStore from "./store/modules/affairsStore";
import ruleStore from "./store/modules/ruleStore";
import employeeStore from "./store/modules/employeeStore";
import reportDisplayStore from "./store/modules/reportDisplayStore";
import scoreDisplayStore from "./store/modules/scoreDisplayStore";
import eSettingsStore from "./store/modules/employeeSettingsStore";
import associatedPOStore from "./store/modules/associatedPOStore";
import keyRoleStore from "./store/modules/keyRoleStore";
import openClassStore from "./store/modules/openClassStore";
import orgStructureStore from "./store/modules/orgStructureStore";
import postStore from "./store/modules/postStore";
import personalCenterStore from "./store/modules/personalCenterStore";
import getters from "./store/getters";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    appStore,
    tagsViewStore,
    userInfoStore,
    projectGroupStore,
    authorizationStore,
    trainingStore,
    affairsStore,
    ruleStore,
    employeeStore,
    reportDisplayStore,
    scoreDisplayStore,
    eSettingsStore,
    associatedPOStore,
    keyRoleStore,
    openClassStore,
    orgStructureStore,
    postStore,
    personalCenterStore
  },
  getters
});
