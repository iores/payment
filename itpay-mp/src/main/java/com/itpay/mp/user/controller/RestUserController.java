package com.itpay.mp.user.controller;

import com.itpay.core.model.page.ListPage;
import com.itpay.mp.user.app.UserAppService;
import com.itpay.mp.user.app.UserLoginAppService;
import com.itpay.mp.user.dto.UserDto;
import com.itpay.restfull.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 2017/5/25 0025.
 *
 * @author zlf
 * 用户信息  rest  接口
 */
@Controller
@RequestMapping("/rest/user")
@ResponseBody
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
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultCode list(ListPage<UserDto> listPage, UserDto queryParam, HttpServletRequest request) {
        listPage = userAppService.listPage(listPage, queryParam);
        Map<String, Object> userDtoMap = new HashMap<>(10);
        userDtoMap.put("page", listPage);
        userDtoMap.put("queryParam", queryParam);
        return new ResultCode(ResultCode.OK, ResultCode.OK, userDtoMap);
    }


}
