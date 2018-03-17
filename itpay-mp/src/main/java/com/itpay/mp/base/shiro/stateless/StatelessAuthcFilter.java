package com.itpay.mp.base.shiro.stateless;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itpay.mp.base.shiro.exception.IncorrectCaptchaException;
import com.itpay.mp.base.util.JwtUtil;
import com.itpay.restfull.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.cache.RedisCache;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * 无状态认证
 * 根据当前请求上下文 每次请求都会调用登录
 *
 * @author lfeng1
 * @date 2018/1/26 0026
 */
public class StatelessAuthcFilter extends AccessControlFilter {


    @Resource(name="sessionRedisCache")
    private RedisCache sessionRedisCache;

    public static final String HTTP_HEADER_NAME = "x-access-token";

    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);


    /**
     * http 请求头部信息 token 存放的位置
     */
    private String httpHeaderName = HTTP_HEADER_NAME;



    public String getHttpHeaderName() {
        return httpHeaderName;
    }

    public void setHttpHeaderName(String httpHeaderName) {
        this.httpHeaderName = httpHeaderName;
    }

    /**
     * 返回true  则允许请求通过，返回fasle 则每次请求都会调用onAccessDenied
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    /**
     * Processes requests where the subject was denied access as determined by the
     * {@link #isAccessAllowed(ServletRequest, ServletResponse, Object) isAccessAllowed}
     * method.
     *
     * @param request  the incoming <code>ServletRequest</code>
     * @param response the outgoing <code>ServletResponse</code>
     * @return <code>true</code> if the request should continue to be processed; false if the subclass will
     * handle/render the response directly.
     * @throws Exception if there is an error processing the request.
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        if (!(request instanceof HttpServletRequest)) {
            log.warn("当前请求不是http 请求,请求信息:[{}]", request);
            return false;
        }
        try {
            //获取表单内容
            StatelessToken token = new StatelessToken();
            //获取请求头部的token
            String clientDigest = ((HttpServletRequest) request).getHeader(getHttpHeaderName());
            if (StringUtils.isNotBlank(clientDigest)) {
                token.setClientDigest(clientDigest);
            }
            //判断是否是登录请求
            String loginUrl = getLoginUrl();
            String url = ((HttpServletRequest) request).getRequestURI();
            //包含登录url,那么就是登录
            if(url.contains(loginUrl)){
                token = getUserLoginInfo(request);
                //检查验证码
                cheackCaptcha(token);
                token.setClientDigest(null);
            }
            token.setHost(request.getRemoteHost());

            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess((HttpServletResponse) response,subject);
        } catch (Exception e) {
            return onLoginFail(response,e);
        }

    }

    /**
     * 检查传入的验证码是否正确
     * @param token token
     */
    private void cheackCaptcha( StatelessToken token){
        if(StringUtils.isBlank(token.getClientDigest())||StringUtils.isBlank(token.getCaptcha())){
            throw new IncorrectCaptchaException("验证码传入错误");
        }
        String captcha;
        try{
            //获取redis 中保存的验证码
            captcha = sessionRedisCache.get(token.getClientDigest(),String.class);
            sessionRedisCache.evict(token.getClientDigest());
        }catch(Exception e){
            log.warn("验证码获取异常!",e);
            throw new IncorrectCaptchaException("验证码获取异常");
        }
        if(!Objects.equals(captcha,token.getCaptcha())){
            throw new IncorrectCaptchaException("验证码传入错误");
        }

    }

    /**
     * 登录成功
     * @param response
     * @return
     * @throws Exception
     */
    protected boolean onLoginSuccess(HttpServletResponse response, Subject subject ) throws Exception {
        //创建token
        String token = JwtUtil.createTokenByHMAC256((String) subject.getPrincipal());
        response.setHeader(HTTP_HEADER_NAME,token);
        return true;
    }

    /**
     * 登录失败返回
     *
     * @param response
     * @throws IOException
     */
    private boolean onLoginFail(ServletResponse response,Exception e) throws IOException {
        String expMsg;
        if(e instanceof UnknownAccountException || e instanceof IncorrectCredentialsException){
            expMsg="认证失败！";
        } else if( e instanceof LockedAccountException){
            expMsg= "用户账号已禁用！";
        } else if( e instanceof ExpiredCredentialsException){
            expMsg= "用户账号已过期！";
        } else if( e instanceof IncorrectCaptchaException){
            expMsg= "验证码错误！";
        } else{
            expMsg="系统异常";
        }
        String code = ResultCode.NO_AUTHORITY;
        ResultCode resultCode = new ResultCode(code, expMsg);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(mapper.writeValueAsString(resultCode));
        response.getWriter().flush();
        return false;
    }

    /**
     * 获取登陆请求信息
     * @param request
     * @return
     */
    private StatelessToken getUserLoginInfo(ServletRequest request){
        try{
            BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line ;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            if(StringUtils.isNotBlank(sb.toString())){
                ObjectMapper mapper = new ObjectMapper();
                return  mapper.readValue(sb.toString(),StatelessToken.class);
            }
        }catch (Exception e){
            log.info("获取登录信息失败！",e);
        }
        return new StatelessToken();

    }



}
