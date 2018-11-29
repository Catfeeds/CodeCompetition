import request from "@/utils/request";

export function getArea(product) {
    return request({
      url: "relationShip/queryArea",
      method: "get",
      params: { product: product }
    });
  }

  export function getRelationShips(param) {
    return request({
      url: "relationShip/getRelationShips",
      method: "post",
      data:param
    });
  }