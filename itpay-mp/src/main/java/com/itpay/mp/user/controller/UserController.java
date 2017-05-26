package com.itpay.mp.user.controller;

import com.itpay.mp.user.app.UserAppService;
import com.itpay.mp.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 2017/5/25 0025.
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {

    
    @Resource(name="userAppService")
    private UserAppService userAppService; 
    

    @RequestMapping("/goDetail")
    public ModelAndView goDetail(){

        UserDto userDto=userAppService.selectByPrimaryKey("2672a7b2-7baf-4cfa-acac-dbb467e11e97");
        Map<String,Object> userDtoMap=new HashMap<>();
        userDtoMap.put("user",userDto);
        
        return new ModelAndView("detail",userDtoMap);
    }
    
}
