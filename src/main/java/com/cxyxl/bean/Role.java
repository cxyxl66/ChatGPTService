package com.cxyxl.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    private Integer id;
    private String roleName;
    private String roleMessage;
    private String model;
    private Date createTime;
    private double temperature;
    private int maxTokens;
    private double topP;
    private double presencePenalty;
    private double frequencyPenalty;
    private int n;
}
