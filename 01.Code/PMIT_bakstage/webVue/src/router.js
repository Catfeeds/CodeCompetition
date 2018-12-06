import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);
import Layout from "@/views/layout/Layout";

export const constantRouterMap = [
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/Login.vue"),
    hidden: true
  },
  {
    path: "",
    component: Layout,
    redirect: "dashboard",
    meta: { title: "reportDisplay", icon: "table", noCache: true },
    children: [
      {
        path: "dashboard",
        component: () => import("@/views/dashboard/index"),
        name: "StoneReport",
        meta: {
          title: "backboneReport",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: "/404",
    component: () => import("@/views/errorPage/404"),
    hidden: true
  },
  {
    path: "/401",
    component: () => import("@/views/errorPage/401"),
    hidden: true
  },
  {
    path: "*",
    redirect: "/404"
  }
];
export const asyncRouterMap = [
  {
    path: "/personnelManagement",
    component: Layout,
    redirect: "/personnelManagement/empolyeeInfo",
    name: "StaffManagement",
    meta: { title: "personnelManagement", icon: "peoples", noCache: true },
    children: [
      {
        path: "empolyeeInfo",
        component: () => import("@/views/personnelManagement/personnelList"),
        name: "StaffInformation",
        meta: {
          title: "empolyeeInfo",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "empolyeeDetail/:id(\\d+)",
        component: () => import("@/views/personnelManagement/personnelDetail"),
        // name: "StaffInformation",
        meta: {
          title: "empolyeeDetail",
          icon: "",
          noCache: true,
          requiresAuth: true
        },
        hidden: true
      }
    ]
  },
  {
    path: "/projectManagement",
    component: Layout,
    redirect: "noredirect",
    name: "ProjectManagement",
    meta: { title: "projectManagement", icon: "component", noCache: true },
    children: [
      {
        path: "projectGroupConfiguration",
        component: () => import("@/views/projectGroupSettings/index"),
        name: "ProjectSetUp",
        meta: {
          title: "projectGroupConfiguration",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: "/organizationalStructure",
    component: Layout,
    redirect: "/organizationalStructure/businessSystem",
    name: "OrganizationalStructure",
    meta: { title: "organizationalStructure", icon: "tree", noCache: true },
    children: [
      {
        path: "businessSystem",
        component: () => import("@/views/relationTreeSettings/index"),
        name: "BusinessSystem",
        meta: {
          title: "businessSystem",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "humanSystem",
        component: () => import("@/views/dashboard/index"),
        name: "StaffSystem",
        meta: {
          title: "humanSystem",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: "/training",
    component: Layout,
    redirect: "/training/trainingSetting",
    name: "TrainingPlan",
    meta: { title: "training", icon: "form", noCache: true },
    children: [
      {
        path: "trainingSetting",
        component: () => import("@/views/trainingSettings/index"),
        name: "TrainSetUp",
        meta: {
          title: "trainingSetting",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "assessmentAffairs",
        component: () => import("@/views/assessmentAffairsSettings/index"),
        name: "ExaminationAffairsSetUp",
        meta: {
          title: "assessmentAffairs",
          noCache: true,
          icon: "",
          requiresAuth: true
        }
      },
      {
        path: "assessmentRules",
        component: () => import("@/views/assessmentRulesSettings/index"),
        name: "ExaminationRoleSetUp",
        meta: {
          title: "assessmentRules",
          noCache: true,
          icon: "",
          requiresAuth: true
        }
      },
      {
        path: "course",
        component: () => import("@/views/courseOpenSettings/index"),
        name: "OpeningSetUp",
        meta: {
          title: "courseOpen",
          noCache: true,
          icon: "",
          requiresAuth: true
        }
      },
      {
        path: "examination",
        component: () => import("@/views/examSettings/index"),
        name: "ExaminationSetUp",
        meta: {
          title: "examination",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "score",
        component: () => import("@/views/scoreImport/index"),
        name: "ResultShow",
        meta: { title: "score", icon: "", noCache: true, requiresAuth: true }
      }
    ]
  },
  {
    path: "/setting",
    component: Layout,
    redirect: "/setting/projectGroup",
    name: "System",
    meta: { title: "setting", icon: "set", noCache: true },
    children: [
      {
        path: "role",
        component: () => import("@/views/authorizationSettings/role.vue"),
        name: "RoleSetUp",
        meta: {
          title: "role",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "loginUser",
        component: () => import("@/views/authorizationSettings/loginUser.vue"),
        name: "UserManagement",
        meta: {
          title: "loginUser",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      }
    ]
  }
];
export default new Router({
  routes: constantRouterMap.concat(asyncRouterMap)
});
