import request from "@/utils/request";
function getAffairsList(condition) {
  return request({
    url: "hrbp/affair/findAllAffair",
    method: "post",
    data: condition
  });
}
function getAffairsInfoById(affairID) {
  return request({
    url: "hrbp/affair/findExamAffairByAffairId",
    method: "post",
    data: { affairID }
  });
}
function delAffairsInfo(affairID) {
  return request({
    url: "hrbp/affair/deleteAffair",
    method: "post",
    data: { affairID }
  });
}
function addAffairsInfo(affairsInfo) {
  return request({
    url: "hrbp/affair/insertAffair",
    method: "post",
    data: affairsInfo
  });
}
function editAffairsInfo(affairsInfo) {
  return request({
    url: "hrbp/affair/updateAffair",
    method: "post",
    data: affairsInfo
  });
}
function addDimensionInfo(dimensionInfo) {
  return request({
    url: "hrbp/dimension/insertDimension",
    method: "post",
    data: dimensionInfo
  });
}
function getDimensionInfo(affairID) {
  return request({
    url: "hrbp/dimension/queryDimension",
    method: "post",
    data: { affairID }
  });
}
export default {
  getAffairsList,
  getAffairsInfoById,
  addAffairsInfo,
  editAffairsInfo,
  delAffairsInfo,
  addDimensionInfo,
  getDimensionInfo
};
