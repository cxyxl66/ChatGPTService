package com.cxyxl.dao;


import com.cxyxl.bean.RoleType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Mapper
@Repository
public interface RoleTypeMapper {

    List<RoleType> findAllType();
}
