package com.itpay.restfull;

import java.io.Serializable;

/**
 * @author lfeng1
 * @date 2018/1/19 0019
 * restfluu 接口返回元数据
 */
public class ResultCode implements Serializable {

    private static final long serialVersionUID = -3287718901570990354L;

    public  static final String OK = "成功";
    public  static final String ERROR = "失败";


    /**
     * 头部信息，标识成功/失败
     */
    private Meta meta;
    /**
     * 数据
     */
    private Object data;


    public ResultCode() {
    }

    public ResultCode(Meta meta) {
        this.meta = meta;
    }

    public ResultCode(Meta meta, Object data) {
        this.meta = meta;
        this.data = data;
    }

    public ResultCode(boolean success, String message) {
        this.meta = new Meta(success,message);
    }

    public ResultCode(boolean success, String message,Object data) {
        this.meta = new Meta(success,message);
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
