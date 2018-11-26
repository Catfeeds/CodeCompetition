import request from "@/utils/request";
console.log("aaa");
export function getMenuInfo() {
  return request({ url: "/getMenuInfo", method: "get" });
}
