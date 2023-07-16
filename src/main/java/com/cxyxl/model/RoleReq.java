package com.cxyxl.model;

import lombok.Data;

@Data
public class RoleReq {

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


    private String sortNum;

    /**
     * 分类id
     */
    private String cardTypeId;

    /**
     * 图标
     */
    private String image;

    /**
     * 开场白
     */
    private String chat;

    /**
     * 使用的模型
     */
    private String model;

    /**
     * 角色提示词
     */
    private String prompt;

    private Integer maxToken;

}
