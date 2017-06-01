<%@ page import="org.apache.shiro.authc.*" %>
<%@ page import="com.itpay.mp.base.shiro.exception.IncorrectCaptchaException" %>
<%--
  Created by IntelliJ IDEA.
  User: feng
  Date: 2017/5/31 0031
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object obj=request.getAttribute(org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
    AuthenticationException authExp = (AuthenticationException)obj;
    if( authExp != null ){
        String expMsg;

        if(authExp instanceof UnknownAccountException || authExp instanceof IncorrectCredentialsException){
            expMsg="账号或密码错误！";
        } else if( obj instanceof LockedAccountException){
            expMsg= "用户账号已禁用！";
        } else if( obj instanceof ExpiredCredentialsException){
            expMsg= "用户账号已过期！";
//        } else if( obj instanceof UserNotFoundException ){
//            expMsg= "账号或密码错误！";
        } else if( obj instanceof IncorrectCaptchaException){
            expMsg= "验证码错误！";
        } else{
            expMsg="系统异常";
        }
        out.print(expMsg);
    }
%>