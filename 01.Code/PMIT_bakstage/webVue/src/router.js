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
    children: [
      {
        path: "dashboard",
        component: () => import("@/views/dashboard/index"),
        name: "Dashboard",
        meta: {
          title: "dashboard",
          icon: "home",
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
    path: "/backbone-report",
    component: Layout,
    redirect: "/backbone-report/overall-situation",
    name: "BackboneReport",
    meta: { title: "backboneReport", icon: "Viewlist", noCache: true },
    children: [
      {
        path: "overall-situation",
        component: () => import("@/views/dashboard/index"),
        name: "OverallSituation",
        meta: {
          title: "overallSituation",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "human-model",
        component: () => import("@/views/dashboard/index"),
        name: "HumanModal",
        meta: {
          title: "humanModal",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "role-report",
        component: () => import("@/views/dashboard/index"),
        name: "RoleReport",
        meta: {
          title: "roleReport",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: "/training-report",
    component: Layout,
    redirect: "noredirect",
    meta: { title: "trainingReport", icon: "online-tracking", noCache: true },
    children: [
      {
        path: "training-report",
        component: () => import("@/views/dashboard/index"),
        name: "TrainingReport",
        meta: {
          title: "trainingReport",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: "/examination-report",
    component: Layout,
    redirect: "noredirect",
    meta: { title: "examinationReport", icon: "comments", noCache: true },
    children: [
      {
        path: "examination-report",
        component: () => import("@/views/dashboard/index"),
        name: "ExaminationReport",
        meta: {
          title: "examinationReport",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: "/setting",
    component: Layout,
    redirect: "/setting/project-team",
    name: "Setting",
    meta: { title: "setting", icon: "set", noCache: true },
    children: [
      {
        path: "project-team",
        component: () => import("@/views/projectGroupSettings/index"),
        name: "ProjectTeam",
        meta: {
          title: "projectTeam",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "relationship",
        component: () => import("@/views/relationTreeSettings/index"),
        name: "Relationship",
        meta: {
          title: "relationship",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "course",
        component: () => import("@/views/dashboard/index"),
        name: "Course",
        meta: { title: "course", icon: "", noCache: true, requiresAuth: true }
      },
      {
        path: "training",
        component: () => import("@/views/dashboard/index"),
        name: "Training",
        meta: { title: "training", icon: "", noCache: true, requiresAuth: true }
      },
      {
        path: "examination",
        component: () => import("@/views/dashboard/index"),
        name: "Examination",
        meta: {
          title: "examination",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "import",
        component: () => import("@/views/dashboard/index"),
        name: "Import",
        meta: { title: "import", icon: "", noCache: true, requiresAuth: true }
      },
      {
        path: "permission",
        component: () => import("@/views/authorizationSettings/index"),
        name: "Permission",
        meta: {
          title: "permission",
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
