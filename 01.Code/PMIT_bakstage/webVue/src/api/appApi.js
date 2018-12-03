import request from "@/utils/request";
export function getAllMenuInfo() {
  return request({ url: "hrbp/menuManage/getAllMenuList", method: "post" });
}
export function getMenuInfoByEmployeeId(employeeID) {
  return request({
    url: "hrbp/menuManage/getMenuTreeByEmpID",
    method: "post",
    data: { employeeID }
  });
}
export function getMenuInfoByRoleId(roleId) {
  return request({
    url: "hrbp/menuManage/getMenuTreeByRoleId",
    method: "post",
    data: { roleId }
  });
}
export function getProductInfo() {
  return request({
    url: "hrbp/trainingsetting/queryBu",
    method: "post"
  });
}
