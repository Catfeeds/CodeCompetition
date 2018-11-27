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
export function getPDUctInfo(product, du) {
  return request({
    url: "projectGroup/getPDUctInfo",
    method: "get",
    params: { product: product, du: du }
  });
}
