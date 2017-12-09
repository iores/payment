package com.itpay.mp.user.service;

import com.itpay.mp.user.dto.MpPermission;

import java.util.List;

/**
 * 管理平台权限管理
 */
public interface MpPermissionManager {
    /**
     * 插入
     * @param record
     * @return
     */
    int insert(MpPermission record);

    /**
     * 插入
     * @param record
     * @return
     */
    int insertSelective(MpPermission record);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    MpPermission selectByPrimaryKey(String id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MpPermission record);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(MpPermission record);

    /**
     * 获取所有资源
     * @return
     */
    List<MpPermission> findAll();

}
