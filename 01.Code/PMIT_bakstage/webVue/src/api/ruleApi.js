import request from "@/utils/request";
function getRoleList(condition) {
  return request({
    url: "hrbp/eRules/querySpecialRolesInfo",
    method: "post",
    data: condition
  });
}
function delRoleInfo(roleId) {
  return request({
    url: "hrbp/eRules/deleteSpecialRole",
    method: "post",
    data: { roleId }
  });
}
function saveRoleInfo(roleInfo) {
  return request({
    url: "hrbp/eRules/saveSpecialRole",
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
  saveRoleInfo,
  getRuleList,
  addRuleInfo,
  editRuleInfo,
  delRuleInfo
};
