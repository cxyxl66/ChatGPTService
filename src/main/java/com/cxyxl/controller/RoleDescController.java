package com.cxyxl.controller;

import com.cxyxl.bean.RoleDesc;
import com.cxyxl.bean.RoleType;
import com.cxyxl.model.*;
import com.cxyxl.service.RoleDescService;
import com.cxyxl.service.RoleTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@RestController
@Slf4j
@RequestMapping(value = "/role_desc")
public class RoleDescController {

    @Autowired
    RoleDescService roleDescService;

    @Autowired
    RoleTypeService roleTypeService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getAllType")
    public Response getAllType(){
        Response response = new Response();

        List<RoleType> resp = roleTypeService.getAll();
        if (resp == null) {
            response.setCode(500).setSuccess(false).setData(null).setMsg("select role type failed");
            return response;
        }
        response.setCode(200).setSuccess(true).setData(resp).setMsg("select role type success");
        return response;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/list")
    public Response getCardList(@RequestBody CommonReq req){
        Response response = new Response();
        List<RoleDesc> roleDescList = roleDescService.getRoleDescList(req);
        Integer count  = roleDescService.queryRoleDescCount(req);

        if (roleDescList == null) {
            response.setCode(500).setSuccess(false).setData(null).setMsg("select role desc failed");
            return response;
        }
        List<CommonResp> publishResps = new ArrayList<>();
        roleDescList.forEach(roleDesc -> {
            RoleDescResp commonResp = RoleDescResp.builder()
                    .image(roleDesc.getImage())
                    .id(roleDesc.getId())
                    .title(roleDesc.getTitle())
                    .description(roleDesc.getDescription())
                    .sortNum(roleDesc.getSortNum().toString())
                    .postStatus(roleDesc.getPostStatus())
                    .createDate(roleDesc.getCreateDate())
                    .updateDate(roleDesc.getUpdateDate())
                    .roleId(roleDesc.getRoleId())
                    .chat(roleDesc.getChat())
                    .roleTypeId(roleDesc.getRoleTypeId())
                    .build();
            publishResps.add(commonResp);

        });
        PageResp resp = new PageResp();
        resp.setCount(count);
        resp.setList(publishResps);
        response.setCode(200).setSuccess(true).setData(resp).setMsg("select role desc success");
        return response;
    }

    /**
     * 更改上线状态
     * @param req
     * @return
     */
    @PostMapping("/audit")
    public Response audit(@RequestBody Map<String, Object> req) {
        log.info(req.toString());
        if (req.get("id") != null && req.get("status") != null) {
            String id = req.get("id").toString();
            int status = Integer.parseInt(req.get("status").toString());
            if(roleDescService.updateStatus(id, status)) {
                return Response.builder().code(200).msg("audit success").data(true).build();
            }
            return Response.builder().code(500).msg("audit failed").data(false).build();
        }
        return Response.builder().code(400).msg("request is error").data(false).build();
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/add")
    public Response postCardContent(@RequestBody RoleReq req){
        Response response = new Response();
        log.info(req.toString());
        try {
            roleDescService.saveRole(req);
        } catch (Exception e) {
            response.setCode(500).setSuccess(false).setData(null).setMsg("save role failed");
        }
        response.setCode(200).setSuccess(true).setMsg("save role success");
        return response;
    }
}
