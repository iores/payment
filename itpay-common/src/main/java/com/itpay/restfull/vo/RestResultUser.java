package com.itpay.restfull.vo;

import java.io.Serializable;

/**
 * 登陆之后的返回对象
 * @author feng
 */
public class RestResultUser implements Serializable {
    private static final long serialVersionUID = -2013644079028872121L;

    /**
     * sessionId
     */
    private Serializable sessionId;

    /**
     * 登陆用户名
     */
    private String userName;

    public Serializable getSessionId() {
        return sessionId;
    }

    public void setSessionId(Serializable sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
