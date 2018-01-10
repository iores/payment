package com.itpay.core.base;

/**
 *
 * @author feng
 * @date 2017/8/20 0020
 * 枚举基类
 */
public interface BaseEnum<T> {
    /**
     * 获取value
     * @return value
     */
    T getValue();

    /**
     *  获取描述
     * @return displayName
     */
    String getDisplayName();
}