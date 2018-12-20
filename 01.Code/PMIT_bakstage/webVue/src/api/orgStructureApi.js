import request from "@/utils/request";
function getHumanTreeData() {
  return request({
    url: "hrbp/LevelTree/queryAllTree",
    method: "post"
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

export default {
  getHumanTreeData,
  addHumanTreeNode,
  delHumanTreeNode,
  editHumanTreeNode,
  moveHumanTreeNode,
  getHumanTreeNode
};
