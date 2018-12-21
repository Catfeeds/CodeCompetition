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
    url: "hrbp/eRules/queryExaminationRulesInfo",
    method: "post",
    data: condition
  });
}
function addRuleInfo(ruleInfo) {
  return request({
    url: "hrbp/eRules/saveExaminationRulesInfo",
    method: "post",
    data: ruleInfo
  });
}
function getRuleInfoById(ruleId) {
  return request({
    url: "hrbp/eRules/querySigleRuleInfo",
    method: "post",
    data: { ruleId }
  });
}
function delRuleInfo(ruleId) {
  return request({
    url: "hrbp/eRules/deleteExaminationRulesInfo",
    method: "post",
    data: { ruleId }
  });
}
function queryRoleList() {
  return request({
    url: "hrbp/eRules/queryAllRole",
    method: "post"
  });
}
export default {
  getRoleList,
  delRoleInfo,
  saveRoleInfo,
  getRuleList,
  addRuleInfo,
  getRuleInfoById,
  delRuleInfo,
  queryRoleList
};
