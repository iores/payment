package com.itpay.mp.base.shiro.stateless;

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
 * 无状态Realm
 * @author lfeng1
 * @date 2018/2/7 0007
 */
public class StatelessRealm extends AuthorizingRealm {

    private UserLoginAppService userLoginAppService;


    public void setUserLoginAppService(UserLoginAppService userLoginAppService) {
        this.userLoginAppService = userLoginAppService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;
    }


    /**
     * 获取权限信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> permissions=new ArrayList<>();
        permissions.add("dd");
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 获取认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessToken token = (StatelessToken) authenticationToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        if(StringUtils.isNotBlank(username)){
            UserLoginDto userLoginDto=userLoginAppService.findByUserLoginName(username);
            if(userLoginDto!=null){
                return new SimpleAuthenticationInfo(userLoginDto.getLoginName(),userLoginDto.getLoginPwd(),getName());
            }
        }
        return null;
    }
}
