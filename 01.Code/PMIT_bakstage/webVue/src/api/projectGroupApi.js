import request from "@/utils/request";
function getProjectGroupInfo(param) {
  return request({
    url: "/hrbp/projectManage/queryProjects",
    method: "post",
    data: param
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

export default {
  getProjectGroupInfo,
  addProjectNode,
  queryProjectLevel,
  delProjectNode
};
