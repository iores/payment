package com.itpay.mp.base.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by feng on 2017/5/30 0030
 * 扩展用户验证码
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
    private static final long serialVersionUID = -8916747789555286929L;

    private String captcha;

    public CaptchaUsernamePasswordToken(String username, String  password,
                                        boolean rememberMe, String host,String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }


    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
