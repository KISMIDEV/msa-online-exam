package com.github.kingjava.exam.handler.impl;

import com.github.kingjava.exam.api.constants.AnswerConstant;
import com.github.kingjava.exam.enums.SubjectTypeEnum;
import com.github.kingjava.exam.api.dto.SubjectDto;
import com.github.kingjava.exam.api.module.Answer;
import com.github.kingjava.exam.handler.AbstractAnswerHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 判断题
 * @author kingjava
 * @date 2019/12/8 22:01
 */
@Slf4j
@AllArgsConstructor
@Component
public class JudgementAnswerHandler extends AbstractAnswerHandler {

	@Override
	public SubjectTypeEnum getSubjectType() {
		return SubjectTypeEnum.JUDGEMENT;
	}

	@Override
	public boolean judgeRight(Answer answer, SubjectDto subject) {
		return subject.getAnswer().getAnswer().equalsIgnoreCase(answer.getAnswer());
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
