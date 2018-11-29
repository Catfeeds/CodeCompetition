import request from "@/utils/request";
function getRoleList() {
  return request({
    url: "permission/getRoleList",
    method: "get"
  });
}
function addRoleInfo(roleInfo) {
  return request({
    url: "permission/addRoleInfo",
    method: "post",
    data: roleInfo
  });
}
function delRoleInfo(id) {
  return request({
    url: "permission/delRoleInfo",
    method: "post",
    data: { id: id }
  });
}
function editRoleInfo(roleInfo) {
  return request({
    url: "permission/editRoleInfo",
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
  addRoleInfo,
  delRoleInfo,
  editRoleInfo,
  getLoginUserList,
  addLoginUser,
  delLoginUser,
  editLoginUser
};
