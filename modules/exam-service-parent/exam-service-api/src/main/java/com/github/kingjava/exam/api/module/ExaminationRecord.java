package com.github.kingjava.exam.api.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 考试记录
 *
 * @author kingjava
 * @date 2018/11/8 21:05
 */
@Data
public class ExaminationRecord extends BaseEntity<ExaminationRecord> {

    /**
     * 考生ID
     */
    @NotBlank(message = "用户ID不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    /**
     * 考试ID
     */
    @NotBlank(message = "考试ID不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 成绩
     */
    private Double score;

    /**
     * 오답题目数量
     */
    private Integer inCorrectNumber;

    /**
     * 정답题目数量
     */
    private Integer correctNumber;

    /**
     * 提交状态 1-已提交 0-未提交
     */
    @NotBlank(message = "状态不能为空")
    private Integer submitStatus;
}