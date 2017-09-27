package com.itpay.mp.user.dto;

import com.itpay.base.enums.EEffectiveStatus;
import com.itpay.core.base.dto.UuidBaseDto;

import java.util.Date;

public class MpRole extends UuidBaseDto {
    private static final long serialVersionUID = 6279555146705246738L;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态
     */
    private EEffectiveStatus roleStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 上一级id
     */
    private String upLevelRoleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public EEffectiveStatus getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(EEffectiveStatus roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpLevelRoleId() {
        return upLevelRoleId;
    }

    public void setUpLevelRoleId(String upLevelRoleId) {
        this.upLevelRoleId = upLevelRoleId == null ? null : upLevelRoleId.trim();
    }
}