package com.github.kingjava.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kingjava.common.security.core.CustomUserDetailsService;
import com.github.kingjava.common.security.mobile.MobileLoginSuccessHandler;
import com.github.kingjava.common.security.mobile.MobileSecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 手机号로그인配置
 *
 * @author kingjava
 * @date 2019/6/23 15:18
 */
@Configuration
public class MobileLoginConfig {

    /**
     * 配置手机号로그인
     * 采用懒加载是因为只有认证授权服务需要手机로그인的相关配置
     *
     * @return MobileSecurityConfigurer
     */
    @Bean
    public MobileSecurityConfigurer mobileSecurityConfigurer(@Lazy PasswordEncoder encoder, @Lazy ClientDetailsService clientDetailsService,
                                                             @Lazy CustomUserDetailsService userDetailsService, @Lazy ObjectMapper objectMapper,
                                                             @Lazy AuthorizationServerTokenServices defaultAuthorizationServerTokenServices) {
        MobileSecurityConfigurer mobileSecurityConfigurer = new MobileSecurityConfigurer();
        mobileSecurityConfigurer.setMobileLoginSuccessHandler(mobileLoginSuccessHandler(encoder, clientDetailsService, objectMapper, defaultAuthorizationServerTokenServices));
        mobileSecurityConfigurer.setUserDetailsService(userDetailsService);
        return mobileSecurityConfigurer;
    }

    /**
     * 手机로그인成功后的处理
     *
     * @return AuthenticationSuccessHandler
     */
    @Bean
    public AuthenticationSuccessHandler mobileLoginSuccessHandler(PasswordEncoder encoder, ClientDetailsService clientDetailsService, ObjectMapper objectMapper,
                                                                  AuthorizationServerTokenServices defaultAuthorizationServerTokenServices) {
        return MobileLoginSuccessHandler.builder()
                .objectMapper(objectMapper)
                .clientDetailsService(clientDetailsService)
                .passwordEncoder(encoder)
                .defaultAuthorizationServerTokenServices(defaultAuthorizationServerTokenServices).build();
    }
}
