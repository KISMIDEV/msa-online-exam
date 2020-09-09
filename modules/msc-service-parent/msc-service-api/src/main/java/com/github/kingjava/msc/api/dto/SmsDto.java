package com.github.kingjava.msc.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kingjava
 * @date 2019/6/22 13:07
 */
@Data
public class SmsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接收人
     */
    private String receiver;

    /**
     * 发送内容
     */
    private String content;
}