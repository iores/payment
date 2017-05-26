package com.itpay;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by feng on 2017/5/22 0022.
 * 测试基础类
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath*:/spring/spring-core.xml",
        "classpath*:/spring/spring-mybatis.xml",
})
public class BaseTest {
    
    
}
