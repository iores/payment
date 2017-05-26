package com.itpay.mp.user.service;

import com.itpay.BaseTest;
import com.itpay.mp.user.dto.UserDto;
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
    private UserService userService;
    
    @Test
    public void insert(){
        UserDto record=new UserDto();
        record.setId(UUID.randomUUID().toString());
        record.setCreateTime(new Date());
        record.setEmail("ccm@11");
        record.setName("ccm1");
        record.setPhone("1318987828");
        record.setSex("1");
        record.setStatus("2");
        int a= userService.insert(record);
        System.out.println(a);
    }
}
