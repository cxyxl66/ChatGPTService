package com.cxyxl.util;

/**
 * 封装微信小程序appid, secret js_code  为获得用户openId
 *
 * @author cxyxl
 * @date 2023/6/20
 */
public class UrlUtils {

    public static StringBuffer appendUrl(String code) {

        StringBuffer info = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?");

        info.append("appid=").append("你小程序员的appid").append("&");

        info.append("secret=").append("你小程序的secret").append("&");

        info.append("js_code=").append(code).append("&");

        info.append("grant_type=").append("authorization_code");

        return info;
    }
}
