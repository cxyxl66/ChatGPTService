package com.cxyxl.controller;

import com.cxyxl.Token.TokenService;
import com.cxyxl.bean.User;
import com.cxyxl.model.Response;
import com.cxyxl.model.RoleModel;
import com.cxyxl.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Controller
@RequestMapping()
@Slf4j
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    TokenService tokenService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/wx_login")
    public Response wxLogin(@RequestParam String code){
        log.info("code:{}", code);
        String id = loginService.onLogin(code);
        if (id != null) {
            return Response.builder().code(200).success(true).data(id).build();
        }
        return Response.builder().code(400).success(false).build();
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/login")
    public Response login(@RequestBody Map<String, String> request){
        Response response = new Response();
        if (request != null && request.get("username") != null && request.get("password") != null) {
            User user = loginService.findUserByUserName(request.get("username"));
            if (user == null) {
                response.setData(null).setCode(400).setData("the user not found").setSuccess(false);
                return response;
            }
            if (!user.getPassword().equals(request.get("password"))) {
                response.setData(null).setCode(400).setData("the password is wrong").setSuccess(false);
                return response;
            }
            String token = tokenService.getToken(user);

            RoleModel roleModel = new RoleModel();
            roleModel.setRoles(Arrays.asList(user.getRole().split(",")));
            roleModel.setToken(token);
            response.setData(roleModel).setSuccess(true).setCode(200);
        }
        return response;
    }

}
