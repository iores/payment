package com.itpay.restfull;

import java.io.Serializable;

/**
 * @author lfeng1
 * @date 2018/1/19 0019
 * restfull 返回的头部信息 标识请求成功/失败
 */
public class Meta implements Serializable {
    private static final long serialVersionUID = 6253795632159961570L;

    /**
     * http 请求码
     */
    private String  code;

    /**
     * 描述
     */
    private String message;

    public Meta() {
    }

    /**
     * 构造方法
     * @param success 是否成功
     * @param message 描述
     */
    public Meta(String  code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
