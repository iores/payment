package com.itpay.core.exception;

/**
 * 核心系统自定义异常
 *
 * @author lfeng1
 * @date 2018/1/10 0010
 */
public class CoreBusiRunTimeException extends RuntimeException {
    private static final long serialVersionUID = 7730863539054086313L;

    /**
     * 异常编码
     */
    private String code;

    public CoreBusiRunTimeException() {
        super();
    }

    public CoreBusiRunTimeException(String message) {
        super(message);
    }

    public CoreBusiRunTimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CoreBusiRunTimeException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
