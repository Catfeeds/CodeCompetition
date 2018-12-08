import request from "@/utils/request";

function getTrainingScore(param) {
  return request({
    url: "hrbp/gradeSheet/getColumnName",
    method: "post",
    data: {},//param
  });
}

export default {
  getTrainingScore
};
