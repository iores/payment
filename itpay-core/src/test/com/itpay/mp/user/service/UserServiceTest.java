package com.itpay.mp.user.service;

import com.itpay.BaseTest;
import com.itpay.base.enums.ESexType;
import com.itpay.base.enums.EUserStatus;
import com.itpay.core.util.PassWordUtil;
import com.itpay.mp.cache.UserCacheServer;
import com.itpay.mp.user.dto.MpPermission;
import com.itpay.mp.user.dto.MpRole;
import com.itpay.mp.user.dto.UserDto;
import com.itpay.mp.user.dto.UserLoginDto;
import com.itpay.mp.user.vo.UserLoginVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private MpRoleManager mpRoleManager;
    @Autowired
    private UserCacheServer userCacheServer;
    
    @Test
    public void insert(){
        UserDto record=new UserDto();
        record.setId(UUID.randomUUID().toString());
        record.setCreateTime(new Date());
        record.setEmail("ccm@211113");
        record.setName("ccm321");
        record.setPhone("1318987828");
        record.setSex(ESexType.MAN);
        record.setStatus(EUserStatus.NORMAL);
        int a= userManager.insert(record);
        System.out.println(a);
    }

    @Test
    public void addlogin(){
        String password = PassWordUtil.createMd5("zlf123");

        List<UserLoginVo> loginVos =new ArrayList<>(2);
        UserLoginVo vo =  new UserLoginVo();
        vo.setLoginName("zlf");
        vo.setUserId("b7dfe0c5-5163-4292-a16d-1de3c280a455");
        vo.setPassWord(PassWordUtil.createMd5(password+vo.getLoginName()));
        loginVos.add(vo);
        userLoginManager.addLoginInfo(loginVos);
    }
    
    @Test
    public void insert2(){
        UserLoginDto u=new UserLoginDto();
        u.setId(UUID.randomUUID().toString());
        //u.setStatus("11");
        u.setCreateTime(new Date());
        u.setLoginName("zlf");
        u.setLoginPwd("zlf");
        u.setRegisterTime(new Date());
        u.setUserId("111111111");
        userLoginManager.insert(u);
    }

    @Test
    public void testInsterRole(){

        MpRole role=new MpRole();
        role.setRoleId("test111");
        List<MpPermission> permissions=new ArrayList<>();
        MpPermission mpPermission=new MpPermission();
        mpPermission.setId("1111111111111");
        permissions.add(mpPermission);

        MpPermission mpPermission1=new MpPermission();
        mpPermission1.setId("222222");
        permissions.add(mpPermission1);
        mpRoleManager.updatePermissionByRole(role,permissions);
    }


    @Test
    public void  testCache(){
        UserDto userDto=userCacheServer.findById("2672a7b2-7baf-4cfa-acac-db");
        System.out.println(userDto);

    }
    
    
}
