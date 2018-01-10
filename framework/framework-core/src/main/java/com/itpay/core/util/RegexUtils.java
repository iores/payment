package com.itpay.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 *
 * @author lfeng1
 * @date 2018/1/9 0009
 */
public class RegexUtils {
    /**
     * 正常字符的正则表达式
     */
    private final static String CHAR = "[a-z]*[A-Z]*\\d*-*_*\\s*";

    /**
     * 判断是否是正确的IP地址
     *
     * @param ip ip
     * @return boolean true,通过，false，没通过
     */
    public static boolean cheackIp(String ip) {
        if (StringUtils.isBlank(ip)) {
            return false;
        }
        //ipv4
        String regexIpv4 = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        //ipv6
        String regexIpv6 = "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|" +
                "(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|" +
                "(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){1,4})|" +
                "(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|" +
                "(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])" +
                "(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){5}:(\\\\d|[1-9]\\\\d|" +
                "1\\\\d{2}|2[0-4]\\\\d|25[0-5])(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3})|" +
                "(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4})?:(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])" +
                "(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4})" +
                "{0,2}:(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3})|" +
                "(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}:(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])(\\\\.(\\\\d|[1-9]\\\\d|" +
                "1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){0,4}:(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])" +
                "(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3})|(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])" +
                "(\\\\.(\\\\d|[1-9]\\\\d|1\\\\d{2}|2[0-4]\\\\d|25[0-5])){3}))$";
        return ip.matches(regexIpv4) || ip.matches(regexIpv6);
    }

    /**
     * 判断是否是正确的邮箱地址
     *
     * @param email 邮箱
     * @return boolean true,通过，false，没通过
     */
    public static boolean cheackEmail(String email) {
        return StringUtils.isNotBlank(email)
                && email.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    }


    /**
     * 判断是否正整数
     *
     * @param number 数字
     * @return boolean true,通过，false，没通过
     */
    public static boolean cheackNumber(String number) {
        return StringUtils.isNotBlank(number) && number.matches("[0-9]*");
    }

    /**
     * 判断几位小数(正数)
     *
     * @param decimal 数字
     * @param count   小数位数
     * @return boolean true,通过，false，没通过
     */
    public static boolean cheackDecimal(String decimal, int count) {
        return StringUtils.isNotBlank(decimal)
                && decimal.matches("^(-)?(([1-9]\\d*)|([0]))(\\.(\\d){" + count + "})?$");
    }

    /**
     * 判断是否是手机号码
     *
     * @param phoneNumber 手机号码
     * @return boolean true,通过，false，没通过
     */
    public static boolean cheackPhoneNumber(String phoneNumber) {
        return StringUtils.isNotBlank(phoneNumber)
                && phoneNumber.matches("^(13[0-9]|14[579]|15[0-3,5-9]|17[0135678]|18[0-9])\\\\d{8}$");
    }

    /**
     * 判断是否含有特殊字符
     *
     * @param text 内容
     * @return boolean true,通过，false，没通过
     */
    public static boolean hasSpecialChar(String text) {
        //为空则说明不含特殊字符
        return StringUtils.isBlank(text) || text.replaceAll(CHAR, "").length() == 0;
    }



    /**
     * 适应CJK（中日韩）字符集，部分中日韩的字是一样的
     */
    public static boolean cheackChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (char c : ch) {
            return isChinese(c);
        }
        return false;
    }

    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    /**
     * 判断是否为数字或字母
     *
     * @param number 数字或字母
     * @return boolean true,通过，false，没通过
     */
    public static boolean cheackNumberOrLetter(String number) {
        if (StringUtils.isBlank(number)) {
            return false;
        }
        String regex = "[a-zA-Z0-9]*";
        return number.matches(regex);
    }


}
