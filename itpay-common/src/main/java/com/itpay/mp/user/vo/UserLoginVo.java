package com.itpay.mp.user.vo;

import java.io.Serializable;

/**
 * 用户登录信息的vo
 * @author lfeng1
 * @date 2018/1/9 0009
 */
public class UserLoginVo implements Serializable {

    private static final long serialVersionUID = 5232326302711444013L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String passWord;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
