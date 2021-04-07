# Docker组队学习

## 简介

该目录用于存储Docker组队学习教程，该教程是与《[docker从入门到实践](https://vuepress.mirror.docker-practice.com/)》的合作项目，在经得作者同意的前提下，我们在原项目的基础上进行了整理与重构使得内容更适合与我们本期的Docker组队学习。

## 目录

0. 开篇词

1. docker简介与安装
  
   1.1. Docker简介
   
   1.2. Docker三大基本概念
   
      - Docker镜像
      - Docker容器
      - Docker Registry
   
   1.3. Docker安装
   
2. docker容器与镜像
  
   2.1. Docker镜像
   
      - 获取镜像
      - 列出镜像
      - 删除本地镜像
      - Dockerfile构建镜像
      - 跨平台构建镜像
      - 镜像存储位置
   
   2.2. Docker容器
   
      - 新建并启动容器
      - 启动已终止的容器
      - 停止容器
      - 重启容器
      - 后台运行容器
      - 进入容器
      - 暂停容器
      - 删除容器
      - 导出容器
   - 导入容器
   
3. docker数据管理

   3.1. 数据卷
      - 创建数据卷
      - 启动一个挂载数据卷的容器
      - 查看数据卷的具体信息
      - 删除数据卷
   
   3.2. 挂载主机目录
      - 挂载一个主机目录作为数据卷
      - 查看数据卷的具体信息
      - 挂载一个本地主机文件作为数据卷

4. docker网络
  
   4.1.  Docker 基础网络介绍
      - 外部访问容器
      - 容器互联
      - 配置DNS

   4.2. Docker的网络模式
      - Bridge 模式
      - Host 模式
      - None 模式
      - Container 模式

   4.3. Docker高级网络配置
      - 快速配置指南
      - 容器访问控制
      - 端口映射实现
      - 配置docker0网桥
      - 自定义网桥
      - 工具和示例
      - 编辑网络配置文件
      - 实例：创建一个点到点连接

5. docker compose
  
   5.1. 什么是docker compose
   
   5.2. 如何使用docker compose
      - web应用
   
   5.3. docker compose基本使用
      - 启动服务
      - 查看服务状态
      - 停止或删除服务
      - 进入服务
      - 查看服务输出日志
   
   5.4. Compose模板文件
      - build
      - depends_on
      - environment
      - expose
      - ports
      - secrets
      - image
      - labels
      - network_mode
      - networks
      - volumes
   
   5.5. Compose命令
      - 命令对象与格式
      - 命令选项
   
   5.6. [常见服务的docker-compose.yml集合](https://github.com/datawhalechina/team-learning-program/tree/master/Docker/Compose/%E5%B8%B8%E7%94%A8%E6%9C%8D%E5%8A%A1)、[awesome-compose](https://github.com/docker/awesome-compose)
   
6. 综合实践

   6.1. 挂载部署
   
   6.2. 构建镜像部署

## 贡献人员
感谢以下Datawhale成员对项目推进作出的贡献（排名不分先后）：

<table align="center" style="width:80%;">
  <caption><b>贡献者名单</b></caption>
<thead>
  <tr>
    <th>成员</th>
    <th>个人简介</th>
    <th>个人主页</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">苏鹏</span></td>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">东北大学硕士，Datawhale成员</td>
    <td><a href="https://github.com/SuperSupeng">Github</a></td>
  </tr>
  <tr>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">丁一超</span></td>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">Datawhale成员</td>
    <td><a href="https://github.com/Jeffding">Github</a></td>
  </tr>
  <tr>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">陈安东</span></td>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">中央民族大学，Datawhale成员</td>
    <td><a href="https://www.zhihu.com/people/wang-ya-fei-48">知乎主页</a></td>
  </tr>
  <tr>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">陈长沙</span></td>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">重庆邮电大学，Datawhale成员</td>
    <td><a href="https://blog.csdn.net/sinat_26566137?t=1">CSDN</a></td>
  </tr>
  <tr>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">刘雯静</span></td>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none"></td>
    <td><a href="https://blog.csdn.net/sinat_26566137?t=1"></a></td>
  </tr>
  <tr>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">乔石</span></td>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none"></td>
    <td><a href="https://blog.csdn.net/sinat_26566137?t=1"></a></td>
  </tr>
  <tr>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none">于鸿飞</span></td>
    <td><span style="font-weight:normal;font-style:normal;text-decoration:none"></td>
    <td><a href="https://blog.csdn.net/sinat_26566137?t=1"></a></td>
  </tr>
  </tbody>
</table> 

### 项目贡献情况

- 项目构建与整合：苏鹏
- 第一章：陈安东（校对：乔石）
- 第二章：陈长沙，乔石（校对：于鸿飞，苏鹏）
- 第三章：丁一超（校对：陈长沙）
- 第四章：刘雯静（校对：丁一超）
- 第五章：苏鹏（校对：刘雯静）
- 第六章：于鸿飞（校对：苏鹏）

## 特别鸣谢
特别鸣谢《[docker从入门到实践](https://vuepress.mirror.docker-practice.com/)》的作者[Baohua Yang](https://github.com/yeasy)对本次组队学习的支持，希望大家未来也能将自己的内容进行整理并开源出来帮助更多的人。

## 关注我们

> "Datawhale是一个专注AI领域的开源组织，以“for the learner，和学习者一起成长”为愿景，构建对学习者最有价值的开源学习社区。关注我们，一起学习成长。"

<img src="https://github.com/datawhalechina/team-learning-sql/blob/main/img/datawhale_code.jpeg" width="175" height= "200">