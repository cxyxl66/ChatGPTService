package com.cxyxl.service;

import com.cxyxl.bean.Role;
import com.cxyxl.dao.RoleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Service
@Slf4j
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }
}
