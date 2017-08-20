package com.itpay.core.base;

/**
 * Created by feng on 2017/8/20 0020.
 * 枚举基类
 */
public interface BaseEnum<E extends Enum<?>, T> {
    T getValue();
    String getDisplayName();
}