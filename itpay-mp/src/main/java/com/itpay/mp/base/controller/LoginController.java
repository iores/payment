package com.itpay.mp.base.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.itpay.mp.base.shiro.exception.IncorrectCaptchaException;
import com.itpay.mp.user.dto.UserLoginDto;
import com.itpay.restfull.ResultCode;
import com.itpay.restfull.vo.RestResultUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author feng
 * @date 2017/5/28 0028
 * 用户登陆
 */
@Controller
public class LoginController {

    @Resource(name="captchaProducer")
    private Producer captchaProducer;
    

    /**
     * 进入登录界面 
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultCode goLogin(HttpServletRequest request){
        //如果已经登陆，那么直接跳转到成功页面
        if(SecurityUtils.getSubject().isAuthenticated()){
            //登录成功
            return new ResultCode(ResultCode.OK,"登录成功",SecurityUtils.getSubject().getPrincipal());
        }
        String code = ResultCode.NO_AUTHORITY;
        String expMsg = "未登录";
        return new ResultCode(code,expMsg);
    }



    /**
     * 进入登录界面
     */
    @RequestMapping("/mplogin")
    public String  login(HttpServletRequest request){
        if(SecurityUtils.getSubject().isAuthenticated()){
             return "redirect:/main.html";
        }
       return "login";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public String goLogout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login.html";
    }

    /**
     * 登陆成功
     */
    @RequestMapping("/main")
    public String   main(){
        //登录成功
        return "base/home";
    }

    
    
    /**
     * 生成验证码
     */
    @RequestMapping("/image/captcha")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }
    
}
