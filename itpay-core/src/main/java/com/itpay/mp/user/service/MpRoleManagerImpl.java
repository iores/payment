package com.itpay.mp.user.service;

import com.itpay.mp.user.dao.MpRoleMapper;
import com.itpay.mp.user.dao.MpRolePermissionRefMapper;
import com.itpay.mp.user.dto.MpPermission;
import com.itpay.mp.user.dto.MpRole;
import com.itpay.mp.user.dto.MpRolePermissionRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by erika on 2017/9/24.
 * 用户角色管理
 */
@Service("mpRoleManager")
public class MpRoleManagerImpl implements  MpRoleManager {

    private Logger log= LoggerFactory.getLogger(MpRoleManagerImpl.class);

    @Resource(name="mpRoleMapper")
    private MpRoleMapper mpRoleMapper;

    @Resource(name="mpRolePermissionRefMapper")
    private MpRolePermissionRefMapper mpRolePermissionRefMapper;


    @Override
    public int insert(MpRole record) {
        return mpRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(MpRole record) {
        return mpRoleMapper.insertSelective(record);
    }

    @Override
    public MpRole selectByPrimaryKey(String roleId) {
        return mpRoleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(MpRole record) {
        return mpRoleMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(MpRole record) {
        return mpRoleMapper.updateByPrimaryKey(record);
    }


    @Override
    public MpRole updatePermissionByRole(MpRole role,List<MpPermission> permissions) {
        if(CollectionUtils.isEmpty(permissions)||role==null){
            log.error("角色权限保存失败！传入的角色或权限信息为空！");
            throw new RuntimeException("角色权限保存失败！传入的角色或权限信息为空！");
        }

        //先删除角色权限关联表信息
        mpRolePermissionRefMapper.deleteByRoleId(role.getRoleId());
        List<MpRolePermissionRef> rolePermissionRefs=new ArrayList<>();
        for (MpPermission mpPermission : permissions) {
            MpRolePermissionRef mpRolePermissionRef=new MpRolePermissionRef();
            mpRolePermissionRef.setId(mpRolePermissionRef.getIdentity());
            mpRolePermissionRef.setCreateTime(new Date());
            mpRolePermissionRef.setRoleId(role.getRoleId());
            mpRolePermissionRef.setPermissionId(mpPermission.getId());
            rolePermissionRefs.add(mpRolePermissionRef);
        }

        //批量保存
        mpRolePermissionRefMapper.saveBatch(rolePermissionRefs);

        return role;
    }

    /**
     * 新增角色
     *
     * @param role
     * @param permissions
     */
    @Override
    public void addRole(MpRole role, List<MpPermission> permissions) {
        if(role==null){
            log.error("新增角色失败！传入的角色为空！");
            throw new RuntimeException("新增角色失败！传入的角色为空！");
        }
        role.setRoleId(role.getIdentity());
        mpRoleMapper.insert(role);
        //初始化角色权限关联信息
        List<MpRolePermissionRef> rolePermissionRefs=new ArrayList<>();
        for (MpPermission mpPermission : permissions) {
            MpRolePermissionRef mpRolePermissionRef=new MpRolePermissionRef();
            mpRolePermissionRef.setId(mpRolePermissionRef.getIdentity());
            mpRolePermissionRef.setCreateTime(new Date());
            mpRolePermissionRef.setRoleId(role.getRoleId());
            mpRolePermissionRef.setPermissionId(mpPermission.getId());
            rolePermissionRefs.add(mpRolePermissionRef);
        }
        //批量保存
        mpRolePermissionRefMapper.saveBatch(rolePermissionRefs);

    }

    /**
     * 查询所有觉得
     *
     * @return
     */
    @Override
    public List<MpRole> findAll() {
        return mpRoleMapper.findAll();
    }
}
