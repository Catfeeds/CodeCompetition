import request from "@/utils/request";

export function loginByUserName(userName, password) {
  const data = { staffisstAccount: userName, password: password };
  return request({
    url: "auth/loginIn",
    method: "post",
    data: data
  });
}

export function logout() {
  return request({
    url: "/login/logout",
    method: "post"
  });
}

export function getUserInfo(token) {
  return request({
    url: "/user/info",
    method: "get",
    params: { token }
  });
}
