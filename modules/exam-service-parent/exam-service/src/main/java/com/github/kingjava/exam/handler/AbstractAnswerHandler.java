package com.github.kingjava.exam.handler;

import com.github.kingjava.common.core.utils.SpringContextHolder;
import com.github.kingjava.exam.api.dto.SubjectDto;
import com.github.kingjava.exam.api.module.Answer;
import com.github.kingjava.exam.service.SubjectService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计成绩
 * @author kingjava
 * @date 2020/1/19 10:07 上午
 */
public abstract class AbstractAnswerHandler implements IAnswerHandler {

	@Override
	public AnswerHandleResult handle(List<Answer> answers) {
		if (CollectionUtils.isNotEmpty(answers)) {
			// 저장答题정답的题目分数
			List<Double> rightScore = new ArrayList<>();
			// 获取题目信息
			List<SubjectDto> subjects = getSubjects(answers);
			answers.forEach(tempAnswer -> {
				subjects.stream()
						// 题目ID匹配
						.filter(tempSubject -> tempSubject.getId().equals(tempAnswer.getSubjectId())).findFirst()
						.ifPresent(subject -> judge(tempAnswer, subject, rightScore));
			});
			AnswerHandleResult result = new AnswerHandleResult();
			// 记录总分、정답题目数、오답题目数
			result.setScore(rightScore.stream().mapToDouble(Double::valueOf).sum());
			result.setCorrectNum(rightScore.size());
			result.setInCorrectNum(answers.size() - rightScore.size());
			return result;
		}
		return null;
	}

	@Override
	public List<SubjectDto> getSubjects(List<Answer> answers) {
		return SpringContextHolder.getApplicationContext().getBean(SubjectService.class)
				.findListById(getSubjectType().getValue(),
						answers.stream().map(Answer::getSubjectId).distinct().toArray(Long[]::new));
	}
}
