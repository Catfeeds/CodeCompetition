import request from "@/utils/request";
function getEmployeeList(condition) {
  return request({
    url: "hrbp/baseStaff/getAllPersonalInfo",
    method: "post",
    data: condition
  });
}
function getEmployeeInfoById(employeeID) {
  return request({
    url: "/hrbp/trainingsetting/queryTrainingInfoByTrainingId",
    method: "post",
    data: { employeeID }
  });
}
function delEmployeeInfo(employeeID) {
  return request({
    url: "hrbp/trainingsetting/deleteTrainingInfo",
    method: "post",
    data: { employeeID }
  });
}
function addEmployeeInfo(employeeInfo) {
  return request({
    url: "hrbp/trainingsetting/saveTrainingInfo",
    method: "post",
    data: employeeInfo
  });
}
function editEmployeeInfo(employeeInfo) {
  return request({
    url: "hrbp/trainingsetting/saveTrainingInfo",
    method: "post",
    data: employeeInfo
  });
}
export default {
  getEmployeeList,
  getEmployeeInfoById,
  delEmployeeInfo,
  addEmployeeInfo,
  editEmployeeInfo
};
