package com.itpay.mp.user.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itpay.base.enums.ECoreExceptionCodeType;
import com.itpay.base.enums.EUserStatus;
import com.itpay.core.exception.CoreBusiRunTimeException;
import com.itpay.core.model.page.ListPage;
import com.itpay.core.util.PassWordUtil;
import com.itpay.core.util.ValidateUtils;
import com.itpay.mp.user.dao.UserLoginDtoMapper;
import com.itpay.mp.user.dto.UserDto;
import com.itpay.mp.user.dto.UserLoginDto;
import com.itpay.mp.user.vo.UserLoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author feng
 * @date 2017/5/29 0029
 * 用户登陆
 */
@Service("userLoginManager")
public class UserLoginManagerImpl implements UserLoginManager {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginManagerImpl.class);


    @Resource(name = "userLoginDtoMapper")
    private UserLoginDtoMapper userLoginDtoMapper;


    /**
     * 插入
     *
     * @param record
     * @return
     */
    @Override
    public void insert(UserLoginDto record) {
        userLoginDtoMapper.insert(record);
    }

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    @Override
    public UserLoginDto selectByPrimaryKey(String id) {
        return userLoginDtoMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public void updateByPrimaryKeySelective(UserLoginDto record) {
        userLoginDtoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    @Override
    public void updateByPrimaryKey(UserLoginDto record) {
        userLoginDtoMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据用户登陆名称查询出用户信息
     *
     * @param loginName 登陆名称
     * @return UserLoginDto
     */
    @Override
    public UserLoginDto findByUserLoginName(String loginName) {
        return userLoginDtoMapper.findByUserLoginName(loginName);
    }

    /**
     * 根据用户id查询登陆用户信息
     *
     * @param userId 用户的id
     * @return List<UserLoginDto>
     */
    @Override
    public List<UserLoginDto> findByUserId(String userId) {
        return userLoginDtoMapper.findByUserId(userId);
    }

    /**
     * 增加登录用户
     *
     * @param loginVos 新增登录用户请求信息
     */
    @Override
    public void addLoginInfo(List<UserLoginVo> loginVos) {
        for (UserLoginVo loginVo : loginVos) {
            try {
                ValidateUtils.valid(loginVo);
            } catch (Exception e) {
                logger.warn("传入参数验证失败!传入参数:[{}],错误信息:[{}]",loginVo,e.getMessage());
                throw new CoreBusiRunTimeException(ECoreExceptionCodeType.FIELD_CHEACK_ERROR.getValue(),e.getMessage());
            }
            //将密码加密
            String password = PassWordUtil.bcryptHashpw(loginVo.getPassWord());
            //保存登录用户信息
            userLoginDtoMapper.insertSelective(assemblyUserLogin(loginVo.getLoginName(),password,loginVo.getUserId()));
        }
    }

    /**
     * 分页查询
     *
     * @param listPage
     * @param queryParam
     * @return
     */
    @Override
    public ListPage<UserLoginDto> listPage(ListPage<UserLoginDto> listPage, UserLoginDto queryParam) {

        PageHelper.startPage(listPage.getPageNum(),listPage.getPageSize());
        List<UserLoginDto> list=userLoginDtoMapper.findBySearch(queryParam);
        Page<UserLoginDto> page = (Page<UserLoginDto>) list;
        listPage.setTotal(page.getTotal());
        listPage.setPages(page.getPages());
        listPage.setList(list);
        return listPage;
    }


    /**
     * 组装登录用户信息
     * @param loginName 登录名称
     * @param loginPwd 登录密码
     * @param userId 用户id
     * @return 组装的数据
     */
    private UserLoginDto assemblyUserLogin(String loginName, String loginPwd, String userId) {
        Date date = new Date();
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setId(userLoginDto.getIdentity());
        userLoginDto.setCreateTime(date);
        userLoginDto.setRegisterTime(date);
        userLoginDto.setStatus(EUserStatus.NORMAL);
        userLoginDto.setPwdErrorNum(0);
        userLoginDto.setLoginName(loginName);
        userLoginDto.setLoginPwd(loginPwd);
        userLoginDto.setUserId(userId);
        return userLoginDto;
    }
}
