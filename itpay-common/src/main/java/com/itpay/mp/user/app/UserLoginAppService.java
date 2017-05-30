package com.itpay.mp.user.app;

import com.itpay.mp.user.dto.UserLoginDto;

/**
 * Created by feng on 2017/5/29 0029.
 * 用户登陆验证
 */
public interface UserLoginAppService {


    /**
     * 根据用户登陆名称查询出用户信息
     * @param loginName 登陆名称
     * @return UserLoginDto
     */
    UserLoginDto findByUserLoginName(String loginName);
    
    
}
