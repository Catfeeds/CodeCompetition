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
    url: "/login/logout",
    method: "post"
  });
}

export function getUserInfo(employeeID) {
  return request({
    url: "/auth/getEmployee",
    method: "post",
    data: { employeeID }
  });
}
