import request from "@/utils/request";
function getProjectGroupInfo(param) {
  return request({
    url: "projectGroup/getProjectGroupInfo",
    method: "get",
    params: param
  });
}
export default {
  getProjectGroupInfo
};
