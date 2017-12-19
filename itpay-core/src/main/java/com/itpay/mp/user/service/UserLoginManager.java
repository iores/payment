package com.itpay.mp.user.service;

import com.itpay.mp.user.dto.UserLoginDto;

import java.util.List;

/**
 * Created by feng on 2017/5/29 0029.
 * 用户登录
 */
public interface UserLoginManager {

    /**
     * 插入
     * @param record
     * @return
     */
    void  insert(UserLoginDto record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    UserLoginDto selectByPrimaryKey(String id);

    /**
     * 更新
     * @param record
     * @return
     */
    void updateByPrimaryKeySelective(UserLoginDto record);

    /**
     * 更新
     * @param record
     * @return
     */
    void  updateByPrimaryKey(UserLoginDto record);


    /**
     * 根据用户登陆名称查询出用户信息
     *
     * @param loginName 登陆名称
     * @return UserLoginDto
     */
    UserLoginDto findByUserLoginName(String loginName);


    /**
     * 根据用户id查询登陆用户信息
     *
     * @param userId 用户的id
     * @return List<UserLoginDto>
     */
    List<UserLoginDto> findByUserId(String userId);


}
