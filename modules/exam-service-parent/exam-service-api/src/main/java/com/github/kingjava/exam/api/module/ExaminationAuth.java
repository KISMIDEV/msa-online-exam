package com.github.kingjava.exam.api.module;

import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

/**
 * 考试权限
 *
 * @author kingjava
 * @date 2019-07-16 14:01
 */
@Data
public class ExaminationAuth extends BaseEntity<ExaminationAuth> {

    /**
     * 学生ID
     */
    private String userId;

    /**
     * 考试ID
     */
    private String examinationId;
}
