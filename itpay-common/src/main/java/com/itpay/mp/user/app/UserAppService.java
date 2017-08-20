package com.itpay.mp.user.app;

import com.itpay.core.model.page.ListPage;
import com.itpay.mp.user.dto.UserDto;

import java.util.List;

/**
 * Created by feng on 2017/5/24 0024.
 * 用户接口
 */
public interface UserAppService {

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return UserDto
     */
    UserDto selectByPrimaryKey(String id);

    /**
     * 查询所有用户
     * @return list
     */
    List<UserDto> findAll();

    /**
     * 增加用户
     * @param userDto 用户
     */
    void addUser(UserDto userDto);

    /**
     * 更新用户
     * @param userDto 用户
     */
    void update(UserDto userDto);

    /**
     * 分页查询 
     * @param page 分页信息
     * @param search 查询条件
     * @return
     */
    ListPage<UserDto> listPage(ListPage<UserDto> page, UserDto search);
    

}
