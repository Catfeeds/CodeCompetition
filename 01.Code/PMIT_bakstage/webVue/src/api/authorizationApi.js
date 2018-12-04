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
function getLoginUserList(pageInfo) {
  return request({
    url: "hrbp/user/findEmpInformation",
    method: "post",
    data: pageInfo
  });
}
function getLoginUserById(employeeID) {
  return request({
    url: "hrbp/user/findEmpInformationByEmpId",
    method: "post",
    data: { employeeID }
  });
}
function addLoginUser(userInfo) {
  return request({
    url: "hrbp/user/insertUserRole",
    method: "post",
    data: userInfo
  });
}
function delLoginUser(employeeID) {
  return request({
    url: "hrbp/user/deleteUserRole",
    method: "post",
    data: { employeeID }
  });
}
function editLoginUser(userInfo) {
  return request({
    url: "hrbp/user/updateUserRole",
    method: "post",
    data: userInfo
  });
}
function searchEmployeeInfo(keyword) {
  return request({
    url: "hrbp/user/queryUserByKeyword",
    method: "post",
    data: { keyword }
  });
}
export default {
  getRoleList,
  getRoleInfoById,
  addRoleInfo,
  delRoleInfo,
  editRoleInfo,
  getLoginUserList,
  getLoginUserById,
  addLoginUser,
  delLoginUser,
  editLoginUser,
  searchEmployeeInfo
};
