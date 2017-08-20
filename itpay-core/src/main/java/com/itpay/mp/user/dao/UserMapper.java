package com.itpay.mp.user.dao;


import com.itpay.mp.user.dto.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userMapper")
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_USER_INFO
     *
     *  
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_USER_INFO
     *
     *  
     */
    int insert(UserDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_USER_INFO
     *
     *  
     */
    int insertSelective(UserDto record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_USER_INFO
     *
     *  
     */
    UserDto selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_USER_INFO
     *
     *  
     */
    int updateByPrimaryKeySelective(UserDto record);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_USER_INFO
     *
     *  
     */
    int updateByPrimaryKey(UserDto record);

    /**
     * 根据条件查询
     * @param search 查询条件
     * @return
     */
    List<UserDto> findBySearch(UserDto search);
    
    
}