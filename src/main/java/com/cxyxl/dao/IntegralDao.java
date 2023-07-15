package com.cxyxl.dao;


import com.cxyxl.bean.IntegralCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
public interface IntegralDao extends JpaRepository<IntegralCode, String> {

    IntegralCode getIntegralCodeById(String id);

    List<IntegralCode> getIntegralCodesByIntegralAndDeleteFlag(int integral, int deleteFlag);

}
