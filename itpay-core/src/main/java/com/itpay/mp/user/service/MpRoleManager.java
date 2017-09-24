package com.itpay.mp.user.service;

import com.itpay.mp.user.dto.MpPermission;
import com.itpay.mp.user.dto.MpRole;

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
     * 修改角色关联的权限数据
     * @param record
     * @param permissions
     * @return
     */
    MpRole updatePermissionByRole(MpRole record, List<MpPermission> permissions);



}
