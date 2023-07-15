package com.cxyxl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class RoleModel {
    private String token;
    private List<String> roles;
}
