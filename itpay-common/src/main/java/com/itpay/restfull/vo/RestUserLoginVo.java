package com.itpay.restfull.vo;

import java.io.Serializable;

/**
 * rest  用户登录vo
 * @author lfeng1
 * @date 2018/1/23 0023
 */
public class RestUserLoginVo implements Serializable {
    private static final long serialVersionUID = 2844242219896140180L;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String passWord;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 记住
     */
    private String rememberMe;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}
