import axios from "axios";
import { Message } from "element-ui";
axios.defaults.headers["Access-Control-Allow-Origin"] = "*";
axios.defaults.headers["Access-Control-Allow-Method"] = "*";
axios.defaults.headers["Accept"] = "*/*";
const service = axios.create({
  baseURL: process.env.NODE_ENV === "production" ? "" : "/api", // api 的 base_url
  timeout: 5000
});

service.interceptors.request.use(
  config => {
    // if (store.getters.token) {
    //   // 让每个请求携带token-- ['X-Token']为自定义key 请根据实际情况自行修改
    //   config.headers['X-Token'] = getToken()
    // }
    return config;
  },
  error => {
    Promise.reject(error);
  }
);

service.interceptors.response.use(
  response => response,
  error => {
    Message({
      message: error.message,
      type: "error",
      duration: 5 * 1000
    });
    return Promise.reject(error);
  }
);

export default service;
