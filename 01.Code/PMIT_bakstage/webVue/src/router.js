import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);
import Layout from "@/views/layout/Layout";
export const constantRouterMap = [
  {
    path: "/login",
    name: "login",
    component: () => import("./views/login/index.vue"),
    hidden: true
  },
  {
    path: "",
    component: Layout,
    redirect: "dashboard",
    meta: { title: "首页", icon: "dashboard", noCache: true },
    children: [
      {
        path: "dashboard",
        component: () => import("@/views/dashboard/index"),
        name: "Dashboard",
        meta: { title: "首页", icon: "dashboard", noCache: true }
      }
    ]
  },
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
    path: "/404",
    component: () => import("@/views/errorPage/404"),
    hidden: true
  },
  {
    path: "/401",
    component: () => import("@/views/errorPage/401"),
    hidden: true
  }
];
export const asyncRouterMap = [
  {
    path: "/backbone-report",
    component: Layout,
    redirect: "/backbone-report/overall-situation",
    name: "BackboneReport",
    meta: { title: "骨干报表", icon: "dashboard", noCache: true },
    children: [
      {
        path: "overall-situation",
        component: () => import("@/views/dashboard/index"),
        name: "OverallSituation",
        meta: { title: "整体情况", icon: "dashboard", noCache: true }
      },
      {
        path: "human-model",
        component: () => import("@/views/dashboard/index"),
        name: "HumanModal",
        meta: { title: "人力模型", icon: "dashboard", noCache: true }
      },
      {
        path: "role-report",
        component: () => import("@/views/dashboard/index"),
        name: "RoleReport",
        meta: { title: "角色报表", icon: "dashboard", noCache: true }
      }
    ]
  },
  {
    path: "/training-report",
    component: Layout,
    redirect: "noredirect",
    meta: { title: "培训报表", icon: "dashboard", noCache: true },
    children: [
      {
        path: "training-report1",
        component: () => import("@/views/dashboard/index"),
        name: "TrainingReport",
        meta: { title: "培训报表", icon: "dashboard", noCache: true }
      }
    ]
  },
  {
    path: "/examination-report",
    component: Layout,
    redirect: "noredirect",
    meta: { title: "考试报表", icon: "dashboard", noCache: true },
    children: [
      {
        path: "examination-report1",
        component: () => import("@/views/dashboard/index"),
        name: "ExaminationReport",
        meta: { title: "考试报表", icon: "dashboard", noCache: true }
      }
    ]
  },
  {
    path: "/setting",
    component: Layout,
    redirect: "/setting/project-team",
    name: "Setting",
    meta: { title: "系统设置", icon: "dashboard", noCache: true },
    children: [
      {
        path: "project-team",
        component: () => import("@/views/dashboard/index"),
        name: "ProjectTeam",
        meta: { title: "项目组设置", icon: "dashboard", noCache: true }
      },
      {
        path: "relationship",
        component: () => import("@/views/dashboard/index"),
        name: "Relationship",
        meta: { title: "关系设置", icon: "dashboard", noCache: true }
      },
      {
        path: "course",
        component: () => import("@/views/dashboard/index"),
        name: "Course",
        meta: { title: "课程设置", icon: "dashboard", noCache: true }
      },
      {
        path: "training",
        component: () => import("@/views/dashboard/index"),
        name: "Training",
        meta: { title: "培训设置", icon: "dashboard", noCache: true }
      },
      {
        path: "examination",
        component: () => import("@/views/dashboard/index"),
        name: "Examination",
        meta: { title: "考试设置", icon: "dashboard", noCache: true }
      },
      {
        path: "import",
        component: () => import("@/views/dashboard/index"),
        name: "Import",
        meta: { title: "导入", icon: "dashboard", noCache: true }
      },
      {
        path: "permission",
        component: () => import("@/views/dashboard/index"),
        name: "Permission",
        meta: { title: "权限设置", icon: "dashboard", noCache: true }
      }
    ]
  }
];
export default new Router({
  routes: constantRouterMap.concat(asyncRouterMap)
});
