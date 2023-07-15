package com.cxyxl.service;


import com.cxyxl.bean.RoleDesc;
import com.cxyxl.dao.RoleDescMapper;
import com.cxyxl.model.CommonReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Service
@Slf4j
public class RoleDescService {

    @Autowired
    private RoleDescMapper roleDescMapper;

    public List<RoleDesc> getRoleDescList(CommonReq req) {
        log.info("get all role desc by categoryId :{}, id:{}, title:{} pageNum:{}, pageSize:{}",
                req.getCategoryId(), req.getId(), req.getTitle(), req.getPageNum(), req.getPageSize());
        int offSet = (req.getPageNum() -1) * req.getPageSize();
        if (Strings.isNotEmpty(req.getTitle())) {
            req.setTitle("%" + req.getTitle() + "%");
        }
        return roleDescMapper.queryRoleDesc(offSet, req.getPageSize(), req);
    }

    public Integer queryRoleDescCount(CommonReq req) {
        if (Strings.isNotEmpty(req.getTitle())) {
            req.setTitle("%" + req.getTitle() + "%");
        }
        return roleDescMapper.queryRoleDescCount(req);
    }
}
