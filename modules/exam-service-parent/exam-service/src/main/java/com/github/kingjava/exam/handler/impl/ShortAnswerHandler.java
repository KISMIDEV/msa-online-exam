package com.github.kingjava.exam.handler.impl;

import com.github.kingjava.exam.api.constants.AnswerConstant;
import com.github.kingjava.exam.api.dto.SubjectDto;
import com.github.kingjava.exam.enums.SubjectTypeEnum;
import com.github.kingjava.exam.handler.AbstractAnswerHandler;
import com.github.kingjava.exam.api.module.Answer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 简答题
 * @author kingjava
 * @date 2019/12/8 22:00
 */
@Slf4j
@AllArgsConstructor
@Component
public class ShortAnswerHandler extends AbstractAnswerHandler {

	@Override
	public SubjectTypeEnum getSubjectType() {
		return SubjectTypeEnum.SHORT_ANSWER;
	}

	@Override
	public boolean judgeRight(Answer answer, SubjectDto subject) {
		// TODO 暂时全匹配
		return subject.getAnswer().getAnswer().equals(answer.getAnswer());
	}

	@Override
	public void judge(Answer answer, SubjectDto subject, List<Double> rightScore) {
		if (judgeRight(answer, subject)) {
			rightScore.add(subject.getScore());
			answer.setAnswerType(AnswerConstant.RIGHT);
			answer.setScore(subject.getScore());
		} else {
			answer.setAnswerType(AnswerConstant.WRONG);
			answer.setScore(0.0);
		}
		answer.setMarkStatus(AnswerConstant.MARKED);
	}
}
