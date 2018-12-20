import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);
import Layout from "@/views/layout/Layout";

export const constantRouterMap = [
  {
    path: "/redirect",
    component: Layout,
    hidden: true,
    children: [
      {
        path: "/redirect/:path*",
        component: () => import("@/views/redirect/index")
      }
    ]
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/Login.vue"),
    hidden: true
  },
  {
    path: "/personal",
    component: Layout,
    redirect: "noredirect",
    hidden: true,
    meta: { title: "personalCenter", icon: "table", noCache: true },
    children: [
      {
        path: "",
        component: () => import("@/views/systemSettings/personalCenter"),
        name: "PersonalCenter",
        meta: {
          title: "personalCenter",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      }
    ]
  },
  {
    path: "",
    component: Layout,
    redirect: "dashboard",
    meta: { title: "reportDisplay", icon: "table", noCache: true },
    children: [
      {
        path: "dashboard",
        component: () => import("@/views/reportDisplay/backboneReport"),
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
    path: "/employeeManagement",
    component: Layout,
    redirect: "/employeeManagement/employeeInfo",
    name: "StaffManagement",
    meta: { title: "personnelManagement", icon: "peoples", noCache: true },
    children: [
      {
        path: "employeeInfo",
        component: () => import("@/views/employeeManagement/employeeList"),
        name: "StaffInformation",
        meta: {
          title: "employeeInfo",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "employeeDetail/:id(\\d+)",
        component: () => import("@/views/employeeManagement/employeeDetail"),
        name: "EmployeeDetail",
        meta: {
          title: "employeeDetail",
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
    redirect: "/projectManagement/projectGroupConfiguration",
    name: "ProjectManagement",
    meta: { title: "projectManagement", icon: "component", noCache: true },
    children: [
      {
        path: "projectGroupConfiguration",
        component: () => import("@/views/projectManagement/projectGroupConfig"),
        name: "ProjectSetUp",
        meta: {
          title: "projectGroupConfiguration",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "teamSettings/:id",
        component: () => import("@/views/projectManagement/teamSettings"),
        name: "TeamSettings",
        meta: {
          title: "teamSettings",
          icon: "",
          noCache: true,
          requiresAuth: true
        },
        hidden: true
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
        component: () =>
          import("@/views/organizationalStructure/businessSystem"),
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
        component: () => import("@/views/organizationalStructure/humanSystem"),
        name: "StaffSystem",
        meta: {
          title: "humanSystem",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "postManagement",
        component: () =>
          import("@/views/organizationalStructure/postManagement"),
        name: "PostManagement",
        meta: {
          title: "postManagement",
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
        component: () => import("@/views/trainningAndTraining/trainSetting"),
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
        component: () => import("@/views/trainningAndTraining/affairSettiing"),
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
        component: () => import("@/views/trainningAndTraining/rulesSetting"),
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
        component: () =>
          import("@/views/trainningAndTraining/openClassSetting"),
        name: "OpeningSetUp",
        meta: {
          title: "courseOpen",
          noCache: true,
          icon: "",
          requiresAuth: true
        }
      },
      {
        path: "score",
        component: () => import("@/views/trainningAndTraining/scoreDisplay"),
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
        component: () => import("@/views/systemSettings/roleSetting.vue"),
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
        component: () => import("@/views/systemSettings/loginUser.vue"),
        name: "UserManagement",
        meta: {
          title: "loginUser",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "backup",
        component: () => import("@/views/systemSettings/backupRestore.vue"),
        name: "BackupRestore",
        meta: {
          title: "backupRestore",
          icon: "",
          noCache: true,
          requiresAuth: true
        }
      },
      {
        path: "log",
        component: () => import("@/views/systemSettings/logManagement.vue"),
        name: "LogManagement",
        meta: {
          title: "logManagement",
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
