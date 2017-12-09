package com.itpay.mp.user.app;

import com.itpay.mp.user.dto.MpPermission;
import com.itpay.mp.user.dto.MpRole;
import com.itpay.mp.user.service.MpRoleManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("mpRoleAppService")
public class MpRoleAppServiceImpl implements MpRoleAppService {

    @Resource(name="mpRoleManager")
    private MpRoleManager mpRoleManager;

    /**
     * 新增角色
     *
     * @param role        角色
     * @param permissions 权限
     */
    @Override
    public void addRole(MpRole role, List<MpPermission> permissions) {
        mpRoleManager.addRole(role,permissions);
    }

    /**
     * 获取所有角色信息
     *
     * @return
     */
    @Override
    public List<MpRole> findAll() {
        return mpRoleManager.findAll();
    }
}
