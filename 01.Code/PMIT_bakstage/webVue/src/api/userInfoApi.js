import request from "@/utils/request";

export function loginByUserName(employeeID, password) {
  return request({
    url: "auth/loginIn",
    method: "post",
    data: { employeeID, password }
  });
}

export function logout() {
  return request({
    url: "auth/logout",
    method: "post"
  });
}

export function getUserInfo(employeeID) {
  return request({
    url: "hrbp/user/getEmployee",
    method: "post",
    data: { employeeID }
  });
}
export function updatePassword(params) {
  return request({
    url: "/hrbp/user/updateUserPassword",
    method: "post",
    data: params
  });
}
