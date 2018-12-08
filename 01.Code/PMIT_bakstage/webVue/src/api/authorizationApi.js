import request from "@/utils/request";
function getSysRoleList() {
  return request({
    url: "hrbp/system/queryAllRoles",
    method: "post"
  });
}
function getSysRoleInfoById(roleId) {
  return request({
    url: "hrbp/system/querySystemRoleById",
    method: "post",
    data: { roleId }
  });
}
function addSysRoleInfo(roleInfo) {
  return request({
    url: "hrbp/system/addRole",
    method: "post",
    data: roleInfo
  });
}
function delSysRoleInfo(roleId) {
  return request({
    url: "hrbp/system/deleteSystemRole",
    method: "post",
    data: { roleId }
  });
}
function editSysRoleInfo(roleInfo) {
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
function backup(condition) {
  return request({
    url: "hrbp/backup/dataDackup",
    method: "post",
    data: condition
  });
}
function restore(fileName) {
  return request({
    url: "hrbp/backup/dataRecoverp",
    method: "post",
    data: { fileName }
  });
}
function getBackupList(condition) {
  return request({
    url: "hrbp/backup/dataInfoModifList",
    method: "post",
    data: condition
  });
}
export default {
  getSysRoleList,
  getSysRoleInfoById,
  addSysRoleInfo,
  delSysRoleInfo,
  editSysRoleInfo,
  getLoginUserList,
  getLoginUserById,
  addLoginUser,
  delLoginUser,
  editLoginUser,
  searchEmployeeInfo,
  backup,
  restore,
  getBackupList
};
