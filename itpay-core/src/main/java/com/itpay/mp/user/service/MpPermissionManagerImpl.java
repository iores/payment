package com.itpay.mp.user.service;

import com.itpay.mp.user.dao.MpPermissionMapper;
import com.itpay.mp.user.dto.MpPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("mpPermissionManager")
public class MpPermissionManagerImpl implements MpPermissionManager {

    private Logger log= LoggerFactory.getLogger(MpPermissionManagerImpl.class);

    @Resource(name="mpPermissionMapper")
    private MpPermissionMapper mpPermissionMapper;

    @Override
    public int insert(MpPermission record) {
        return mpPermissionMapper.insert(record);
    }

    @Override
    public int insertSelective(MpPermission record) {
        return mpPermissionMapper.insertSelective(record);
    }

    @Override
    public MpPermission selectByPrimaryKey(String id) {
        return mpPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MpPermission record) {
        return mpPermissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MpPermission record) {
        return mpPermissionMapper.updateByPrimaryKey(record);
    }

    /**
     * 获取所有资源
     *
     * @return
     */
    @Override
    public List<MpPermission> findAll() {
        return mpPermissionMapper.findAll();
    }
}
