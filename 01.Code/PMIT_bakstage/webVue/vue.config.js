

let webpack = require('webpack');
module.exports = {
  baseUrl: "",
  configureWebpack:{
    plugins:[
      new webpack.providePlugin({
          jquery: 'jquery'
      })
    ]
  },
  chainWebpack: config => {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule
      .use("svg-sprite-loader")
      .loader("svg-sprite-loader")
      .options({
        symbolId: "icon-[name]"
      });
  } //,
  }, //,
  // 这里写你调用接口的基础路径，来解决跨域，如果设置了代理，那你本地开发环境的axios的baseUrl要写为 '' ，即空字符串
  //   devServer: {
  //     proxy: {
  //       "/api": {
  //         target: "http://10.60.86.49:8080/",
  //         changeOrigin: true,
  //         pathReWrite: {
  //           "^/api": ""
  //         }
  //       }
  //     }
  //   }
    devServer: {
      proxy: {
        "/api": {
          target: "http://10.60.86.68:8080/",
          changeOrigin: true,
          pathReWrite: {
            "^/api": ""
          }
        }
      }
    }
};
