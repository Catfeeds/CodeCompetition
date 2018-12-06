import request from "@/utils/request";

function getMainstayLevel(params) {
  return request({
    url: "hrbp/MainstayReportContraer/queryMainstayLevel",
    method: "post",
    data: params
  });
}

function getRate(param){
    return request({
        url: "hrbp/MainstayReportContraer/queryMainstayProportionChart",
        method: "post",
        data: param
    });
}

function getStatisByDepartment(param){
    return request({
        url: "hrbp/MainstayReportContraer/queryMainstayByBU",
        method: "post",
        data: param
    });
}

function getStatisByPosition(param){
    return request({
        url: "hrbp/MainstayReportContraer/queryMainstayByPost",
        method: "post",
        data: param
    });
}

function getStatisByTranining(param){
    return request({
        url: "hrbp/MainstayReportContraer/queryMainstayTraining",
        method: "post",
        data: param
    });
}

function getStatisByAge(param){
    return request({
        url: "hrbp/MainstayReportContraer/queryMainstayAge",
        method: "post",
        data: param
    });
}

function getEmployee(param){
    return request({
        url: "hrbp/MainstayReportContraer/queryMainstayTable",
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
}