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

    public StatelessToken(String username, Map<String, ?> params, String clientDigest, long sequence) {
        this.username = username;
        this.params = params;
        this.clientDigest = clientDigest;
        this.sequence = sequence;
    }

    /**
     * 请求用户名
     */
    private String username;

    /**
     * 请求参数
     */
    private Map<String, ?> params;

    /**
     * 请求的消息摘要
     */
    private String clientDigest;
    /**
     * 请求时间戳
     */
    private long sequence;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, ?> getParams() {
        return params;
    }

    public void setParams(Map<String, ?> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }


    @Override
    public Object getPrincipal() {
        return username;
    }


    @Override
    public Object getCredentials() {
        return clientDigest;
    }
}
