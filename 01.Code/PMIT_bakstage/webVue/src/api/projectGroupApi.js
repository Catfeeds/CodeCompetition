import request from "@/utils/request";
function getProjectGroupInfo(param) {
  return request({
    url: "/hrbp/projectManage/queryProjects",
    method: "post",
    data: param
  });
}
function getProjectGroupInfoById(teamID) {
  return request({
    url: "/hrbp/projectManage/queryProjects",
    method: "post",
    data: { teamID: teamID, pageNo: 1, pageSize: 1 }
  });
}
function addProjectNode(param) {
  return request({
    url: "/hrbp/projectManage/addProjectNode",
    method: "post",
    data: param
  });
}
function delProjectNode(projectID) {
  return request({
    url: "hrbp/projectManage/deleteProject",
    method: "post",
    data: { projectID }
  });
}
function queryProjectLevel(param) {
  return request({
    url: "/hrbp/projectManage/queryProjectLevel",
    method: "post",
    data: param
  });
}
function queryProjectManagers(condition) {
  return request({
    url: "hrbp/projectManage/queryPMs",
    method: "post",
    data: condition
  });
}
export default {
  getProjectGroupInfo,
  getProjectGroupInfoById,
  addProjectNode,
  queryProjectLevel,
  delProjectNode,
  queryProjectManagers
};
