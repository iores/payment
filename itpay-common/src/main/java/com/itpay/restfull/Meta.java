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
     * 是否请求成功
     */
    private boolean success;

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
    public Meta(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
