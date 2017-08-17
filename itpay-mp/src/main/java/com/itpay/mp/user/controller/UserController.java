package com.itpay.mp.user.controller;

import com.itpay.core.model.page.ListPage;
import com.itpay.mp.user.app.UserAppService;
import com.itpay.mp.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feng on 2017/5/25 0025.
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Resource(name="userAppService")
    private UserAppService userAppService;


    /**
     * 返回详情
     * @param id
     * @return
     */
    @RequestMapping("/goDetail")
    public ModelAndView goDetail(String id){
        UserDto userDto=userAppService.selectByPrimaryKey(id);
        Map<String,Object> userDtoMap=new HashMap<>();
        userDtoMap.put("user",userDto);

        return new ModelAndView("user/user_detail",userDtoMap);
    }

    /**
     * 查看列表
     * @return
     */
    @RequestMapping("/goList")
    public ModelAndView goList(){
        ListPage<UserDto> listPage=new ListPage<>();
        

        return new ModelAndView("user/list");
    }

}
