# PMIT_bakstage 

## 项目简介


## 后端技术栈
* springboot 2.0
* mybatis 1.1.1   
* mysql 5.6
* Apache shiro 1.4.0  权限管理
* druid 2.0 
* swagger2 2.0
* java 1.8
* pagehelper 5.1.2  


## 项目启动准备
* mysql新建hw_it数据库，执行sql脚本，在resource/sql。
* 修改application中，spring.profiles.active：dev  # 环境 dev:开发环境|test：测试环境|prod：线上环境
* 修改查看相关环境配置

## 项目结构
```
PMIT_bakstage 
├─common 公共模块 
│  ├─web  自定义公共组件
│  ├─exception   自定异常状态 
│  ├─constant    自定义常量 
│  └─Util  自定义工具类 
│ 
├─framework 框架配置 
│ ├─druid   数据库连接池 
│ ├─shiro   apache shiro权限管理框架
│ └─swagger API管理 
│ 
├─project 系统业务 
│  ├─rest API接口模块
│  └─ 
│ 
├─system 权限管理 
│  ├─dept    部门管理
│  ├─menu    菜单管理
│  ├─user    用户管理
│  ├─role    角色管理
│  └─dict    字典管理
│ 
├─resources 
│  ├─doc 相关文档 
│  ├─mapper mybatis的xml文件 
│  └─sql 数据库脚本   

```

## 编码约定
* 每个controller类中添加@RequestMapping("/name)注解,name为文件夹名。
* 方法注释按照JavaDoc格式。
* 需要修改Utils中的公共方法，请在修改前通知全体人员。
* 项目的业务全部放在project包下面
* 项目系统底层权限管理等在system包下
* 多数据源在serviceimpl包里面，方法名加上注解@DataSource(name="second")，调用pmo_it数据库，不加注解默认调用hw_it数据库
* controller返回状态统一使用AjaxResult中方法，如有不足请补充。


## druid 访问
* http://IP:port/druid/login.html 
* 账号密码为数据库的账号密码


## Swagger
* 访问地址：http://IP:port/swagger-ui.html#/
* 使用swagger时候需要在controller里面对接口的信息进行配置，进入到swagger页面才能显示


## 项目打包和运行
* 执行 maven insatll
* 在项目目录中找到target文件夹中的jar包
* 复制到相应文件夹，按住shit右键打开cmd
* 执行 java -jar （jar包名） 



## 测试接口

localhost:8080/system/dictData/selectDictDataByDataType

```json
{
	"item":{
	"dictType":"sys_region_list"
	},
	"pageNum":"2",
	"pageSize":"10"
	
}

```
