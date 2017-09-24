package com.itpay.mp.user.dao;


import com.itpay.mp.user.dto.MpRolePermissionRef;
import org.springframework.stereotype.Repository;

@Repository("mpRolePermissionRefMapper")
public interface MpRolePermissionRefMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_mp_role_permission_ref
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_mp_role_permission_ref
     *
     * @mbg.generated
     */
    int insert(MpRolePermissionRef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_mp_role_permission_ref
     *
     * @mbg.generated
     */
    int insertSelective(MpRolePermissionRef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_mp_role_permission_ref
     *
     * @mbg.generated
     */
    MpRolePermissionRef selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_mp_role_permission_ref
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MpRolePermissionRef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_mp_role_permission_ref
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MpRolePermissionRef record);
}