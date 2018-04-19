package com.itpay.mp.user.service;

import com.itpay.BaseTest;
import com.itpay.base.enums.ESexType;
import com.itpay.base.enums.EUserStatus;
import com.itpay.core.model.page.ListPage;
import com.itpay.core.util.PassWordUtil;
import com.itpay.mp.cache.UserCacheServer;
import com.itpay.mp.user.dto.MpPermission;
import com.itpay.mp.user.dto.MpRole;
import com.itpay.mp.user.dto.UserDto;
import com.itpay.mp.user.dto.UserLoginDto;
import com.itpay.mp.user.vo.UserLoginVo;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.UUIDEditor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by feng on 2017/5/22 0022.
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserLoginManager userLoginManager;
    @Autowired
    private MpRoleManager mpRoleManager;
    @Autowired
    private UserCacheServer userCacheServer;
    @Autowired
    @Qualifier("nioEventLoopGroup")
    private EventExecutorGroup eventExecutorGroup;

    @Test
    public void insert() {
        UserDto record = new UserDto();
        record.setId(UUID.randomUUID().toString());
        record.setCreateTime(new Date());
        record.setEmail("ccm@211113");
        record.setName("ccm321");
        record.setPhone("1318987828");
        record.setSex(ESexType.MAN);
        record.setStatus(EUserStatus.NORMAL);
        int a = userManager.insert(record);
        System.out.println(a);
    }

    @Test
    public void updatEerror() {
        //更新需要处理的数据
        String uuid = UUID.randomUUID().toString();
        userLoginManager.updateHandlerBatchNo(uuid, EUserStatus.NORMAL);
        final int pageSize = 1000;
        ListPage<UserLoginDto> listPage = new ListPage<>();
        listPage.setPageSize(pageSize);
        final UserLoginDto queryParam = new UserLoginDto();
        queryParam.setHandlerBatchNo(uuid);
        List<Future<Boolean>> fs = new ArrayList<>(20);

        ListPage<UserLoginDto> listPage1 = userLoginManager.listPage(listPage, queryParam);
        fs.add(eventExecutorGroup.submit(() -> {
            System.out.println("线程:" + Thread.currentThread().getName() + ",开始执行!");
            List<UserLoginDto> list= listPage1.getList();
            for (UserLoginDto userLoginDto : list) {
                userLoginDto.setPwdErrorNum(userLoginDto.getPwdErrorNum() + 1);
                //userLoginManager.updateByPrimaryKeySelective(userLoginDto);
            }
            userLoginManager.updateBatch(list);
            System.out.println("线程:" + Thread.currentThread().getName() + ",执行结束!");
            return true;
        }));

        for (int i = 1; i < listPage1.getPages(); i++) {
            final int pageNum = i+1 ;
            fs.add(eventExecutorGroup.submit(() -> {
                System.out.println("线程:" + Thread.currentThread().getName() + ",开始执行!");
                ListPage<UserLoginDto> listPage2 = new ListPage<>();
                listPage2.setPageSize(pageSize);
                listPage2.setPageNum(pageNum);
                List<UserLoginDto> list = userLoginManager.listPage(listPage2, queryParam).getList();
                for (UserLoginDto userLoginDto : list) {
                    userLoginDto.setPwdErrorNum(userLoginDto.getPwdErrorNum() + 1);
                   // userLoginManager.updateByPrimaryKeySelective(userLoginDto);
                }
                userLoginManager.updateBatch(list);
                System.out.println("线程:" + Thread.currentThread().getName() + ",执行结束!");
                return true;
            }));
        }

        for (Future<Boolean> f : fs) {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        userLoginManager.updateHandlerBatchNoNull(uuid);

    }

    @Test
    public void addlogin() {
        String password = PassWordUtil.createMd5("zlf123");
        List<List<UserLoginVo>> logins = new ArrayList<>(100);

        for (int j = 0; j < 100; j++) {
            List<UserLoginVo> loginVos = new ArrayList<>(10);
            for (int i = 0; i < 10000; i++) {
                UserLoginVo vo = new UserLoginVo();
                vo.setLoginName("8zlf" + i + j);
                vo.setUserId("b7dfe0c5-5163-4292-a16d-1de3c280a455");
                vo.setPassWord(PassWordUtil.createMd5(password + vo.getLoginName()));
                loginVos.add(vo);
            }
            logins.add(loginVos);
        }

        List<Future> fs = new ArrayList<>(100);
        for (List<UserLoginVo> login : logins) {
            fs.add(eventExecutorGroup.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    userLoginManager.addLoginInfo(login);
                    return null;
                }
            }));
        }

        //判断是否都执行成功
        for (Future f : fs) {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("执行错误!!!");
            }

        }


    }

    @Test
    public void insert2() {
        UserLoginDto u = new UserLoginDto();
        u.setId(UUID.randomUUID().toString());
        //u.setStatus("11");
        u.setCreateTime(new Date());
        u.setLoginName("zlf");
        u.setLoginPwd("zlf");
        u.setRegisterTime(new Date());
        u.setUserId("111111111");
        userLoginManager.insert(u);
    }

    @Test
    public void testInsterRole() {

        MpRole role = new MpRole();
        role.setRoleId("test111");
        List<MpPermission> permissions = new ArrayList<>();
        MpPermission mpPermission = new MpPermission();
        mpPermission.setId("1111111111111");
        permissions.add(mpPermission);

        MpPermission mpPermission1 = new MpPermission();
        mpPermission1.setId("222222");
        permissions.add(mpPermission1);
        mpRoleManager.updatePermissionByRole(role, permissions);
    }


    @Test
    public void testCache() {
        UserDto userDto = userCacheServer.findById("2672a7b2-7baf-4cfa-acac-db");
        System.out.println(userDto);

    }


}
