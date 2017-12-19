package com.itpay.mp.base.shiro;

import com.itpay.mp.base.shiro.exception.IncorrectCaptchaException;
import com.itpay.mp.user.app.UserLoginAppService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by feng on 2017/5/30 0030.
 * 扩展验证码认证
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
    
    private Logger log= LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);


    private UserLoginAppService userLoginAppService;

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }


    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {

        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
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
     * 修改登陆成功后的跳转，登陆成功不跳转请求页面
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        //清除url,使其不再跳转到原始请求的url
        WebUtils.getAndClearSavedRequest(request);
        issueSuccessRedirect(request, response);
        //we handled the success redirect directly, prevent the chain from continuing:
        return false;
    }

    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }
}
