package com.cxyxl.dao;

import com.cxyxl.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
public interface RoleDao extends JpaRepository<Role, Integer> {

    Role getRoleById(Integer id);

}
