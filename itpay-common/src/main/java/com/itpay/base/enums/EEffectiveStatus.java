package com.itpay.base.enums;

import com.itpay.core.base.BaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 有效无效状态
 */
public enum EEffectiveStatus implements BaseEnum<EEffectiveStatus,String>{

    EFFECTIVE("01","有效"),
    INVALID("02","无效"),

    ;


    private String value;

    private String displayName;

    EEffectiveStatus(String value, String displayName){
        this.value=value;
        this.displayName=displayName;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    static Map<String,EEffectiveStatus> enumMap = new HashMap<>();
    static {
        for(EEffectiveStatus en : EEffectiveStatus.values()){
            enumMap.put(en.getValue(), en);
        }
    }

    public static EEffectiveStatus getEnum(String value) {
        return enumMap.get(value);
    }
    
}