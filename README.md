# 项目介绍
### 介绍

本项目使用spring boot，mysql ，mybaits等实现与ChatGPT官方提供的openAI接口对接，实现了与openAI的对话功能。同时，实现了与前端和微信小程序对接的接口。

### 效果图

先来看下效果图，可以看小程序端和前端效果

#### 网页端

![微信截图_20230715183726.png](https://www.jingyoushui.cn/image/277f4405-ccff-42ad-8e8d-f67d176cafbe.png)![微信截图_20230715183754.png](https://www.jingyoushui.cn/image/6c34e34e-729a-41b8-b766-50069ff17282.png)![微信截图_20230715183909.png](https://www.jingyoushui.cn/image/d5a26a15-ab97-45cf-8271-cdd21a02339d.png)![微信截图_20230715183922.png](https://www.jingyoushui.cn/image/a84932d0-09e5-4557-af4b-929bfb56ff54.png)![微信截图_20230715183943.png](https://www.jingyoushui.cn/image/153e79a8-9e8a-401b-9032-b39556e2ca79.png)

#### 小程序端

![微信截图_20230715220647.png](https://www.jingyoushui.cn/image/043472e1-4ad9-4b7c-b8ca-e722964165a0.png)



![微信截图_20230715220231.png](https://www.jingyoushui.cn/image/267a3d1f-6372-401e-97ac-ab6ccfa2278a.png)



### 使用说明

1.clone该项目 git clone https://github.com/cxyxl66/ChatGPTService.git

2.打开项目，安装依赖，maven install

3.修改application.yaml

- port 修改为自己想使用的端口号
- spring.datasource.username，修改为自己本地或服务器的数据库用户名，password为数据库密码，url中修改数据库名称
- 修改chatgpt.apiKey为自己的apiKey，可以去chatGPT官网查看
- 修改com.cxyxl.util.UrlUtils类，将其中的appid和secret换成自己小程序的appid和secret

4.启动项目即可



### 求助交流

如果自己尝试过程中，遇到问题，可以关注我的公众号【c程序员修炼】，查看里面具体的文章介绍。或者加我的微信 cxyxl66

![程序员修炼](https://www.jingyoushui.cn/image/e6ea2efe-d0d7-487a-b7a5-c8bf0ee7d005.jpg)