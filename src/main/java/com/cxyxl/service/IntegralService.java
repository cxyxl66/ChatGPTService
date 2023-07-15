package com.cxyxl.service;

import com.cxyxl.bean.IntegralCode;
import com.cxyxl.dao.IntegralDao;
import com.cxyxl.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Service
@Slf4j
public class IntegralService {

    @Autowired
    private IntegralDao integralDao;

    @Autowired
    private WxUserService wxUserService;

    public int createIntegral(int integral) {
        List<IntegralCode> list = new ArrayList<>();
        for (int i=0; i<5; i++) {
            IntegralCode integralCode = IntegralCode.builder()
                    .id(UUID.getUUID10())
                    .deleteFlag(0)
                    .createDate(new Date())
                    .updateDate(new Date())
                    .integral(integral)
                    .build();
            list.add(integralCode);
        }
        List<IntegralCode> rows = integralDao.saveAll(list);
        return rows.size();
    }

    public List<String> queryIntegral(int integral) {
        List<IntegralCode> list = integralDao.getIntegralCodesByIntegralAndDeleteFlag(integral, 0);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.stream().map(IntegralCode::getId).collect(Collectors.toList());
    }

    @Transactional
    public int changeIntegral(String integral, String userId) {
        IntegralCode integralCode = integralDao.getIntegralCodeById(integral);
        if (integralCode == null) {
            return -2;
        }
        if (integralCode.getDeleteFlag() == 1 || integralCode.getIntegral() <= 0) {
            return -1;
        }
        integralCode.setDeleteFlag(1);
        integralCode.setUpdateDate(new Date());
        integralDao.save(integralCode);
        return wxUserService.addIntegral(userId, integralCode.getIntegral());
    }

}
