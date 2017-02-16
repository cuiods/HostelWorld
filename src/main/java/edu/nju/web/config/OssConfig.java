package edu.nju.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Oss configuration file
 */
@Data
@Component
@ConfigurationProperties(prefix="OSS")
public class OssConfig {
    private String host;  //OSS服务根节点
    private String accessKeyId;  //OSS服务用户名
    private String accessKeySecret;  //OSSKeySecret，类似于密码
    private String bucketName;
    private String endpoint;
}
