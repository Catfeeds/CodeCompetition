import request from "@/utils/request";
function getTreeData(type) {
  return request({
    url: "hrbp/LevelTree/queryAllTree",
    method: "post",
    data: { type }
  });
}
function addTreeNode(param) {
  return request({
    url: "hrbp/LevelTree/addTreeNode",
    method: "post",
    data: param
  });
}
function delTreeNode(param) {
  return request({
    url: "hrbp/LevelTree/deleteTreeNode",
    method: "post",
    data: param
  });
}
function editTreeNode(param) {
  return request({
    url: "hrbp/LevelTree/updateTreeNode",
    method: "post",
    data: param
  });
}
function moveTreeNode(param) {
  return request({
    url: "hrbp/LevelTree/moveTreeNode",
    method: "post",
    data: param
  });
}
function getTreeNode(relationID) {
  return request({
    url: "hrbp/LevelTree/queryAllLevel",
    method: "post",
    data: { relationID }
  });
}
function getTreeTeamNode() {
  return request({
    url: "hrbp/LevelTree/queryTeamInfo",
    method: "post",
    data: {}
  });
}
function getTreePONode() {
  return request({
    url: "hrbp/LevelTree/queryPOInfo",
    method: "post",
    data: {}
  });
}
export default {
  getTreeData,
  addTreeNode,
  delTreeNode,
  editTreeNode,
  moveTreeNode,
  getTreeNode,
  getTreeTeamNode,
  getTreePONode
};
