package com.itpay.mp.user.app;

import com.itpay.mp.user.dto.UserLoginDto;
import com.itpay.mp.user.service.UserLoginManager;
import com.itpay.mp.user.vo.UserLoginVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author feng
 * @date 2017/5/29 0029
 * 
 */
@Service("userLoginAppService")
public class UserLoginAppServiceImpl implements UserLoginAppService {
    
    
    @Resource(name="userLoginManager")
    private UserLoginManager userLoginManager;
    
    
    /**
     * 根据用户登陆名称查询出用户信息
     *
     * @param loginName 登陆名称
     * @return UserLoginDto
     */
    @Override
    public UserLoginDto findByUserLoginName(String loginName) {
        return userLoginManager.findByUserLoginName(loginName);
    }

    /**
     * 根据用户id查询登陆用户信息
     *
     * @param userId 用户的id
     * @return List<UserLoginDto>
     */
    @Override
    public List<UserLoginDto> findByUserId(String userId) {
        return userLoginManager.findByUserId(userId);
    }

    /**
     * 增加登录用户
     *
     * @param loginVos 新增登录用户请求信息
     */
    @Override
    public void addLoginInfo(List<UserLoginVo> loginVos) {
         userLoginManager.addLoginInfo(loginVos);
    }
}
