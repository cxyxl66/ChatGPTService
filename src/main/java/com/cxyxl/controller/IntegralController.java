package com.cxyxl.controller;

import com.cxyxl.model.IntegralReq;
import com.cxyxl.model.Response;
import com.cxyxl.service.IntegralService;
import com.cxyxl.service.WxUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@RestController
@Slf4j
@RequestMapping(value = "/integral")
public class IntegralController {

    @Autowired
    IntegralService integralService;

    @Autowired
    WxUserService wxUserService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/change")
    public Response changeIntegral(@RequestBody IntegralReq req){
        Response response = new Response();
        if (req.getIntegralId() == null || req.getUserId() == null) {
            response.setCode(400).setSuccess(false).setData(null).setMsg("userId is null");
            return response;
        }
        int res = integralService.changeIntegral(req.getIntegralId(), req.getUserId());
        if (res == -1) {
            response.setCode(401).setSuccess(false).setData(null).setMsg("兑换码已被使用");
            return response;
        }

        if (res == -2) {
            response.setCode(402).setSuccess(false).setData(null).setMsg("兑换码无效");
            return response;
        }
        response.setCode(200).setSuccess(true).setData(res).setMsg("兑换成功");
        return response;

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/create")
    public Response createIntegral(@RequestParam int integral){
        log.info("createIntegral:{}", integral);
        Response response = new Response();
        int res = integralService.createIntegral(integral);
        if (res > 0) {
            response.setCode(200).setSuccess(true).setData(res).setMsg("create integral success");
            return response;
        }
        response.setCode(500).setSuccess(false).setData(null).setMsg("create integral failed");
        return response;

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/query")
    public Response queryIntegral(@RequestParam int integral){
        log.info("queryIntegral:{}", integral);
        Response response = new Response();
        List<String> res = integralService.queryIntegral(integral);
        if (res != null) {
            response.setCode(200).setSuccess(true).setData(res).setMsg("query integral success");
            return response;
        }
        response.setCode(500).setSuccess(false).setData(null).setMsg("query integral failed");
        return response;

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/sub")
    public Response subIntegral(@RequestParam String userId, @RequestParam Integer integral) {
        if (Strings.isEmpty(userId) || integral == null) {
            return Response.builder().code(400).success(false).build();
        }
        int res = wxUserService.subIntegral(userId, integral);
        if (res >= 0) {
            return Response.builder().code(200).success(true).data(res).build();
        }
        return Response.builder().code(400).success(false).build();
    }


}
