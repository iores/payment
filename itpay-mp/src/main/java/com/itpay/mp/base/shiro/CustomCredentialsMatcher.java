package com.itpay.mp.base.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 自定义的 密码验证器
 *
 * @author erika
 * @date 2018/1/7
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * 处理密码
     * @param token 传入信息
     * @param info 保存信息
     * @return 是否验证通过
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return super.doCredentialsMatch(token, info);
    }


}
