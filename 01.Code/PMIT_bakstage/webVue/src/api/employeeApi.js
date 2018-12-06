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
    url: "/hrbp/baseStaff/getPersonalInfo",
    method: "post",
    data: { employeeID }
  });
}
function delEmployeeInfo(employeeID) {
  return request({
    url: "hrbp/baseStaff/deletePersonalInfo",
    method: "post",
    data: { employeeID }
  });
}
function addEmployeeInfo(employeeInfo) {
  return request({
    url: "hrbp/baseStaff/insertPersonalInfo",
    method: "post",
    data: employeeInfo
  });
}
function editEmployeeInfo(employeeInfo) {
  return request({
    url: "hrbp/baseStaff/updatePersonalInfo",
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
