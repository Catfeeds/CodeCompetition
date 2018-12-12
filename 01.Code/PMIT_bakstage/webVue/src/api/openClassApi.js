import request from "@/utils/request";
function getOpenClassList(condition) {
  return request({
    url: "hrbp/class/queryAllClass",
    method: "post",
    data: condition
  });
}
function getOpenClassInfoById(openingID) {
  return request({
    url: "hrbp/class/findClassByID",
    method: "post",
    data: { openingID }
  });
}
function delOpenClassInfo(openingID) {
  return request({
    url: "hrbp/class/deleteClass",
    method: "post",
    data: { openingID }
  });
}
function addOpenClassInfo(classInfo) {
  return request({
    url: "hrbp/class/insertClass",
    method: "post",
    data: classInfo
  });
}
function editOpenClassInfo(classInfo) {
  return request({
    url: "hrbp/class/updateClass",
    method: "post",
    data: classInfo
  });
}
export default {
  getOpenClassList,
  getOpenClassInfoById,
  delOpenClassInfo,
  addOpenClassInfo,
  editOpenClassInfo
};
