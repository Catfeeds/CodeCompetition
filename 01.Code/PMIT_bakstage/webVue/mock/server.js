const jsonServer = require("json-server");
const server = jsonServer.create();
const routes = require("./routes.js");

// Support middleware
const middleware = jsonServer.defaults();
server.use(middleware);
const rewriter = jsonServer.rewriter(routes);
// Add this before server.use(router)
server.use(rewriter);

// 支持加载多个db文件
const _ = require("underscore");
const path = require("path");
const fs = require("fs");
const mockDir = path.join(__dirname, "data");
const base = {};
const files = fs.readdirSync(mockDir);
files.forEach(file => {
  _.extend(base, require(path.resolve(mockDir, file)));
});
const router = jsonServer.router(base);
// 处理登录逻辑
server.post("/auth/loginIn", (req, res) => {
  let db = router.db; // lowdb instance
  let data = db.get("login").value();
  res.jsonp({
    data: data,
    status: 0,
    message: ""
  });
});
// 处理登出逻辑
server.post("/auth/logout", (req, res) => {
  let db = router.db; // lowdb instance
  let data = db.get("logout").value();
  res.jsonp({
    data: data,
    status: 0,
    message: ""
  });
});
// 将 POST 请求转为 GET
server.use((request, res, next) => {
  request.method = "GET";
  next();
});
server.use(router);

// 返回自定义格式数据
// router.render = (req, res) => {
//   res.jsonp({
//     data: res.locals.data,
//     status: 0,
//     message: ""
//   });
// };

server.listen(8081, () => {
  console.log("JSON Server is running");
});
