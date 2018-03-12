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
 * 性别
 */
public enum ESexType  implements BaseEnum<String>{
    /**
     * 男
     */
    MAN("1","男"),
    /**
     * 女
     */
    WONMAN("2","女"),
    
    ;
    
    
    private String value;
    
    private String displayName;

    ESexType(String value,String displayName){
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

    static Map<String,ESexType> enumMap = new HashMap<>();
    static {
        for(ESexType en : ESexType.values()){
            enumMap.put(en.getValue(), en);
        }
    }
    /**
     * 反序列化时的 初始化函数，入参为 对应该枚举的 json值
     */
    @JsonCreator
    public static ESexType getEnum(String value) {
        return enumMap.get(value);
    }
    
}
