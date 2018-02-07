package com.itpay.mp.base.shiro.stateless;

import com.itpay.core.util.PassWordUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;


/**
 * 无状态密码验证器
 */
public class StatelessCredentialsMatcher extends SimpleCredentialsMatcher {


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
        return tokenCredentials != null && accountCredentials != null
                && PassWordUtil.bcryptCheckpw(String.valueOf((char[])tokenCredentials), accountCredentials.toString());

    }


}
