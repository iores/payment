package com.itpay.mp.user.dao;


import com.itpay.mp.user.dto.UserLoginDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository("userLoginDtoMapper")
public interface UserLoginDtoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_login
     *
     *  
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_login
     *
     *  
     */
    int insert(UserLoginDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_login
     *
     *  
     */
    int insertSelective(UserLoginDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_login
     *
     *  
     */
    UserLoginDto selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_login
     *
     *  
     */
    int updateByPrimaryKeySelective(UserLoginDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_login
     *
     *  
     */
    int updateByPrimaryKey(UserLoginDto record);

    /**
     * 根据用户登陆名称查询出用户信息
     *
     * @param loginName 登陆名称
     * @return UserLoginDto
     */
    
    UserLoginDto findByUserLoginName(@Param("loginName") String loginName);
    
    
}