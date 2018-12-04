export default {
  route: {
    dashboard: "首页",
    reportDisplay: "报表展示",
    backboneReport: "骨干报表",
    personnelManagement: "人员管理",
    empolyeeDetail: "员工详情",
    projectManagement: "项目管理",
    projectGroupConfiguration: "项目组配置",
    organizationalStructure: "组织结构",
    businessSystem: "业务体系",
    humanSystem: "人力体系",
    setting: "系统设置",
    assessmentAffairs: "考核事务设置",
    assessmentRules: "考核规则设置",
    training: "培训培养",
    trainingSetting: "培训设置",
    examination: "考试设置",
    score: "成绩展示",
    role: "角色设置",
    loginUser: "登录用户管理",
    courseOpen: "开班设置"
  },
  navbar: {
    logOut: "Log Out",
    dashboard: "Dashboard",
    github: "Github",
    screenfull: "Screenfull",
    theme: "Theme",
    size: "Global Size"
  },
  login: {
    title: "骨干管理系统",
    logIn: "Log in",
    userName: "Username",
    password: "Password",
    forgetPassword: "密码提示",
    forgetPasswordTips: "请联系管理员重置你的密码！",
    passwordTips: "The password can not be less than 6 digits",
    userNameTips: "Please enter the correct user name",
    freeLogin: "十天内免登录",
    forgetPasswordTitle: "忘记密码?"
  },
  documentation: {
    documentation: "Documentation",
    github: "Github Repository"
  },
  permission: {
    roles: "Your roles",
    switchRoles: "Switch roles"
  },
  guide: {
    description:
      "The guide page is useful for some people who entered the project for the first time. You can briefly introduce the features of the project. Demo is based on ",
    button: "Show Guide"
  },
  components: {
    documentation: "Documentation",
    tinymceTips:
      "Rich text editor is a core part of management system, but at the same time is a place with lots of problems. In the process of selecting rich texts, I also walked a lot of detours. The common rich text editors in the market are basically used, and the finally chose Tinymce. See documentation for more detailed rich text editor comparisons and introductions.",
    dropzoneTips:
      "Because my business has special needs, and has to upload images to qiniu, so instead of a third party, I chose encapsulate it by myself. It is very simple, you can see the detail code in @/components/Dropzone.",
    stickyTips:
      "when the page is scrolled to the preset position will be sticky on the top.",
    backToTopTips1:
      "When the page is scrolled to the specified position, the Back to Top button appears in the lower right corner",
    backToTopTips2:
      "You can customize the style of the button, show / hide, height of appearance, height of the return. If you need a text prompt, you can use element-ui el-tooltip elements externally",
    imageUploadTips:
      "Since I was using only the vue@1 version, and it is not compatible with mockjs at the moment, I modified it myself, and if you are going to use it, it is better to use official version."
  },
  projectGroup: {
    product: "产品线",
    teamName: "项目组名称"
  },
  table: {
    id: "ID",
    option: "Option",
    delete: "Delete",
    cancel: "Cancel",
    confirm: "Confirm",
    search: "Search",
    add: "Add"
  },
  errorLog: {
    tips: "Please click the bug icon in the upper right corner",
    description:
      "Now the management system are basically the form of the spa, it enhances the user experience, but it also increases the possibility of page problems, a small negligence may lead to the entire page deadlock. Fortunately Vue provides a way to catch handling exceptions, where you can handle errors or report exceptions.",
    documentation: "Document introduction"
  },
  excel: {
    export: "Export",
    selectedExport: "Export Selected Items",
    placeholder: "Please enter the file name(default excel-list)"
  },
  zip: {
    export: "Export",
    placeholder: "Please enter the file name(default file)"
  },
  theme: {
    change: "Change Theme",
    documentation: "Theme documentation",
    tips:
      "Tips: It is different from the theme-pick on the navbar is two different skinning methods, each with different application scenarios. Refer to the documentation for details."
  },
  tagsView: {
    refresh: "Refresh",
    close: "Close",
    closeOthers: "Close Others",
    closeAll: "Close All"
  }
};
