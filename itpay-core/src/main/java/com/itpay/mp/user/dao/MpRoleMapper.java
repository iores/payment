package com.itpay.mp.user.dao;


import com.itpay.mp.user.dto.MpRole;
import org.springframework.stereotype.Repository;

@Repository("mpRoleMapper")
public interface MpRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(MpRole record);

    int insertSelective(MpRole record);

    MpRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(MpRole record);

    int updateByPrimaryKey(MpRole record);


}