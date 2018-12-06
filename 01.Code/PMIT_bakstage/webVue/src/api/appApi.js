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
    url: "hrbp/trainingsetting/queryBu",
    method: "post"
  });
}
function getDUInfo(product) {
  return request({
    url: "projectGroup/getDUInfo",
    method: "get",
    params: { product: product }
  });
}
function getPDUInfo(product, du) {
  return request({
    url: "projectGroup/getPDUInfo",
    method: "get",
    params: { product: product, du: du }
  });
}
export default {
  getAllMenuInfo,
  getMenuInfoByEmployeeId,
  getMenuInfoByRoleId,
  getProductInfo,
  getDUInfo,
  getPDUInfo
};
