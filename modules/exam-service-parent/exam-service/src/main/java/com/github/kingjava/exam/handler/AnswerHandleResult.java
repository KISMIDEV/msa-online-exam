package com.github.kingjava.exam.handler;

import lombok.Data;

/**
 * 计算结果
 * @author kingjava
 * @date 2019/12/8 9:56 下午
 */
@Data
public class AnswerHandleResult {

	/**
	 * 总分
	 */
	private double score;

	/**
	 * 정답题目数
	 */
	private int correctNum;

	/**
	 * 오답题目数
	 */
	private int inCorrectNum;
}
