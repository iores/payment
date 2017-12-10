package com.itpay.mp.user.service;

import com.itpay.mp.user.dto.MpPermission;
import com.itpay.mp.user.dto.MpRole;
import com.itpay.mp.user.dto.MpRolePermissionRef;

import java.util.List;

/**
 * Created by erika on 2017/9/24.
 * 管理平台用户角色
 */
public interface MpRoleManager {


    /**
     * 插入
     * @param record
     * @return
     */
    int insert(MpRole record);

    /**
     * 插入
     * @param record
     * @return
     */
    int insertSelective(MpRole record);

    /**
     * 根据roleid 查询
     * @param roleId
     * @return
     */
    MpRole selectByPrimaryKey(String roleId);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MpRole record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(MpRole record);


    /**
     * 修改角色与权限关联信息
     * @param role
     * @param permissions
     * @return
     */
    MpRole updatePermissionByRole(MpRole role,List<MpPermission> permissions);

    /**
     * 新增角色
     * @param role
     * @param permissions
     */
    void addRole(MpRole role, List<MpPermission> permissions);

    /**
     * 查询所有觉得
     * @return
     */
    List<MpRole> findAll();


    /**
     * 保存角色与权限的关联信息
     * @param roleId
     * @param permissions
     */
    void saveRolePermissionsRef(String roleId,List<MpPermission> permissions);

}
