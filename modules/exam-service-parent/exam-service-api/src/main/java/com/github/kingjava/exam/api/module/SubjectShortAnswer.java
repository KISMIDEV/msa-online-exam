package com.github.kingjava.exam.api.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.kingjava.common.core.persistence.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 简答题
 *
 * @author kingjava
 * @date 2018/11/8 20:53
 */
@Data
public class SubjectShortAnswer extends BaseEntity<SubjectShortAnswer> {

	/**
	 * 문제분류ID
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long categoryId;

	/**
	 * 문제명
	 */
	@NotBlank(message = "문제명不能为空")
	private String subjectName;

	/**
	 * 参考答案
	 */
	private String answer;

	/**
	 * 分值
	 */
	@NotBlank(message = "题目分值不能为空")
	private Double score;

	/**
	 * 解析
	 */
	private String analysis;

	/**
	 * 难度等级
	 */
	private Integer level;
}