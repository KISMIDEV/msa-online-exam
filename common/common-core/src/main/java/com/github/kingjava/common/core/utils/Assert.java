package com.github.kingjava.common.core.utils;

/**
 * @author kingjava
 * @date 2019/1/23 19:59
 */
public class Assert {

    /**
     * 非空校验
     *
     * @param object  object
     * @param message message
     * @author kingjava
     * @date 2019/01/23 20:00
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
