# HisArchive
历史档案扫描业务

# 需要
1. 开发工具： idea
2. maven 3.9
3. jdk 8
4. tomcat 8.5
5. 数据库 oracle
6. 架构 layui+ajax+json+spring+springmvc+jersey+mybatis
7. github

# 模块描述

spring +springmvc +jersey +mybatis
#############################

jersey:
http://localhost:8080/history-archive/api/path/hello
#############################

springmvc
http://localhost:8080/history-archive/api/mvc/hello
#############################

http://localhost:8080/history-archive/index

http://localhost:8080/history-archive/api/mvc/getUserInfo?name=ds
http://localhost:8080/history-archive/api/mvc/deleteUserInfo?name=AAAA


#############################
config 公共的资源配置中心
包括 mybaits 数据库连接 等配置
#############################

exception  公共的异常配置中心
#############################

app 后端配置中心
api.controller 控制中心
api.model  模型  pv 视图
api.service 服务中心 impl  实现中心
dal.mapper  mybatis 配置类  dal.mapper.page 分页mybatis 配置类
resources.mapper  mybatis xml配置中心
#############################



