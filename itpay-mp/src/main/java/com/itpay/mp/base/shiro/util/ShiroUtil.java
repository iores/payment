package com.itpay.mp.base.shiro.util;

import com.itpay.mp.base.shiro.exception.IncorrectCaptchaException;
import com.itpay.mp.user.dto.UserLoginDto;
import org.apache.shiro.SecurityUtils;


/**
 * shiro  帮助类
 *
 * @auther erika
 * @date 2018/1/7
 */
public class ShiroUtil {

    //private static final String PASSWORD_

    /**
     * 获取当前登陆的用户
     *
     * @return 当前的用户
     */
    public static UserLoginDto getCurrentUser() {
        Object obj = SecurityUtils.getSubject().getPrincipal();
        if (obj != null && obj instanceof UserLoginDto) {

            return (UserLoginDto) obj;
        }
        throw new IncorrectCaptchaException("获取当前登陆用户失败!当前没有用户登陆!");
    }



}
