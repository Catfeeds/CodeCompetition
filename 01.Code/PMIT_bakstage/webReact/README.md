# npm start | npm run start && npm run build 打包项目
#   项目配置
react+antd+webpack+redux+axios  

更改react-scripts包中的路径 / => ./
#打包文件分析

build --prod 打包      js == 637k     css == 428k
见package.json

#打包war 配置环境运行说明 

src/main/webapp/  下存放静态文件项目

WEB-INF  //文件夹为固定文件夹  不可动

build文件夹静态文件放入src/main/webapp/ 中,执行mvn package操作