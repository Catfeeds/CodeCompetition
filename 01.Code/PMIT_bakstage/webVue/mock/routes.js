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
  "/hrbp/MainstayReportContraer/queryScore":"/queryScore",
  "hrbp/projectManage/queryProjects":"queryProjects",
  "hrbp/TeamManage/queryAreaAndCuBycondition":"/queryAreaAndCuBycondition",
  "hrbp/TeamManage/searchEmployeeInfos":"/searchEmployeeInfos",
  "hrbp/TeamManage/getTeamInfos":"getTeamInfos",
  "hrbp/TeamManage/queryProjectInformation":"queryProjectInformation"
};

module.exports = routes;
