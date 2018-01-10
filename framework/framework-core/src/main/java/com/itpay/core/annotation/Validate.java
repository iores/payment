package com.itpay.core.annotation;

import com.itpay.core.enums.ERegexType;

import java.lang.annotation.*;

/**
 * 用于验证字段的注解
 * @author lfeng1
 * @date 2018/1/9 0009
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Inherited
@Documented
public @interface Validate {
    /**
     * 是否可以为空 ，默认为false
     * @return true 可为空  fasle 不能为空
     */
    boolean nullable() default false;

    /**
     * 最大长度
     * @return 最大长度
     */
    int maxLength() default 0;
    /**
     * 最小长度
     * @return 最小长度
     */
    int minLength() default 0;

    /**
     * 常用的正则类型
     * @return NONE
     */
    ERegexType regexType() default ERegexType.NONE;

    /**
     * 自定义正则表达式
     * @return 正则
     */
    String regexExpression() default "";

    /**
     * 参数或者字段描述
     * @return 描述
     */
    String description() default "";

}
