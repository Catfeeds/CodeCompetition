import request from "@/utils/request";
function getProductInfo() {
  return request({
    url: "projectGroup/getProductInfo",
    method: "get"
  });
}
function getDUInfo(product) {
  return request({
    url: "projectGroup/getDUInfo",
    method: "get",
    params: { product: product }
  });
}
function getPDUInfo(product, du) {
  return request({
    url: "projectGroup/getPDUInfo",
    method: "get",
    params: { product: product, du: du }
  });
}
function getProjectGroupInfo(param) {
  return request({
    url: "projectGroup/getProjectGroupInfo",
    method: "get",
    params: param
  });
}
export default {
  getProductInfo,
  getDUInfo,
  getPDUInfo,
  getProjectGroupInfo
};
