package com.cxyxl.service;


import com.cxyxl.bean.WxUser;
import com.cxyxl.dao.WxUserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Service
@Slf4j
public class WxUserService {

    @Autowired
    private WxUserDao wxUserDao;

    @Transactional
    public int subIntegral(String id, int needIntegral) {

        WxUser wxUser = wxUserDao.getWxUserById(id);
        if (wxUser == null) {
            return -1;
        }
        int integral = wxUser.getIntegral();
        if (integral >= needIntegral) {
            wxUser.setIntegral(integral - needIntegral);
            wxUserDao.save(wxUser);
            return wxUser.getIntegral();
        }
        return -1;
    }

    @Transactional
    public int addIntegral(String id, int needIntegral) {

        WxUser wxUser = wxUserDao.getWxUserById(id);
        if (wxUser == null) {
            return -1;
        }
        int integral = wxUser.getIntegral();

        wxUser.setIntegral(integral + needIntegral);
        wxUserDao.save(wxUser);
        return wxUser.getIntegral();


    }
}
