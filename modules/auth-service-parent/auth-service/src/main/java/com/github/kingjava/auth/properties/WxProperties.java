package com.github.kingjava.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置
 *
 * @author kingjava
 * @date 2019/07/04 20:25
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

    private String appId;

    private String appSecret;

    private String grantType;
}
