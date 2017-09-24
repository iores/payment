package com.itpay.mp.user.service;

import com.itpay.mp.user.dao.MpRoleMapper;
import com.itpay.mp.user.dao.MpRolePermissionRefMapper;
import com.itpay.mp.user.dto.MpPermission;
import com.itpay.mp.user.dto.MpRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by erika on 2017/9/24.
 * 用户角色管理
 */
@Service("mpRoleManager")
public class MpRoleManagerImpl implements  MpRoleManager {


    @Resource(name="mpRoleMapper")
    private MpRoleMapper mpRoleMapper;

    @Resource(name="mpRolePermissionRefMapper")
    private MpRolePermissionRefMapper mpRolePermissionRefMapper;


    @Override
    public int insert(MpRole record) {
        return mpRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(MpRole record) {
        return mpRoleMapper.insertSelective(record);
    }

    @Override
    public MpRole selectByPrimaryKey(String roleId) {
        return mpRoleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(MpRole record) {
        return mpRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MpRole record) {
        return mpRoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public MpRole updatePermissionByRole(MpRole record, List<MpPermission> permissions) {
        return null;
    }
}
