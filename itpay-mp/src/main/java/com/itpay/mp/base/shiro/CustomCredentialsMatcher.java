package com.itpay.mp.base.shiro;

import com.itpay.core.util.PassWordUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 自定义的 密码验证器
 *
 * @author erika
 * @date 2018/1/7
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
    private static final Logger log= LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);


    /**
     * 处理密码
     * @param token 传入信息
     * @param info 保存信息
     * @return 是否验证通过
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //传入的密码
        Object tokenCredentials = getCredentials(token);
        //获取保存的密码
        Object accountCredentials = getCredentials(info);
        if(tokenCredentials!=null &&accountCredentials !=null){
            return PassWordUtil.bcryptCheckpw((String)tokenCredentials,(String)accountCredentials);
        }

        return false;
    }


}
