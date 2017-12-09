package com.itpay.mp.user.dao;


import com.itpay.mp.user.dto.MpRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mpRoleMapper")
public interface MpRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(MpRole record);

    int insertSelective(MpRole record);

    MpRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(MpRole record);

    int updateByPrimaryKey(MpRole record);

    /**
     * 查询所有角色
     * @return
     */
    List<MpRole> findAll();

}