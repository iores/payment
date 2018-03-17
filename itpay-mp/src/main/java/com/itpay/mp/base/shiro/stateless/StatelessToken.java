package com.itpay.mp.base.shiro.stateless;

import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * 无状态token
 * @author lfeng1
 * @date 2018/2/7 0007
 */
public class StatelessToken implements AuthenticationToken {


    private static final long serialVersionUID = -4237352821154548427L;

    public StatelessToken() {
    }

    public StatelessToken(String userName, char[] passWord, String clientDigest,String host,String captcha) {
        this.userName = userName;
        this.passWord = passWord;
        this.clientDigest = clientDigest;
        this.host = host;
        this.captcha = captcha;
    }

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private char[] passWord;


    /**
     * 请求的消息摘要  登陆时为验证码的 id
     */
    private String clientDigest;

    /**
     * 请求地址
     */
    private String host;

    /**
     * 登陆验证码
     */
    private String captcha;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassWord() {
        return passWord;
    }

    public void setPassWord(char[] passWord) {
        this.passWord = passWord;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public Object getPrincipal() {
        return userName;
    }


    @Override
    public Object getCredentials() {
        return passWord;
    }
}
