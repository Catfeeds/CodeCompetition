import request from "@/utils/request";
function getRoleList(condition) {
  return request({
    url: "hrbp/affair/findAllAffair",
    method: "post",
    data: condition
  });
}
function delRoleInfo(roleId) {
  return request({
    url: "hrbp/affair/deleteAffair",
    method: "post",
    data: { roleId }
  });
}
function addRoleInfo(roleInfo) {
  return request({
    url: "hrbp/affair/insertAffair",
    method: "post",
    data: roleInfo
  });
}
function editRoleInfo(roleInfo) {
  return request({
    url: "hrbp/affair/updateAffair",
    method: "post",
    data: roleInfo
  });
}
function getRuleList(condition) {
  return request({
    url: "hrbp/affair/findAllAffair",
    method: "post",
    data: condition
  });
}
function addRuleInfo(ruleInfo) {
  return request({
    url: "hrbp/dimension/insertDimension",
    method: "post",
    data: ruleInfo
  });
}
function editRuleInfo(ruleInfo) {
  return request({
    url: "hrbp/dimension/insertDimension",
    method: "post",
    data: ruleInfo
  });
}
function delRuleInfo(ruleId) {
  return request({
    url: "hrbp/dimension/insertDimension",
    method: "post",
    data: { ruleId }
  });
}

export default {
  getRoleList,
  delRoleInfo,
  addRoleInfo,
  editRoleInfo,
  getRuleList,
  addRuleInfo,
  editRuleInfo,
  delRuleInfo
};
