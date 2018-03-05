package com.itpay.restfull;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author lfeng1
 * @date 2018/1/19 0019
 * restfluu 接口返回元数据
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultCode implements Serializable {

    private static final long serialVersionUID = -3287718901570990354L;

    public  static final String OK = "200";
    public  static final String NO_AUTHORITY = "401";
    public  static final String ERROR = "500";


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

    public ResultCode(String  code, String message) {
        this.meta = new Meta(code,message);
    }

    public ResultCode(String  code, String message,Object data) {
        this.meta = new Meta(code,message);
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
