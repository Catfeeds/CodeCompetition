import request from "@/utils/request";
function getAllMenuInfo() {
  return request({ url: "hrbp/menuManage/getAllMenuList", method: "post" });
}
function getMenuInfoByEmployeeId(employeeID) {
  return request({
    url: "hrbp/menuManage/getMenuTreeByEmpID",
    method: "post",
    data: { employeeID }
  });
}
function getMenuInfoByRoleId(roleId) {
  return request({
    url: "hrbp/menuManage/getMenuTreeByRoleId",
    method: "post",
    data: { roleId }
  });
}
function getBusinessRelationship(param) {
  return request({
    url: "hrbp/levelBussinessRelationship/queryLevel",
    method: "post",
    data: param
  });
}
function getBU() {
  return request({
    url: "hrbp/trainingsetting/queryBu",
    method: "post"
  });
}
function getDU(bu) {
  return request({
    url: "hrbp/trainingsetting/queryDu",
    method: "post",
    data: { bu }
  });
}
function getPDU(bu, du) {
  return request({
    url: "hrbp/trainingsetting/queryPdu",
    method: "post",
    data: { bu, du }
  });
}
function getLogList(condition) {
  return request({
    url: "hrbp/levelBussinessRelationship/queryLevel",
    method: "post",
    data: condition
  });
}
export default {
  getAllMenuInfo,
  getMenuInfoByEmployeeId,
  getMenuInfoByRoleId,
  getBusinessRelationship,
  getLogList,
  getBU,
  getDU,
  getPDU
};
