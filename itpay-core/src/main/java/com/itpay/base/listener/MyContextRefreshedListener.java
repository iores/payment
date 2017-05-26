package com.itpay.base.listener;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by pst12 on 2017/5/18.
 * spring  启动时初始化指定的bean与业务的关联
 */
@Component
public class MyContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(MyContextRefreshedListener.class);



    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //spring 容器
        if(event.getApplicationContext().getParent()==null){
            logger.info("容器初始化完成，注册motan服务进入zookeeper");
            MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        }
    }
}
