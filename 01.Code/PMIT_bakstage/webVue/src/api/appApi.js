import request from "@/utils/request";
export function getMenuInfo() {
  return request({ url: "/getMenuInfo", method: "get" });
}
export function getProductInfo() {
  return request({
    url: "projectGroup/getProductInfo",
    method: "get"
  });
}
