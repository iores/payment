package com.itpay.mp.user.dto;

import java.io.Serializable;
import java.util.Date;

public class UserLoginDto implements Serializable {
    
    private static final long serialVersionUID = 9125345430333640493L;
    
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.id
     *
     *  
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.login_name
     *
     *  
     */
    private String loginName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.login_pwd
     *
     *  
     */
    private String loginPwd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.pwd_error_num
     *
     *  
     */
    private int pwdErrorNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.register_time
     *
     *  
     */
    private Date registerTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.last_login_time
     *
     *  
     */
    private Date lastLoginTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.status
     *
     *  
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.last_login_ip
     *
     *  
     */
    private String lastLoginIp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.user_id
     *
     *  
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user_login.create_time
     *
     *  
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.id
     *
     * @return the value of t_user_login.id
     *
     *  
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.id
     *
     * @param id the value for t_user_login.id
     *
     *  
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.login_name
     *
     * @return the value of t_user_login.login_name
     *
     *  
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.login_name
     *
     * @param loginName the value for t_user_login.login_name
     *
     *  
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.login_pwd
     *
     * @return the value of t_user_login.login_pwd
     *
     *  
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.login_pwd
     *
     * @param loginPwd the value for t_user_login.login_pwd
     *
     *  
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.pwd_error_num
     *
     * @return the value of t_user_login.pwd_error_num
     *
     *  
     */
    public int getPwdErrorNum() {
        return pwdErrorNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.pwd_error_num
     *
     * @param pwdErrorNum the value for t_user_login.pwd_error_num
     *
     *  
     */
    public void setPwdErrorNum(int pwdErrorNum) {
        this.pwdErrorNum = pwdErrorNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.register_time
     *
     * @return the value of t_user_login.register_time
     *
     *  
     */
    public Date getRegisterTime() {
        return registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.register_time
     *
     * @param registerTime the value for t_user_login.register_time
     *
     *  
     */
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.last_login_time
     *
     * @return the value of t_user_login.last_login_time
     *
     *  
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.last_login_time
     *
     * @param lastLoginTime the value for t_user_login.last_login_time
     *
     *  
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.status
     *
     * @return the value of t_user_login.status
     *
     *  
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.status
     *
     * @param status the value for t_user_login.status
     *
     *  
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.last_login_ip
     *
     * @return the value of t_user_login.last_login_ip
     *
     *  
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.last_login_ip
     *
     * @param lastLoginIp the value for t_user_login.last_login_ip
     *
     *  
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.user_id
     *
     * @return the value of t_user_login.user_id
     *
     *  
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.user_id
     *
     * @param userId the value for t_user_login.user_id
     *
     *  
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user_login.create_time
     *
     * @return the value of t_user_login.create_time
     *
     *  
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user_login.create_time
     *
     * @param createTime the value for t_user_login.create_time
     *
     *  
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}