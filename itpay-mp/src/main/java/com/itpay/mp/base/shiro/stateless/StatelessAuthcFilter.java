package com.itpay.mp.base.shiro.stateless;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itpay.restfull.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 无状态认证
 * 根据当前请求上下文 每次请求都会调用登录
 * @author lfeng1
 * @date 2018/1/26 0026
 */
public class StatelessAuthcFilter extends AccessControlFilter {


    public static final String DEFAULT_USERNAME_PARAM = "username";
    public static final String DEFAULT_CLIENTDIGEST_PARAM = "clientDigest";
    public static final String DEFAULT_SEQUENCE_PARAM = "sequence";
    public static final String DEFAULT_MAP_PARAM = "mapKye";

    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    /**
     * 用户名称
     */
    private String usernameParam = DEFAULT_USERNAME_PARAM;
    /**
     * 摘要信息
     */
    private String clientDigestParam = DEFAULT_CLIENTDIGEST_PARAM;
    /**
     * 请求时间戳
     */
    private String sequenceParam = DEFAULT_SEQUENCE_PARAM;
    /**
     * 请求参数信息
     */
    private String mapKey = DEFAULT_MAP_PARAM;


    public String getUsernameParam() {
        return usernameParam;
    }

    public void setUsernameParam(String usernameParam) {
        this.usernameParam = usernameParam;
    }

    public String getClientDigestParam() {
        return clientDigestParam;
    }

    public void setClientDigestParam(String clientDigestParam) {
        this.clientDigestParam = clientDigestParam;
    }

    public String getSequenceParam() {
        return sequenceParam;
    }

    public void setSequenceParam(String sequenceParam) {
        this.sequenceParam = sequenceParam;
    }

    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    /**
     * 返回true  则允许请求通过，返回fasle 则每次请求都会调用onAccessDenied
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
        //1、客户端生成的消息摘要
        String clientDigest = request.getParameter(getClientDigestParam());
        //2、客户端传入的用户身份
        String username = request.getParameter(getUsernameParam());
        long sequence =0L;
        try{
            sequence = Long.parseLong(request.getParameter(getSequenceParam()));
        }catch (Exception e){
            log.warn("传入的时间戳无法解析！",e);
        }
        //3、客户端请求的参数列表
        TreeMap<String, String[]> params = new TreeMap<>(request.getParameterMap());
        //4、生成无状态Token
        StatelessToken token = new StatelessToken(username, params, clientDigest,sequence);
        try{
            getSubject(request,response).login(token);
            return true;
        }catch (Exception e){
            log.warn("登录失败！",e);
            onLoginFail(response);
            return false;
        }

    }

    /**
     * 登录失败返回
     * @param response
     * @throws IOException
     */
    private void onLoginFail(ServletResponse response) throws IOException {
        String code = ResultCode.NO_AUTHORITY;
        String expMsg = "未登录";
        ResultCode resultCode = new ResultCode(code,expMsg);
        ObjectMapper mapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(resultCode));
        response.getWriter().flush();
    }
}
