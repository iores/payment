package com.itpay.base.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.itpay.core.base.BaseEnum;

/**
 * @author lfeng1
 * @date 2018/1/10 0010
 */
public enum ECoreExceptionCodeType implements BaseEnum<String> {
    /**
     * 核心系统字段检查错误编码
     */
    FIELD_CHEACK_ERROR("10001","字段检查错误"),


    ;


    private String value;

    private String displayName;

    ECoreExceptionCodeType(String value, String displayName){
        this.value=value;
        this.displayName=displayName;
    }

    /**
     * 获取value
     *
     * @return value
     */
    @Override
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * 获取描述
     *
     * @return displayName
     */
    @Override
    public String getDisplayName() {
        return displayName;
    }
}
