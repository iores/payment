package com.itpay.mp.user.app;

import com.itpay.mp.user.dto.MpPermission;
import com.itpay.mp.user.service.MpPermissionManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lfeng1
 */
@Service("mpPermissionAppSerice")
public class MpPermissionAppSericeImpl implements MpPermissionAppSerice {

    @Resource(name="mpPermissionManager")
    private MpPermissionManager mpPermissionManager;

    /**
     * 新增权限信息
     *
     * @param record
     */
    @Override
    public void addPermission(MpPermission record) {
        mpPermissionManager.insert(record);
    }

    /**
     * 查询所有权限信息
     *
     * @return
     */
    @Override
    public List<MpPermission> findAll() {
        return mpPermissionManager.findAll();
    }
}
