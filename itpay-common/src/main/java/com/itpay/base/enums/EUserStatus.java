package com.itpay.base.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.itpay.core.base.BaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author feng
 * @date 2017/8/20 0020
 * 用户状态
 */
public enum EUserStatus implements BaseEnum<String>{
    /**
     * 正常
     */
    NORMAL("01","正常"),
    /**
     * 冻结
     */
    FROZEN("02","冻结"),
    /**
     * 注销
     */
    WRITTEN_OFF("03","注销"),

    ;


    private String value;

    private String displayName;

    EUserStatus(String value, String displayName){
        this.value=value;
        this.displayName=displayName;
    }

    @Override
    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    static Map<String,EUserStatus> enumMap = new HashMap<>();
    static {
        for(EUserStatus en : EUserStatus.values()){
            enumMap.put(en.getValue(), en);
        }
    }

    /**
     * 反序列化时的 初始化函数，入参为 对应该枚举的 json值
     */
    @JsonCreator
    public static EUserStatus getEnum(String value) {
        return enumMap.get(value);
    }

}
