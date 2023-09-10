# 项目介绍
### 介绍

本项目使用spring boot，mysql ，mybaits等实现与ChatGPT官方提供的openAI接口对接，实现了与openAI的对话功能。同时，实现了与前端和微信小程序对接的接口。


全面更新，请访问网址：https://ai.jingyoushui.cn

效果图展示：

 [AI应用，源码附赠，轻松打造自己的ChatGPT应用 (qq.com)](https://mp.weixin.qq.com/s?__biz=MzIxMTgyODczNg==&mid=2247485447&idx=1&sn=a6d2b31460f8c613953229c2c6087f21&chksm=974e2168a039a87ee33ef225ff3f91eafa2f66e8aef8b4f3034e4f06de7c0a9c617b6e2cadaf&mpshare=1&srcid=0910tOKcBNPr2ctvOYpHy5GY&sharer_shareinfo=a601e39c2338b3d0201feb526dd9d1c2&sharer_shareinfo_first=a601e39c2338b3d0201feb526dd9d1c2&from=singlemessage&scene=1&subscene=10000&sessionid=1694348220&clicktime=1694352786&enterid=1694352786&ascene=1&fasttmpl_type=0&fasttmpl_fullversion=6846501-zh_CN-zip&fasttmpl_flag=0&realreporttime=1694352786751&devicetype=android-31&version=28002855&nettype=3gnet&lang=zh_CN&exportkey=n_ChQIAhIQQBFZlJh2jVb1vvV41%2BydhhLuAQIE97dBBAEAAAAAAIZ9K4BPM0EAAAAOpnltbLcz9gKNyK89dVj01fD99KRsX7Xr5%2BQfJQmUD65BOJHpu%2BP4%2BAy%2Bg%2Bcl9%2FhBc2K9CuzEm4gR%2Bch4aSDESMg7xIfj3FhVvSLmhUsSMjIa8%2BS9i8wg0MjjnDcc2kW13TcJuxukOwhyL24cK%2BRF8unvECw9QgpJC0REajtp%2F1NwO9oV4tdadVabSjKUErWA019AkEONmlYKSLSSAdQUPuM6TQYjb40HBPGe1L8ex5y9CNl84osEZVO745lUOj26wRBuh5mjdiE%2Blh1OZ4xdDqNL%2FJVEGsk%3D&pass_ticket=qLI56%2BdOGrZaOEyhI1oFW664t089gBTzlOpUIpOWJe4xGfm%2FgiQv%2BT6D5GsXOcDQ&wx_header=3) 

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
