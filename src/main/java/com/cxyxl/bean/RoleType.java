package com.cxyxl.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Table(name = "role_type")
public class RoleType implements Serializable {

    private static final long serialVersionUID = -2147217474405654201L;
    /**
     * 唯一主键
     */
    @Id
    private String id;

    /**
     * 问题类别名称
     */
    private String roleTypeName;

    /**
     * 排序
     */
    private BigDecimal sortNum;

}
