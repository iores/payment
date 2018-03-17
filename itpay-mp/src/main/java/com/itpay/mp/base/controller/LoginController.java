package com.itpay.mp.base.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.itpay.mp.base.shiro.exception.IncorrectCaptchaException;
import com.itpay.mp.user.dto.UserLoginDto;
import com.itpay.redis.RedisCacheUtils;
import com.itpay.restfull.ResultCode;
import com.itpay.restfull.vo.RestResultUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @Resource(name="sessionRedisCache")
    private RedisCache  sessionRedisCache;

    private static final  String CAPTCHA_KEY = "captchaKey";
    

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


    /**
     * 生成验证码
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ResultCode restKaptchaImage(@RequestBody Map<String ,String > captcha) throws Exception {
        String captchaKey = captcha.get(CAPTCHA_KEY);

        String capText = captchaProducer.createText();
        BufferedImage bi = captchaProducer.createImage(capText);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", out);
        Map<String,Object> reslut = new HashMap<>(2);
        try {
            if(StringUtils.isBlank(captchaKey)){
                //生成与图片关联的uuid
                captchaKey =  UUID.randomUUID().toString();
            }
            byte[] base64B = Base64Utils.encode(out.toByteArray());
            reslut.put("key",captchaKey);
            reslut.put("value","data:image/jpeg;base64,"+ new String(base64B));
            //将生成的验证码存入redis 中
            sessionRedisCache.put(captchaKey,capText);
            return new ResultCode(ResultCode.OK,"获取成功",reslut);
        } finally {
            out.close();
        }
    }
}
