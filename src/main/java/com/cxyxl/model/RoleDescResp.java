package com.cxyxl.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
public class RoleDescResp extends CommonResp {

    private String image;

    private Integer roleId;

    private String chat;

    private String roleTypeId;

}
