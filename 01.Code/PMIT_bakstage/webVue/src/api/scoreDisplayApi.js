import request from "@/utils/request";

function getScoreList(param) {
  return request({
    url: "hrbp/gradeSheet/getTotalPersonalsScore",
    method: "post",
    data: param
  });
}

export default {
  getScoreList
};
