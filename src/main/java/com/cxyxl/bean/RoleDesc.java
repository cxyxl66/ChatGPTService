package com.cxyxl.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@Entity
@Builder
@Table(name = "role_desc")
public class RoleDesc implements Serializable {

    private static final long serialVersionUID = 7515115057693933142L;
    /**
     * 唯一主键
     */
    @Id
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 聊天内容
     */
    private String chat;

    /**
     * 首图
     */
    private String image;

    private int roleId;

    /**
     * 分类
     */
    private String roleTypeId;


    /**
     * 上线状态
     */
    private int postStatus;


    /**
     * 排序
     */
    private BigDecimal sortNum;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

}
