import request from "@/utils/request";

export function getArea(product) {
  return request({
    url: "relationship/queryArea",
    method: "get",
    params: { product: product }
  });
}

export function getRelationships(param) {
  return request({
    url: "relationship/getRelationships",
    method: "post",
    data: param
  });
}
