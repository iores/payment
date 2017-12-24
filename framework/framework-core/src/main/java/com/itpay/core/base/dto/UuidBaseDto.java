package com.itpay.core.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 主键为uuid
 */
public class UuidBaseDto extends  BaseDto {


    @Override
    @JsonIgnore
    public String getIdentity() {
        if(StringUtils.isEmpty(this.identity)) {
            this.identity = UUID.randomUUID().toString();
        }
        return this.identity;
    }
}
