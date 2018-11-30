import request from "@/utils/request";
function getRoleList() {
  return request({
    url: "hrbp/system/queryAllRoles",
    method: "post"
  });
}
function getRoleInfoById(roleId) {
  return request({
    url: "hrbp/system/querySystemRoleById",
    method: "post",
    data: { roleId }
  });
}
function addRoleInfo(roleInfo) {
  return request({
    url: "hrbp/system/addRole",
    method: "post",
    data: roleInfo
  });
}
function delRoleInfo(roleId) {
  return request({
    url: "hrbp/system/deleteSystemRole",
    method: "post",
    data: { roleId }
  });
}
function editRoleInfo(roleInfo) {
  return request({
    url: "hrbp/system/updateSystemRole",
    method: "post",
    data: roleInfo
  });
}
function getLoginUserList() {
  return request({
    url: "permission/getLoginUserList",
    method: "get"
  });
}
function addLoginUser(userInfo) {
  return request({
    url: "permission/addLoginUser",
    method: "post",
    data: userInfo
  });
}
function delLoginUser(id) {
  return request({
    url: "permission/delLoginUser",
    method: "post",
    data: { id: id }
  });
}
function editLoginUser(userInfo) {
  return request({
    url: "permission/editLoginUser",
    method: "post",
    data: userInfo
  });
}
export default {
  getRoleList,
  getRoleInfoById,
  addRoleInfo,
  delRoleInfo,
  editRoleInfo,
  getLoginUserList,
  addLoginUser,
  delLoginUser,
  editLoginUser
};
