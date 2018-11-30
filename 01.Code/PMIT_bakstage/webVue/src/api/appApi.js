import request from "@/utils/request";
export function getAllMenuInfo() {
  return request({ url: "hrbp/menuManage/getAllMenuList", method: "get" });
}
export function getMenuInfoByRole(roleId) {
  return request({
    url: "hrbp/menuManage/getMenuList",
    method: "get",
    param: { role: roleId }
  });
}
export function getProductInfo() {
  return request({
    url: "projectGroup/getProductInfo",
    method: "get"
  });
}
