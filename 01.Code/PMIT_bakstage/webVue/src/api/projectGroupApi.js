import request from "@/utils/request";
export function getProductInfo() {
  return request({
    url: "projectGroup/getProductInfo",
    method: "get"
  });
}
export function getDUInfo(product) {
  return request({
    url: "projectGroup/getDUInfo",
    method: "get",
    params: { product: product }
  });
}
export function getPDUInfo(product, du) {
  return request({
    url: "projectGroup/getPDUInfo",
    method: "get",
    params: { product: product, du: du }
  });
}
export function getProjectGroupInfo(param) {
  return request({
    url: "projectGroup/getProjectGroupInfo",
    method: "get",
    params: param
  });
}
