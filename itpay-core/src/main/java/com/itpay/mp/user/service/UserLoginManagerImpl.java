package com.itpay.mp.user.service;

import com.itpay.mp.user.dao.UserLoginDtoMapper;
import com.itpay.mp.user.dto.UserLoginDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by feng on 2017/5/29 0029. 
 * 用户登陆
 */
@Service("userLoginManager")
public class UserLoginManagerImpl implements UserLoginManager {

    
    @Resource(name="userLoginDtoMapper")
    private UserLoginDtoMapper userLoginDtoMapper;
    
    
    /**
     * 插入
     *
     * @param record
     * @return
     */
    @Override
    public void insert(UserLoginDto record) {
        userLoginDtoMapper.insert(record);
    }

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    @Override
    public UserLoginDto selectByPrimaryKey(String id) {
        return userLoginDtoMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public void updateByPrimaryKeySelective(UserLoginDto record) {
        userLoginDtoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public void updateByPrimaryKey(UserLoginDto record) {
        userLoginDtoMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据用户登陆名称查询出用户信息
     *
     * @param loginName 登陆名称
     * @return UserLoginDto
     */
    @Override
    public UserLoginDto findByUserLoginName(String loginName) {
        return userLoginDtoMapper.findByUserLoginName(loginName);
    }
}
