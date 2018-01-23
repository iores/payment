package com.itpay.mp.base.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.itpay.mp.base.shiro.exception.IncorrectCaptchaException;
import com.itpay.mp.user.dto.UserLoginDto;
import com.itpay.restfull.ResultCode;
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
            //获取出当前登录用户
            UserLoginDto userLoginDto = (UserLoginDto) SecurityUtils.getSubject().getPrincipal();

            //已经登录成功
            return new ResultCode(ResultCode.OK,"已经登录",SecurityUtils.getSubject().getSession().getId());
           // return "redirect:/main.html";
        }
        String code = ResultCode.NO_AUTHORITY;
        String expMsg = "未登录";
        Object obj = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if(obj !=null){
            AuthenticationException authExp = (AuthenticationException)obj;
            if(authExp instanceof UnknownAccountException || authExp instanceof IncorrectCredentialsException){
                expMsg="账号或密码错误！";
            } else if( obj instanceof LockedAccountException){
                expMsg= "用户账号已禁用！";
            } else if( obj instanceof ExpiredCredentialsException){
                expMsg= "用户账号已过期！";
            } else if( obj instanceof IncorrectCaptchaException){
                expMsg= "验证码错误！";
            } else{
                code = ResultCode.ERROR;
                expMsg="系统异常";
            }
        }
        return new ResultCode(code,expMsg);
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
    @ResponseBody
    public ResultCode  main(){
        //登录成功
        return new ResultCode(ResultCode.OK,"已经登录",SecurityUtils.getSubject().getSession().getId());
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
