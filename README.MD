# Slack to Easemob Customer Service

## 简介

这个程序能够帮助你在已经有了一定的环信即时通讯云和环信移动客服的基础上，并且已经对Slack有了初步了解之后，快速搭建起来一个Slack App，并通过事件订阅、Api调用的方式来对接Slack Bot

## 文件目录

* java 源代码
* resources 配置文件和静态文件
* webapp jsp页面

## 初始化

在application.properties中，有一些配置文件需要填写
```
# ======= System =======
# 如果需要指定服务的端口号，请在这里设置
#server.port=9901

# ======= Slack =======
# 这里填写在slack官网申请的app信息
slack.clientId=
slack.clientSecret=
slack.verifyToken=

# ======= Easemob =======
# 这里填写在环信即时通讯云申请的信息
easemob.clientId=
easemob.clientSecret=
easemob.org=
easemob.app=
easemob.imServiceNumber=

# ======= Web =======
spring.mvc.view.prefix=/WEB-INF/jsp
spring.mvc.view.suffix=.jsp

# ======= Log =======
#logging.level.org.springframework.web=DEBUG
#logging.level.org.apache.jasper.servlet=DEBUG
```

## 启动

```$xslt
java -jar slack2easemobkefu-0.0.1-SNAPSHOT.jar
```

## 后续

因为该服务需要公网地址才可以让Slack回调，所以我在 https://www.heroku.com 这个网站上申请了个应用，可以实时部署，并且有公网域名，可以免费用，但是不能使用redis、mysql等，所以我这个程序也没有任何持久化存储

如何使用heroku请自行百度或者官网，很简单