package com.github.kingjava.common.basic.model;

import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 日志
 *
 * @author kingjava
 * @date 2018/10/31 20:29
 */
@Data
public class Log extends BaseEntity<Log> {

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 日志标题
     */
    @NotBlank(message = "日志标题不能为空")
    private String title;

    /**
     * 조치用户的IP地址
     */
    private String ip;

    /**
     * 조치用户代理信息
     */
    private String userAgent;

    /**
     * 조치的URI
     */
    private String requestUri;

    /**
     * 조치的方式
     */
    private String method;

    /**
     * 조치提交的数据
     */
    private String params;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 耗时
     */
    private String time;
}
