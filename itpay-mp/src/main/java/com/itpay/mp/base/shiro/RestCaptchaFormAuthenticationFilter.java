package com.itpay.mp.base.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.itpay.mp.base.shiro.exception.IncorrectCaptchaException;
import com.itpay.mp.user.app.UserLoginAppService;
import com.itpay.restfull.vo.RestUserLoginVo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by feng on 2017/5/30 0030.
 * rest扩展验证码认证
 * @author zlf
 */
public class RestCaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
    
    private static final Logger log= LoggerFactory.getLogger(RestCaptchaFormAuthenticationFilter.class);


    private UserLoginAppService userLoginAppService;

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }


    protected String getCaptcha(RestUserLoginVo request) {
        return StringUtils.clean(request.getCaptcha());
    }

    @Override
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {

        RestUserLoginVo loginVo = getUserLoginInfo(request);
        String username = getUsername(loginVo);
        String password = getPassword(loginVo);
        String captcha = getCaptcha(loginVo);
        boolean rememberMe = isRememberMe(loginVo);
        String host = getHost(request);

        return new CaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha);
    }

    /**
     * 执行验证码验证
     *
     * @param request http 请求
     * @param token   token
     */
    protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
        String captcha = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
            throw new IncorrectCaptchaException("验证码错误！");
        }

    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        CaptchaUsernamePasswordToken token = createToken(request, response);

        try {

            doCaptchaValidate((HttpServletRequest) request, token);

            //执行业务校验
            
            Subject subject = getSubject(request, response);
            //shiro 执行登陆
            subject.login(token);

            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }catch (Exception e){
            return onLoginFailure(token, new AuthenticationException(e.getMessage()), request, response);
        }

    }

    /**
     * 修改登陆成功后的不做任何处理，继续请求
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }

    /**
     * 获取登录用户
     * @param restUserLoginVo http 请求
     * @return
     */
    protected String getUsername(RestUserLoginVo restUserLoginVo) {
        return StringUtils.clean(restUserLoginVo.getUserName());
    }

    /**
     * 获取登录密码
     * @param restUserLoginVo http 请求
     * @return
     */
    protected String getPassword(RestUserLoginVo restUserLoginVo) {
        return StringUtils.clean(restUserLoginVo.getPassWord());
    }

    /**
     * 是否为记住我
     * @param restUserLoginVo
     * @return
     */
    protected boolean isRememberMe(RestUserLoginVo restUserLoginVo) {
        String value = StringUtils.clean(restUserLoginVo.getRememberMe());
        return "true".equalsIgnoreCase(value) ||
                        "t".equalsIgnoreCase(value) ||
                        "1".equalsIgnoreCase(value) ||
                        "enabled".equalsIgnoreCase(value) ||
                        "y".equalsIgnoreCase(value) ||
                        "yes".equalsIgnoreCase(value) ||
                        "on".equalsIgnoreCase(value);
    }

    /**
     * 获取登陆请求信息
     * @param request
     * @return
     */
    private RestUserLoginVo getUserLoginInfo(ServletRequest request){
        try{
            BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line ;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            ObjectMapper mapper = new ObjectMapper();
            return  mapper.readValue(sb.toString(),RestUserLoginVo.class);
        }catch (Exception e){
            log.info("获取登录信息失败！",e);
        }
        return new RestUserLoginVo();
    }

}
