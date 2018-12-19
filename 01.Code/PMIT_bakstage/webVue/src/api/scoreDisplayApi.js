import request from "@/utils/request";

function getScoreList(param) {
  return request({
    url: "hrbp/gradeSheet/getTotalPersonalsScore",
    method: "post",
    data: param
  });
}
function delScoreInfo(param) {
  return request({
    url: "hrbp/gradeSheet/deletePersonalsScore",
    method: "post",
    data: param
  });
}
function editScoreInfo(param) {
  return request({
    url: "hrbp/gradeSheet/updatePersonalScore",
    method: "post",
    data: param
  });
}
export default {
  getScoreList,
  delScoreInfo,
  editScoreInfo
};
