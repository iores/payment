package com.itpay.core.util;

import com.itpay.core.annotation.Validate;
import com.itpay.core.enums.ERegexType;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.ValidationException;
import java.lang.reflect.Field;

/**
 * 字段验证工具类
 *
 * @author lfeng1
 * @date 2018/1/9 0009
 */
public class ValidateUtils {


    public static void valid(Object object) throws ValidationException {
        // 获取object的类型
        Class<?> clazz = object.getClass();
        // 获取该类型声明的成员
        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性
        for (Field field : fields) {
            // 对于private私有化的成员变量，通过setAccessible来修改器访问权限
            field.setAccessible(true);
            validate(field, object);
            // 重新设置会私有权限
            field.setAccessible(false);
        }
    }

    /**
     * 验证字段
     *
     * @param field  字段
     * @param object 需要验证的类
     * @throws ValidationException 验证失败
     */
    public static void validate(Field field, Object object) throws ValidationException {
        String description;
        Object value;
        // 获取对象的成员的注解信息
        Validate dv = field.getAnnotation(Validate.class);
        //没有获取到注解，直接返回
        if (dv == null) {
            return;
        }

        try {
            //获取值
            value = field.get(object);
        } catch (Exception e) {
            throw new ValidationException("解析错误");
        }

        //判断描述
        description = StringUtils.isBlank(dv.description()) ? field.getName() : dv.description();
        //是否可以为空
        if (!dv.nullable()) {
            if (value == null || StringUtils.isBlank(value.toString())) {
                throw new ValidationException(description + "不能为空");
            }
        }

        if (value == null) {
            return;
        }
        //处理基本类型
        baseTypeHandler((String) value,dv,description);


    }

    /**
     * 验证字符串
     * @param value 需要验证的值
     * @param dv 注解
     * @param description 字段描述
     */
    private static void baseTypeHandler(String value,Validate dv,String description) throws ValidationException {
        if (value.length() > dv.maxLength() && dv.maxLength() != 0) {
            throw new ValidationException(description + "长度不能超过" + dv.maxLength());
        }
        if (value.length() < dv.minLength() && dv.minLength() != 0) {
            throw new ValidationException(description + "长度不能小于" + dv.minLength());
        }
        if (dv.regexType() != ERegexType.NONE) {
            switch (dv.regexType()) {
                case NONE:
                    break;
                case NO_SPECIALCHAR:
                    if (RegexUtils.hasSpecialChar(value)) {
                        throw new ValidationException(description + "不能含有特殊字符");
                    }
                    break;
                case NO_CHINESE:
                    if (RegexUtils.cheackChinese(value)) {
                        throw new ValidationException(description + "不能含有中文字符");
                    }
                    break;
                case EMAIL:
                    if (!RegexUtils.cheackEmail(value)) {
                        throw new ValidationException(description + "地址格式不正确");
                    }
                    break;
                case IP:
                    if (!RegexUtils.cheackIp(value)) {
                        throw new ValidationException(description + "地址格式不正确");
                    }
                    break;
                case NUMBER:
                    if (!RegexUtils.cheackNumber(value)) {
                        throw new ValidationException(description + "不是数字");
                    }
                    break;
                case PHONENUMBER:
                    if (!RegexUtils.cheackPhoneNumber(value)) {
                        throw new ValidationException(description + "不是手机号");
                    }
                    break;
                case NUMBERORLETTER:
                    if (!RegexUtils.cheackNumberOrLetter(value)) {
                        throw new ValidationException(description + "不是数字或字母");
                    }
                    break;
                default:
                    break;
            }
        }

        //判断是否传入了正则表达式
        if(StringUtils.isNotBlank(dv.regexExpression())){
            //如果没有匹配指定的正则，则报错
            if (!value.matches(dv.regexExpression())) {
                throw new ValidationException(description + "格式不正确");
            }
        }
    }

}
