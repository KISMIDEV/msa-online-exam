package com.github.kingjava.exam.api.module;

import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 과정
 *
 * @author kingjava
 * @date 2018/11/8 20:43
 */
@Data
public class Course extends BaseEntity<Course> {

    /**
     * 과정名称
     */
    @NotBlank(message = "과정名称不能为空")
    private String courseName;

    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 老师
     */
    private String teacher;

    /**
     * 과정描述
     */
    private String courseDescription;

    /**
     * logoId
     */
    private Long logoId;

    /**
     * logo URL
     */
    private String logoUrl;
}
