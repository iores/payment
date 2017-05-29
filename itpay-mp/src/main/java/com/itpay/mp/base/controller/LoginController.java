package com.itpay.mp.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by feng on 2017/5/28 0028.
 * 用户登陆
 */
@Controller
public class LoginController {


    /**
     * 进入登录界面 
     */
    @RequestMapping("/login")
    public String  goLogin(){
        return "login";
    }


    /**
     * 进入登录界面 
     */
    @RequestMapping("/doLogin")
    public String  doLogin(){
        return "login";
    }
}
