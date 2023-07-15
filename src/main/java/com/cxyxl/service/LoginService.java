package com.cxyxl.service;


import com.alibaba.fastjson.JSON;
import com.cxyxl.bean.User;
import com.cxyxl.bean.WxUser;
import com.cxyxl.dao.UserDao;
import com.cxyxl.dao.WxUserDao;
import com.cxyxl.model.Code2SessionResponse;
import com.cxyxl.util.UUID;
import com.cxyxl.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private WxUserDao wxUserDao;

    public String onLogin(String code) {

        String url = UrlUtils.appendUrl(code).toString();
        RestTemplate restTemplate = new RestTemplate();

        //用restTemplate请求url，得到openid和session_key
        String response = restTemplate.getForObject(url, String.class);
        Code2SessionResponse sessionResponse = JSON.parseObject(response, Code2SessionResponse.class);
        log.info("response:{}", sessionResponse.toString());

        String openId = sessionResponse.getOpenId();
        WxUser user = wxUserDao.getWxUserByOpenId(openId);
        if (user != null) {
            return user.getId();
        }
        WxUser wxUser = new WxUser();
        wxUser.setId(UUID.getUUID10());
        wxUser.setOpenId(openId);
        wxUser.setVip(0);
        wxUser.setIntegral(10);
        wxUserDao.save(wxUser);
        return wxUser.getId();
    }

    public User findUserByUserName(String username) {
        return userDao.getUserByUsername(username);
    }

    public User findUserById(int userId){
        return userDao.getUserById(userId);
    }
}
