package com.cxyxl.service;


import com.cxyxl.bean.RoleType;
import com.cxyxl.dao.RoleTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Service
@Slf4j
public class RoleTypeService {

    @Autowired
    private RoleTypeMapper roleTypeMapper;

    public List<RoleType> getAll() {
        return roleTypeMapper.findAllType();
    }
}
