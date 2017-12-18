package com.itpay.mp.user.controller;

import com.itpay.base.enums.ESexType;
import com.itpay.base.enums.EUserStatus;
import com.itpay.core.model.page.ListPage;
import com.itpay.mp.user.app.UserAppService;
import com.itpay.mp.user.app.UserLoginAppService;
import com.itpay.mp.user.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 2017/5/25 0025.
 * 用户信息
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Resource(name = "userAppService")
    private UserAppService userAppService;
    /**
     * 登陆用户信息
     */
    @Resource(name = "userLoginAppService")
    private UserLoginAppService userLoginAppService;


    /**
     * 返回详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/goDetail")
    public ModelAndView goDetail(String id) {
        Map<String, Object> userDtoMap = new HashMap<>();
        UserDto userDto = userAppService.selectByPrimaryKey(id);

        userDtoMap.put("user", userDto);

        return new ModelAndView("user/user_detail", userDtoMap);
    }

    /**
     * 查看列表
     *
     * @return
     */
    @RequestMapping("/goList")
    public ModelAndView goList(ListPage<UserDto> listPage, UserDto queryParam, HttpServletRequest request) {
        listPage = userAppService.listPage(listPage, queryParam);
        Map<String, Object> userDtoMap = new HashMap<>();
        userDtoMap.put("page", listPage);
        userDtoMap.put("queryParam", queryParam);
        userDtoMap.put("sexTypes", ESexType.values());
        userDtoMap.put("userStatus", EUserStatus.values());
        return new ModelAndView("user/user_list", userDtoMap);
    }


}
