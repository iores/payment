package com.itpay;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by feng on 2017/5/23 0023.
 * 
 */
public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {

    static {
        //改变系统参数
        System.setProperty("log4j.configurationFile","classpath:config/log4j2.xml");
        //重新初始化Log4j2的配置上下文
        LoggerContext context =(LoggerContext) LogManager.getContext(false);
        context.reconfigure();
    }

    public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}
