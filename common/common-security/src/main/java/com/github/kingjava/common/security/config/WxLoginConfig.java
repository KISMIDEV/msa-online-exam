package com.github.kingjava.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kingjava.common.security.core.CustomUserDetailsService;
import com.github.kingjava.common.security.wx.WxLoginSuccessHandler;
import com.github.kingjava.common.security.wx.WxSecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 微信로그인相关配置
 *
 * @author kingjava
 * @date 2019/07/05 19:44
 */
@Configuration
public class WxLoginConfig {

    /**
     * 配置微信로그인
     *
     * @return WxSecurityConfigurer
     */
    @Bean
    public WxSecurityConfigurer wxSecurityConfigurer(@Lazy PasswordEncoder encoder, @Lazy ClientDetailsService clientDetailsService,
                                                     @Lazy CustomUserDetailsService userDetailsService, @Lazy ObjectMapper objectMapper,
                                                     @Lazy AuthorizationServerTokenServices defaultAuthorizationServerTokenServices) {
        WxSecurityConfigurer wxSecurityConfigurer = new WxSecurityConfigurer();
        wxSecurityConfigurer.setWxLoginSuccessHandler(wxLoginSuccessHandler(encoder, clientDetailsService, objectMapper, defaultAuthorizationServerTokenServices));
        wxSecurityConfigurer.setUserDetailsService(userDetailsService);
        return wxSecurityConfigurer;
    }

    /**
     * 微信로그인成功后的处理
     *
     * @return AuthenticationSuccessHandler
     */
    @Bean
    public AuthenticationSuccessHandler wxLoginSuccessHandler(PasswordEncoder encoder, ClientDetailsService clientDetailsService, ObjectMapper objectMapper,
                                                              AuthorizationServerTokenServices defaultAuthorizationServerTokenServices) {
        return WxLoginSuccessHandler.builder()
                .objectMapper(objectMapper)
                .clientDetailsService(clientDetailsService)
                .passwordEncoder(encoder)
                .defaultAuthorizationServerTokenServices(defaultAuthorizationServerTokenServices).build();
    }
}
