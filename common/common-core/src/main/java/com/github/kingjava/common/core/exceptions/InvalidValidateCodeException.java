package com.github.kingjava.common.core.exceptions;

/**
 * 验证码오답异常
 *
 * @author kingjava
 * @date 2019/3/18 16:46
 */
public class InvalidValidateCodeException extends CommonException {

    private static final long serialVersionUID = -7285211528095468156L;

    public InvalidValidateCodeException() {
    }

    public InvalidValidateCodeException(String msg) {
        super(msg);
    }
}
