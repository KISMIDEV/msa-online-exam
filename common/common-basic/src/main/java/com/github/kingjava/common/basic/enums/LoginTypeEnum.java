package com.github.kingjava.common.basic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인类型
 *
 * @author kingjava
 * @date 2019/07/02 09:45
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {

    /**
     * 账号密码로그인
     */
    PWD("PWD", "账号密码로그인", "/oauth/token"),

    /**
     * 验证码로그인
     */
    SMS("SMS", "验证码로그인", "/api/v1/mobile/token"),

    /**
     * QQ로그인
     */
    QQ("QQ", "QQ로그인", "/api/v1/mobile/token"),

    /**
     * 微信로그인
     */
    WECHAT("WX", "微信로그인", "/api/v1/wx/token");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 接口uri
     */
    private String uri;
}
