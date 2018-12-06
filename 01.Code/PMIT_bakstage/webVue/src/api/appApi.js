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
function getProductInfo() {
  return request({
    url: "hrbp/levelBussinessRelationship/queryLevel",
    method: "post",
    data: {}
  });
}
function getDUInfo(bu) {
  return request({
    url: "hrbp/levelBussinessRelationship/queryLevel",
    method: "post",
    data: { bu }
  });
}
function getPDUInfo(bu, du) {
  return request({
    url: "hrbp/levelBussinessRelationship/queryLevel",
    method: "post",
    data: { bu, du }
  });
}
function getAreaInfo(bu, du, pdu) {
  return request({
    url: "hrbp/levelBussinessRelationship/queryLevel",
    method: "post",
    data: { bu, du, pdu }
  });
}
export default {
  getAllMenuInfo,
  getMenuInfoByEmployeeId,
  getMenuInfoByRoleId,
  getProductInfo,
  getDUInfo,
  getPDUInfo,
  getAreaInfo
};
