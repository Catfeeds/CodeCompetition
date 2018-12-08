import request from "@/utils/request";

function getMainstayLevel(params) {
  return request({
    url: "hrbp/MainstayReport/queryMainstayLevel",
    method: "post",
    data: params||{}
  });
}

function getRate(param) {
  return request({
    url: "hrbp/MainstayReport/queryMainstayProportionChart",
    method: "post",
    data: param
  });
}

function getStatisByDepartment(param) {
  return request({
    url: "hrbp/MainstayReport/queryMainstayByBU",
    method: "post",
    data: param
  });
}

function getStatisByPosition(param) {
  return request({
    url: "hrbp/MainstayReport/queryMainstayByPost",
    method: "post",
    data: param
  });
}

function getStatisByTranining(param) {
  return request({
    url: "hrbp/MainstayReport/queryMainstayTraining",
    method: "post",
    data: param
  });
}

function getStatisByAge(param) {
  return request({
    url: "hrbp/MainstayReport/queryMainstayAge",
    method: "post",
    data: param
  });
}

function getEmployee(param) {
  return request({
    url: "hrbp/MainstayReport/queryMainstayTable",
    method: "post",
    data: param
  });
}

export default {
  getMainstayLevel,
  getRate,
  getStatisByDepartment,
  getStatisByPosition,
  getStatisByTranining,
  getStatisByAge,
  getEmployee
};
