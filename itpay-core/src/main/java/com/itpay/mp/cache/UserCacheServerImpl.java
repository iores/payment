package com.itpay.mp.cache;

import com.itpay.mp.user.dto.UserDto;
import com.itpay.mp.user.service.UserManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userCacheServer")
public class UserCacheServerImpl implements UserCacheServer {

    @Resource(name="userManager")
    private UserManager userManager;

    /**
     * 根据用户id查询出用户信息
     *
     * @param userId 用户id
     * @return UserDto
     */
    @Override
    @Cacheable(value = "mypay" ,key = "'mp-[#userId]'")
    public UserDto findById(String userId) {
        return userManager.selectByPrimaryKey(userId);
    }
}
