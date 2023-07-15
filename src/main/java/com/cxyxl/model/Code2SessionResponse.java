package com.cxyxl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Code2SessionResponse {

    /**
     * 会话密钥
     */
    @JsonProperty("session_key")
    private String sessionKey;

    /**
     * 错误信息
     */
    @JsonProperty("errmsg")
    private String errMsg;

    /**
     * 用户唯一标识
     */
    @JsonProperty("openid")
    private String openId;

    /**
     * 错误码
     */
    @JsonProperty("errcode")
    private int errCode;

    /**
     * 过期时间
     */
    @JsonProperty("expires_in")
    private long expiresIn;
}
