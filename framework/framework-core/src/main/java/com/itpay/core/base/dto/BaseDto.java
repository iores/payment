package com.itpay.core.base.dto;

import java.io.Serializable;

/**
 * 实体类基类
 */
public abstract   class BaseDto implements Serializable {
    private static final long serialVersionUID = 7162357875256310785L;

    protected String identity;


    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * 获取主键
     * @return
     */
    public abstract String getIdentity() ;

}
