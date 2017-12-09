package com.itpay.mp.user.app;

import com.itpay.mp.user.dto.MpPermission;
import com.itpay.mp.user.dto.MpRole;

import java.util.List;

/**
 * 用户角色接口
 */
public interface MpRoleAppService {

    /**
     * 新增角色
     * @param role 角色
     * @param permissions 权限
     */
    void addRole(MpRole role, List<MpPermission> permissions);

    /**
     * 获取所有角色信息
     * @return
     */
    List<MpRole> findAll();

}
