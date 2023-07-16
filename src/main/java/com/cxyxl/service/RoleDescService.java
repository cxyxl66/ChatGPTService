package com.cxyxl.service;


import com.cxyxl.bean.Role;
import com.cxyxl.bean.RoleDesc;
import com.cxyxl.dao.RoleDao;
import com.cxyxl.dao.RoleDescDao;
import com.cxyxl.dao.RoleDescMapper;
import com.cxyxl.model.CommonReq;
import com.cxyxl.model.RoleReq;
import com.cxyxl.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
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

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleDescDao roleDescDao;

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

    public boolean updateStatus(String id, int postStatus) {
        int row = roleDescMapper.updateStatus(id, postStatus);
        return row == 1;
    }

    @Transactional
    public void saveRole(RoleReq req) {
        Role role = Role.builder()
                .roleName(req.getTitle())
                .roleMessage(req.getPrompt())
                .model(req.getModel())
                .temperature(1)
                .maxTokens(req.getMaxToken())
                .topP(1)
                .presencePenalty(0)
                .frequencyPenalty(0)
                .n(1)
                .createTime(new Date())
                .build();

        try {
            Role result = roleDao.save(role);
            this.saveRoleDesc(req, result.getId());
            log.info("save role success");
        } catch (Exception e) {
            log.error("save role failed", e);
            throw e;
        }
    }

    private void saveRoleDesc(RoleReq req, int roleId) {
        RoleDesc roleDesc = RoleDesc.builder()
                .id(UUID.getUUID10())
                .roleId(roleId)
                .title(req.getTitle())
                .description(req.getDescription())
                .chat(req.getChat())
                .image(req.getImage())
                .sortNum(new BigDecimal(req.getSortNum()))
                .postStatus(req.getPostStatus())
                .createDate(new Date())
                .roleTypeId(req.getCardTypeId())
                .build();
        if (req.getId() != null) {
            roleDesc.setId(req.getId());
        }
        try {
            roleDescDao.save(roleDesc);
            log.info("save role desc success");
        } catch (Exception e) {
            log.error("save role desc failed", e);
            throw e;
        }
    }
}
