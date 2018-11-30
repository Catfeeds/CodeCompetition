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
  // {
  //   path: "",
  //   component: Layout,
  //   redirect: "dashboard",
  //   children: [
  //     {
  //       path: "dashboard",
  //       component: () => import("@/views/dashboard/index"),
  //       name: "Dashboard",
  //       meta: {
  //         title: "dashboard",
  //         icon: "home",
  //         noCache: true,
  //         requiresAuth: true
  //       }
  //     }
  //   ]
  // },
  {
    path: "",
    component: Layout,
    redirect: "dashboard",
    meta: { title: "backboneReport", icon: "Viewlist", noCache: true },
    children: [
      {
        path: "dashboard",
        component: () => import("@/views/dashboard/index"),
        name: "BackboneReport",
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
    path: "/setting",
    component: Layout,
    redirect: "/setting/projectGroup",
    name: "Setting",
    meta: { title: "setting", icon: "set", noCache: true },
    children: [
      {
        path: "projectGroup",
        component: () => import("@/views/projectGroupSettings/index"),
        name: "ProjectTeam",
        meta: {
          title: "projectGroup",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "relationTree",
        component: () => import("@/views/relationTreeSettings/index"),
        name: "RelationTree",
        meta: {
          title: "relationTree",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "training",
        component: () => import("@/views/trainingSettings/index"),
        name: "Training",
        meta: { title: "training", icon: "", noCache: true, requiresAuth: true }
      },
      {
        path: "assessmentAffairs",
        component: () => import("@/views/assessmentAffairsSettings/index"),
        name: "ExaminationAffairs",
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
        name: "ExaminationRules",
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
        name: "CourseOpen",
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
        component: () => import("@/views/scoreImport/index"),
        name: "Import",
        meta: { title: "import", icon: "", noCache: true, requiresAuth: true }
      },
      {
        path: "role",
        component: () => import("@/views/authorizationSettings/role.vue"),
        name: "Role",
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
        name: "Login",
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
