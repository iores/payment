package com.itpay.mp.user.controller;

import com.itpay.core.model.page.ListPage;
import com.itpay.mp.user.app.UserAppService;
import com.itpay.mp.user.app.UserLoginAppService;
import com.itpay.mp.user.dto.UserDto;
import com.itpay.mp.user.dto.UserLoginDto;
import com.itpay.mp.user.query.QueryParam;
import com.itpay.mp.user.vo.UserFrozenVo;
import com.itpay.restfull.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 2017/5/25 0025.
 *
 * @author zlf
 * 用户信息  rest  接口
 */
@RestController
@RequestMapping("/rest/user")
public class RestUserController {

    private static final Logger logger = LoggerFactory.getLogger(RestUserController.class);


    @Resource(name = "userAppService")
    private UserAppService userAppService;
    /**
     * 登陆用户信息
     */
    @Resource(name = "userLoginAppService")
    private UserLoginAppService userLoginAppService;


    /**
     * 查看列表
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST , produces = { "application/json;charset=UTF-8" })
    public ResultCode list(@RequestBody QueryParam<UserDto> queryParam) {
        ListPage<UserDto> listPage = userAppService.listPage(queryParam.getListPage(), queryParam.getQueryParam());
        Map<String, Object> userDtoMap = new HashMap<>(10);
        userDtoMap.put("page", listPage);
        userDtoMap.put("queryParam", queryParam.getQueryParam());
        return new ResultCode(ResultCode.OK, ResultCode.OK, userDtoMap);
    }

    /**
     * 获取用户详情
     * @return
     */
    @RequestMapping(value = "/detail/{detail}", method = RequestMethod.GET , produces = { "application/json;charset=UTF-8" })
    public ResultCode detail( @PathVariable(name="detail") String detailId) {
        UserDto userDto =  userAppService.selectByPrimaryKey(detailId);
        if(userDto!=null){
            return new ResultCode(ResultCode.OK, ResultCode.OK, userDto);
        }
        return new ResultCode(ResultCode.OK, ResultCode.OK);
    }


    /**
     * 冻结 解冻用户
     * @param queryParam
     * @return
     */
    @RequestMapping(value = "/frozen", method = RequestMethod.POST , produces = { "application/json;charset=UTF-8" })
    public ResultCode frozen(@RequestBody UserFrozenVo userFrozen) {
        if(userFrozen == null){
            logger.info("用户冻结/解冻处理失败!传入参数为空");
            return new ResultCode(ResultCode.ERROR, "传入参数为空");
        }
        try{
            userAppService.frozenAndNofrozen(userFrozen.getId(),userFrozen.getUserStatus());
            return new ResultCode(ResultCode.OK, ResultCode.OK);
        }catch (Exception e){
            logger.info("用户冻结/解冻处理失败!",e.getMessage());
            return new ResultCode(ResultCode.ERROR, e.getMessage());
        }
    }


    /**
     * 查看登录用户列表
     *
     * @return
     */
    @RequestMapping(value = "/UserLoginList", method = RequestMethod.POST , produces = { "application/json;charset=UTF-8" })
    public ResultCode loginList(@RequestBody QueryParam<UserLoginDto> queryParam) {
        ListPage<UserLoginDto> listPage = userLoginAppService.listPage(queryParam.getListPage(), queryParam.getQueryParam());
        Map<String, Object> userDtoMap = new HashMap<>(10);
        userDtoMap.put("page", listPage);
        userDtoMap.put("queryParam", queryParam.getQueryParam());
        return new ResultCode(ResultCode.OK, ResultCode.OK, userDtoMap);
    }
}
