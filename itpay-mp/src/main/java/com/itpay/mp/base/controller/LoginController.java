package com.itpay.mp.base.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
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
 * Created by feng on 2017/5/28 0028.
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
    public String  goLogin(){
        return "login";
    }


    /**
     * 进入登录界面 
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public String  doLogin(){
        return "登陆成功";
    }

    /**
     * 生成验证码
     */
    @RequestMapping("/image/captcha")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
       // String code = (String)session.getAttribute("code");

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
