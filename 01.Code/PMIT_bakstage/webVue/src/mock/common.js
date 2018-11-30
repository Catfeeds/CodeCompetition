export default {
  getMenu: () => {
    return [
      {
        menuId: 1,
        parentId: 0,
        menuName: "BackboneReport",
        note: "骨干报表"
      },
      {
        menuId: 2,
        parentId: 0,
        menuName: "System",
        note: "系统设置"
      },
      {
        menuId: 201,
        parentId: 2,
        menuName: "ProjectTeam",
        note: "项目组设置"
      },
      {
        menuId: 202,
        parentId: 2,
        menuName: "RelationTree",
        note: "关系树设置"
      },
      {
        menuId: 203,
        parentId: 2,
        menuName: "Training",
        note: "培训设置"
      },
      {
        menuId: 204,
        parentId: 2,
        menuName: "ExaminationAffairs",
        note: "考核事务设置"
      },
      {
        menuId: 205,
        parentId: 2,
        menuName: "ExaminationRules",
        note: "考核规则设置"
      },
      {
        menuId: 206,
        parentId: 2,
        menuName: "CourseOpen",
        note: "开班设置"
      },
      {
        menuId: 207,
        parentId: 2,
        menuName: "Examination",
        note: "考试设置"
      },
      {
        menuId: 208,
        parentId: 2,
        menuName: "Import",
        note: "成绩导入"
      },
      {
        menuId: 209,
        parentId: 2,
        menuName: "Role",
        note: "角色设置"
      },
      {
        menuId: 210,
        parentId: 2,
        menuName: "Login",
        note: "登录用户设置"
      }
    ];
  }
};
