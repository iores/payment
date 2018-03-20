package com.itpay.mp.user.vo;

import com.itpay.base.enums.EUserStatus;

import java.io.Serializable;

/**
 * 用户冻结解冻请求参数
 * @author lfeng1
 * @date 2018/3/20 0020
 */
public class UserFrozenVo implements Serializable {

    private static final long serialVersionUID = 1583468047073402840L;

    /**
     * 用户id
     */
    private String id;

    /**
     * 操作类型
     */
    private EUserStatus userStatus;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EUserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(EUserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
