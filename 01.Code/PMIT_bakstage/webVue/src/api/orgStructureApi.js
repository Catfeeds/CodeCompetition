import request from "@/utils/request";
function getHumanTreeData(type) {
  return request({
    url: "hrbp/LevelTree/queryAllTree",
    method: "post",
    data: { type }
  });
}
function addHumanTreeNode(param) {
  return request({
    url: "hrbp/LevelTree/addTreeNode",
    method: "post",
    data: param
  });
}
function delHumanTreeNode(param) {
  return request({
    url: "hrbp/LevelTree/deleteTreeNode",
    method: "post",
    data: param
  });
}
function editHumanTreeNode(param) {
  return request({
    url: "hrbp/LevelTree/updateTreeNode",
    method: "post",
    data: param
  });
}
function moveHumanTreeNode(param) {
  return request({
    url: "hrbp/LevelTree/moveTreeNode",
    method: "post",
    data: param
  });
}
function getHumanTreeNode(relationID) {
  return request({
    url: "hrbp/LevelTree/queryAllLevel",
    method: "post",
    data: { relationID }
  });
}
function getHumanTreeTeamNode() {
  return request({
    url: "hrbp/LevelTree/queryTeamInfo",
    method: "post",
    data: {}
  });
}
export default {
  getHumanTreeData,
  addHumanTreeNode,
  delHumanTreeNode,
  editHumanTreeNode,
  moveHumanTreeNode,
  getHumanTreeNode,
  getHumanTreeTeamNode
};
