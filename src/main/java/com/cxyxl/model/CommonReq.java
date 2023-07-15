package com.cxyxl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonReq {

    private String id;

    private String categoryId;

    private String postStatus;

    private String title;

    private int pageNum;

    private int pageSize;

}
