
import request from "@/utils/request";

function getTrainingScore(param){
    return request({
        url: "hrbp/MainstayReportContraer/queryScore",
        method: "post",
        data: param
    });
}

export default {
    getTrainingScore
}