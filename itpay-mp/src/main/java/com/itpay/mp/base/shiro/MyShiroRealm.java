package com.itpay.mp.base.shiro;

import com.itpay.mp.user.app.UserLoginAppService;
import com.itpay.mp.user.dto.UserLoginDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng on 2017/5/29 0029.
 * MyShiroRealm
 * @author zlf
 */
public class MyShiroRealm extends AuthorizingRealm {

    
    private UserLoginAppService userLoginAppService;

    public UserLoginAppService getUserLoginAppService() {
        return userLoginAppService;
    }

    public void setUserLoginAppService(UserLoginAppService userLoginAppService) {
        this.userLoginAppService = userLoginAppService;
    }



    /**
     * // 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> permissions=new ArrayList<>();
        permissions.add("dd");
        info.addStringPermissions(permissions);
        return info;
    }



    /**
     * // 获取认证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        if(StringUtils.isNotBlank(username)){
            UserLoginDto userLoginDto=userLoginAppService.findByUserLoginName(username);
            if(userLoginDto!=null){
                return new SimpleAuthenticationInfo(
                        userLoginDto.getLoginName(),userLoginDto.getLoginPwd(),getName() ); 
            }
        }

        return null;
    }
}
