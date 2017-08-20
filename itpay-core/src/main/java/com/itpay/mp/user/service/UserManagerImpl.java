package com.itpay.mp.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itpay.core.model.page.ListPage;
import com.itpay.mp.user.dao.UserMapper;
import com.itpay.mp.user.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by feng on 2017/5/22 0022.
 * 用户
 */
@Service("userManager")
public class UserManagerImpl implements UserManager {
    
    @Resource(name="userMapper")
    private UserMapper userMapper;
    
    @Override
    public int insert(UserDto record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(UserDto record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public UserDto selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserDto record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserDto record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param search
     * @return
     */
    @Override
    public ListPage<UserDto> listPage(ListPage<UserDto> page, UserDto search) {
        PageHelper.startPage(page.getNumber(),page.getSize());
        List<UserDto> list=userMapper.findBySearch(search);
        Page<UserDto> pages = (Page<UserDto>) list;
        page.setAllSize(pages.size());
        page.setList(list);
        return page;
    }
}
