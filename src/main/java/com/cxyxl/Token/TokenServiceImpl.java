package com.cxyxl.Token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cxyxl.bean.User;
import org.springframework.stereotype.Service;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId()+"").withSubject(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}


