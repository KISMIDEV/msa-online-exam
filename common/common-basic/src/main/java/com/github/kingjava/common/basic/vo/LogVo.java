package com.github.kingjava.common.basic.vo;

import com.github.kingjava.common.basic.model.Log;
import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * logVo
 *
 * @author kingjava
 * @date 2019-01-05 17:07
 */
@Data
public class LogVo extends BaseEntity<LogVo> {

    private Log log;

    private String username;
}
