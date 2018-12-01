import request from "@/utils/request";
function getTrainingList() {
  return request({
    url: "hrbp/user/findEmpInformation",
    method: "post"
  });
}
function getTrainingById(trainId) {
  return request({
    url: "hrbp/user/findEmpInformationByEmpId",
    method: "post",
    data: { trainId }
  });
}
function addTraining(trainInfo) {
  return request({
    url: "permission/addLoginUser",
    method: "post",
    data: trainInfo
  });
}
function delTraining(trainId) {
  return request({
    url: "permission/delLoginUser",
    method: "post",
    data: { trainId }
  });
}
function editTraining(trainInfo) {
  return request({
    url: "permission/editLoginUser",
    method: "post",
    data: trainInfo
  });
}
export default {
  getTrainingList,
  getTrainingById,
  addTraining,
  delTraining,
  editTraining
};
