import request from "@/utils/request";
/**
 * 查询待我评价列表
 * @param {*} evaluatorId 当前登录用户ID
 */
function getEvaluateList(evaluatorId) {
  return request({
    url: "hrbp/PersonalCenter/getPendingSolved",
    method: "post",
    data: { evaluatorId }
  });
}
/**
 * 查询历史记录
 * @param {*} evaluatorId 当前登录用户ID
 */
function getHistoryEvaluateList(evaluatorId) {
  return request({
    url: "hrbp/PersonalCenter/queryHistorySolved",
    method: "post",
    data: { evaluatorId }
  });
}
/**
 * 保存评价信息
 * @param {*} evaluateInfo
 */
function saveEvaluation(evaluateInfo) {
  return request({
    url: "hrbp/PersonalCenter/saveEvaluation",
    method: "post",
    data: evaluateInfo
  });
}
export default {
  getEvaluateList,
  getHistoryEvaluateList,
  saveEvaluation
};
