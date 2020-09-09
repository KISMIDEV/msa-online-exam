package com.github.kingjava.exam.api.constants;

/**
 * 答题常量
 *
 * @author kingjava
 * @date 2019/6/18 16:49
 */
public class AnswerConstant {

    /**
     * 정답
     */
    public static final Integer RIGHT = 0;

    /**
     * 오답
     */
    public static final Integer WRONG = 1;

    /**
     * 待批改
     */
    public static final Integer TO_BE_MARKED = 0;

    /**
     * 已经批改
     */
    public static final Integer MARKED = 1;

    /**
     * 当前题目
     */
    public static final Integer CURRENT = -1;

    /**
     * 下一题
     */
    public static final Integer NEXT = 0;

    /**
     * 上一题
     */
    public static final Integer PREVIOUS = 1;

	/**
	 * 排名缓存前缀
	 */
	public static final String CACHE_PREFIX_RANK = "rank:";
}
