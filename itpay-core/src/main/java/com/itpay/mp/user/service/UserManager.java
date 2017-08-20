package com.itpay.mp.user.service;

import com.itpay.core.model.page.ListPage;
import com.itpay.mp.user.dto.UserDto;

/**
 * Created by feng on 2017/5/22 0022.
 * 用户
 */
public interface UserManager {


    int insert(UserDto record);

    int insertSelective(UserDto record);

    
    UserDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDto record);


  
    int updateByPrimaryKey(UserDto record);

    /**
     * 分页查询
     * @param page
     * @param search
     * @return
     */
    ListPage<UserDto> listPage(ListPage<UserDto> page, UserDto search);
    
}
