package com.cxyxl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Data
@SuperBuilder(toBuilder = true)
public class CommonResp {

    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String description;

    /**
     * 上线状态，0上线，１下线
     */
    private int postStatus;

    /**
     * 是否原创，0原创，１转载
     */
    private int prop;

    /**
     * 用于排序
     */
    private String sortNum;

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
