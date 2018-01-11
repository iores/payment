package com.itpay.mp.cache;

import com.itpay.mp.user.dto.UserDto;

/**
 * 用户缓存接口
 * @author lfeng1
 */
public interface UserCacheServer {

    /**
     * 根据用户id查询出用户信息
     * @param userId 用户id
     * @return UserDto
     */
    UserDto findById(String userId);

}
