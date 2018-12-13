import request from "@/utils/request";
/**
 * 根据PDU查询区域和成本中心
 * @param {*} pdu
 */
function getAreaAndCuBycondition(pdu) {
  return request({
    url: "hrbp/TeamManage/queryAreaAndCuBycondition",
    method: "post",
    data: { pdu: pdu || "" }
  });
}
/**
 * 查询资源池人员
 * @param {*} param
 */
function getEmployees(param) {
  return request({
    url: "hrbp/TeamManage/searchEmployeeInfos",
    method: "post",
    data: param || {}
  });
}
/**
 * 查询项目组的人员信息
 * @param {*} teamId 项目组ID
 */
function getEmployeesByTeam(teamId) {
  return request({
    url: "hrbp/TeamManage/getTeamInfos",
    method: "post",
    data: { teamId }
  });
}
/**
 * 保存项目人员信息
 * @param {*} teamInfos {teamId,teamName,staffId,staffName}
 */
function saveTeamInfos(teamInfos) {
  return request({
    url: "hrbp/TeamManage/saveTeamInfos",
    method: "post",
    data: { teamInfos }
  });
}
/**
 * 查询PO信息
 * @param {*} params
 */
function getProjectInfo(params) {
  return request({
    url: "hrbp/TeamManage/queryProjectInformation",
    method: "post",
    data: params || {}
  });
}
/**
 * 保存项目组和PO关联信息
 * @param {*} params
 */
function teamRelatedPO(params) {
  return request({
    url: "hrbp/TeamManage/teamRelatedPo",
    method: "post",
    data: params || {}
  });
}
/**
 * 保存关键角色
 * @param {*} params
 */
function saveProjectRole(params) {
  return request({
    url: "hrbp/TeamManage/saveProjectRole",
    method: "post",
    data: params || {}
  });
}
/**
 * 查询关键角色信息
 */
function getProjectRole() {
  return request({
    url: "hrbp/TeamManage/queryProjectRole",
    method: "post"
  });
}
/**
 * 查询项目管理PO信息
 * @param {*} param {teamId,startDate,endDate}
 */
function getPrjectRelatedPO(param) {
  return request({
    url: "hrbp/TeamManage/queryteamRelatedPoByProjectTime",
    method: "post",
    data: param
  });
}
/**
 * 删除项目关联PO
 * @param {*} param {teamId, projectId}
 */
function delTeamRelatedPO(param) {
  return request({
    url: "hrbp/TeamManage/deleteteamRelatedPo",
    method: "post",
    data: param
  });
}
/**
 * 查询项目关键角色
 * @param {*} param {projectId} POID
 * @param {*} param {teamId} 项目组ID
 * @param {*} param {poRoleId} PO角色ID
 */
function getProjectKeyRoleInfo(projectId, teamId, poRoleId) {
  return request({
    url: "hrbp/TeamManage/queryPoStaffInfo",
    method: "post",
    data: { projectId, teamId, poRoleId }
  });
}
/**
 * 删除PO中的关键角色
 * @param {*} param {projectId} POID
 * @param {*} teamId 项目ID
 * @param {*} poRoleId PO关键角色ID
 */
function delTeamPOKeyRole(projectId, teamId, poRoleId) {
  return request({
    url: "hrbp/TeamManage/deletePoStaffInfo",
    method: "post",
    data: { projectId, teamId, poRoleId }
  });
}
export default {
  getAreaAndCuBycondition,
  getEmployees,
  getEmployeesByTeam,
  saveTeamInfos,
  getProjectInfo,
  teamRelatedPO,
  saveProjectRole,
  getProjectRole,
  getPrjectRelatedPO,
  delTeamRelatedPO,
  getProjectKeyRoleInfo,
  delTeamPOKeyRole
};
