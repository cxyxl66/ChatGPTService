package com.cxyxl.dao;


import com.cxyxl.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
public interface UserDao extends JpaRepository<User, Integer> {

    User getUserByUsername(String userName);

    User getUserById(int id);

}
