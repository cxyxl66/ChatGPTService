package com.cxyxl.dao;


import com.cxyxl.bean.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
public interface WxUserDao extends JpaRepository<WxUser, String> {

    WxUser getWxUserByOpenId(String openId);

    WxUser getWxUserById(String id);

}
