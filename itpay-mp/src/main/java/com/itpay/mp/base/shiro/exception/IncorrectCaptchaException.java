package com.itpay.mp.base.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 *
 * @author feng
 * @date 2017/5/30 0030
 * 验证码验证失败的异常
 */
public class IncorrectCaptchaException extends AuthenticationException {
    private static final long serialVersionUID = 1978967517884420100L;
    
    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }

}
