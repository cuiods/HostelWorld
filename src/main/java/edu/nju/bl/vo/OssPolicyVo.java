package edu.nju.bl.vo;

import lombok.Data;

/**
 * Data used for oss auth
 */
@Data
public class OssPolicyVo {
    private String accessId;
    private String host;
    private String policy;
    private String signature;
    private String expire;
    private String dir;
}
