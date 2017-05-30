package com.itpay.mp.user.app;

import com.itpay.mp.user.dto.UserDto;
import com.itpay.mp.user.service.UserManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by feng on 2017/5/24 0024.
 * 用户
 */
@Service("userAppService")
public class UserAppServiceImpl  implements UserAppService{
    
    
    @Resource(name="userManager")
    private UserManager userManager;
    
    
    /**
     * 根据用户id查询用户信息
     *
     * @param id 用户id
     * @return UserDto
     */
    @Override
    public UserDto selectByPrimaryKey(String id) {
        return userManager.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户
     *
     * @return list
     */
    @Override
    public List<UserDto> findAll() {
        return null;
    }

    /**
     * 增加用户
     *
     * @param userDto 用户
     */
    @Override
    public void addUser(UserDto userDto) {
        userManager.insert(userDto);
    }

    /**
     * 更新用户
     *
     * @param userDto 用户
     */
    @Override
    public void update(UserDto userDto) {
        userManager.updateByPrimaryKey(userDto);
    }


}
