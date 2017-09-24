package com.itpay.base.enums;

import com.itpay.core.base.BaseEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 2017/8/20 0020.
 * 用户状态
 */
public enum EUserStatus implements BaseEnum<EUserStatus,String>{

    NORMAL("01","正常"),
    FROZEN("02","冻结"),
    WRITTEN_OFF("03","注销"),
    
    ;
    
    
    private String value;
    
    private String displayName;

    EUserStatus(String value, String displayName){
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

    static Map<String,EUserStatus> enumMap = new HashMap<>();
    static {
        for(EUserStatus en : EUserStatus.values()){
            enumMap.put(en.getValue(), en);
        }
    }

    public static EUserStatus getEnum(String value) {
        return enumMap.get(value);
    }
    
}