package com.cxyxl.Token;


import com.cxyxl.bean.User;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
public interface TokenService {
    public String getToken(User user);
}
