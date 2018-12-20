import request from "@/utils/request";
function getPostList(condition) {
  return request({
    url: "hrbp/postOpt/queryPostOptionInfos",
    method: "post",
    data: condition
  });
}
function getPostInfoById(employeeID) {
  return request({
    url: "hrbp/postOpt/queryPostOption",
    method: "post",
    data: { employeeID }
  });
}
function delPostInfo(postID) {
  return request({
    url: "hrbp/postOpt/deletePostOption",
    method: "post",
    data: { postID }
  });
}
function savePostInfo(postInfo) {
  return request({
    url: "hrbp/postOpt/savePostOption",
    method: "post",
    data: postInfo
  });
}
export default {
  getPostList,
  getPostInfoById,
  delPostInfo,
  savePostInfo
};
