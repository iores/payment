package com.itpay.core.enums;

/**
 * 默认的正则类型
 * @author lfeng1
 * @date 2018/1/9 0009
 */
public enum RegexType {
    /**
     * 无
     */
    NONE,
    /**
     *特殊字符
     */
    SPECIALCHAR,
    /**
     * 中文
     */
    CHINESE,
    /**
     * 邮箱
     */
    EMAIL,
    /**
     * ip
     */
    IP,
    /**
     * 数字
     */
    NUMBER,
    /**
     * 不是特殊字符
     */
    NO_SPECIALCHAR,
    /**
     * 手机号码
     */
    PHONENUMBER,
    /**
     * 数字或字母
     */
    NUMBERORLETTER,
    ;
}
