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

    public StatelessToken(String username, char[] password, String clientDigest,String host) {
        this.username = username;
        this.password = password;
        this.clientDigest = clientDigest;
        this.host = host;
    }

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private char[] password;


    /**
     * 请求的消息摘要
     */
    private String clientDigest;

    /**
     * 请求地址
     */
    private String host;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
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

    @Override
    public Object getPrincipal() {
        return username;
    }


    @Override
    public Object getCredentials() {
        return password;
    }
}
