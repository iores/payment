package com.itpay.mp.user.service;

import com.itpay.BaseTest;
import com.itpay.mp.user.dto.UserDto;
import com.itpay.mp.user.dto.UserLoginDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

/**
 * Created by feng on 2017/5/22 0022.
 * 
 */
public class UserServiceTest  extends BaseTest{
    
    @Autowired
    private UserManager userManager;

    @Autowired
    private UserLoginManager userLoginManager;
    
    @Test
    public void insert(){
        UserDto record=new UserDto();
        record.setId(UUID.randomUUID().toString());
        record.setCreateTime(new Date());
        record.setEmail("ccm@23");
        record.setName("ccm3");
        record.setPhone("1318987828");
        record.setSex("1");
        record.setStatus("2");
        int a= userManager.insert(record);
        System.out.println(a);
    }
    
    @Test
    public void insert2(){
        UserLoginDto u=new UserLoginDto();
        u.setId(UUID.randomUUID().toString());
        u.setStatus("11");
        u.setCreateTime(new Date());
        u.setLoginName("zlf");
        u.setLoginPwd("zlf");
        u.setRegisterTime(new Date());
        u.setUserId("111111111");
        userLoginManager.insert(u);
    }
    
    
    
    
}
