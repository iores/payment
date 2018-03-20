package com.itpay.mp.user.app;

import com.itpay.base.enums.ECoreExceptionCodeType;
import com.itpay.base.enums.EUserStatus;
import com.itpay.core.exception.CoreBusiRunTimeException;
import com.itpay.core.model.page.ListPage;
import com.itpay.mp.user.dto.UserDto;
import com.itpay.mp.user.service.UserManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author feng
 * @date 2017/5/24 0024
 * 用户
 */
@Service("userAppService")
public class UserAppServiceImpl  implements UserAppService{

    private static final Logger logger = LoggerFactory.getLogger(UserAppServiceImpl.class);
    
    
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

    /**
     * 分页查询
     *
     * @param page   分页信息
     * @param search 查询条件
     * @return
     */
    @Override
    public ListPage<UserDto> listPage(ListPage<UserDto> page, UserDto search) {
        return userManager.listPage(page,search);
    }

    /**
     * 冻结解冻用户处理
     *
     * @param userId     用户id
     * @param userStatus 处理状态
     */
    @Override
    public void frozenAndNofrozen(String userId, EUserStatus userStatus) {
        if(StringUtils.isBlank(userId) || userStatus==null){
            throw new CoreBusiRunTimeException(ECoreExceptionCodeType.FIELD_CHEACK_ERROR.getValue(),ECoreExceptionCodeType.FIELD_CHEACK_ERROR.getDisplayName());
        }
        userManager.frozenAndNofrozen(userId,userStatus);
    }


}
