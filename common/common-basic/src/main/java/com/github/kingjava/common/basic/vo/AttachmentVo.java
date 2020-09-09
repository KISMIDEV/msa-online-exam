package com.github.kingjava.common.basic.vo;

import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 附件VO
 *
 * @author kingjava
 * @date 2019/1/1 20:40
 */
@Data
public class AttachmentVo extends BaseEntity<AttachmentVo> {

    /**
     * 附件名称
     */
    private String attachName;

    /**
     * 附件大小
     */
    private String attachSize;

    /**
     * 业务流水号
     */
    private String busiId;

    /**
     * 业务类型
     */
    private String busiType;

    /**
     * 业务模块
     */
    private String busiModule;
}
