package com.cxyxl.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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
@Table(name = "wx_user")
public class WxUser implements Serializable {

    private static final long serialVersionUID = 7696745342964595548L;
    @Id
    private String id;
    private String openId;
    private int vip;
    private Date vipStartDate;

    private Date vipEndDate;

    private int Integral;

}
