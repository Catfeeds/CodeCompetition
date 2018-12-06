const routes = {
  "/hrbp/menuManage/getMenuTreeByEmpID": "/menu",
  "/hrbp/menuManage/getAllMenuList": "/menu",
  "/hrbp/system/queryAllRoles": "/roleList",
  "/hrbp/menuManage/getMenuTreeByRoleId": "/menuByRole",
  "/hrbp/user/findEmpInformation": "/loginUserList",
  "/hrbp/user/queryUserByKeyword": "/findLoginUser",
  "/hrbp/user/findEmpInformationByEmpId": "/loginUserInfo",
  "/hrbp/MainstayReportContraer/queryMainstayProportionChart":"/getRate",
  "/hrbp/MainstayReportContraer/queryMainstayByBU":"/getStatisByDepartment",
  "/hrbp/MainstayReportContraer/queryMainstayByPost":"/getPositionDistribute",
  "/hrbp/MainstayReportContraer/queryMainstayTraining":"/getTraniningStatis",
  "/hrbp/MainstayReportContraer/queryMainstayAge":"/getAgeDistribute",
  "/hrbp/MainstayReportContraer/queryMainstayTable":"/getEmployee",
  "/hrbp/MainstayReportContraer/queryScore":"/queryScore"
};

module.exports = routes;
