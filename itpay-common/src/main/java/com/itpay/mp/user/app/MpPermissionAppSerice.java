package com.itpay.mp.user.app;

import com.itpay.mp.user.dto.MpPermission;

import java.util.List;

/**
 *权限接口
 * @author lfeng1
 */
public interface MpPermissionAppSerice {

    /**
     * 新增权限信息
     * @param record
     */
    void addPermission(MpPermission record);

    /**
     * 查询所有权限信息
     * @return
     */
    List<MpPermission> findAll();

}
