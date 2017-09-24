package com.itpay.mp.user.dao;


import com.itpay.mp.user.dto.MpPermission;
import org.springframework.stereotype.Repository;

@Repository("mpPermissionMapper")
public interface MpPermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_permission
     *
     * @mbg.generated
     */
    int insert(MpPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_permission
     *
     * @mbg.generated
     */
    int insertSelective(MpPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_permission
     *
     * @mbg.generated
     */
    MpPermission selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MpPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MpPermission record);
}