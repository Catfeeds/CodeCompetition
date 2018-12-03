import request from "@/utils/request";
function getTrainingList(condition) {
  return request({
    url: "hrbp/trainingsetting/queryTrainingList",
    method: "post",
    data: condition
  });
}
function getTrainingById(trainingId) {
  return request({
    url: "/hrbp/trainingsetting/queryTrainingInfoByTrainingId",
    method: "post",
    data: { trainingId }
  });
}
function delTraining(trainingId) {
  return request({
    url: "hrbp/trainingsetting/deleteTrainingInfo",
    method: "post",
    data: { trainingId }
  });
}
function saveTrainingInfo(trainInfo) {
  return request({
    url: "hrbp/trainingsetting/saveTrainingInfo",
    method: "post",
    data: trainInfo
  });
}
function querySeries() {
  return request({
    url: "hrbp/trainingsetting/querySeries",
    method: "post"
  });
}
function querySystem() {
  return request({
    url: "hrbp/trainingsetting/querySort",
    method: "post"
  });
}
export default {
  getTrainingList,
  getTrainingById,
  saveTrainingInfo,
  delTraining,
  querySeries,
  querySystem
};
