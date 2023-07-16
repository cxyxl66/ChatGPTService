package com.cxyxl.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    private String roleMessage;
    private String model;
    private Date createTime;
    private double temperature;
    private int maxTokens;

    @Column(name = "top_p")
    private double topP;
    private double presencePenalty;
    private double frequencyPenalty;
    private int n;
}
