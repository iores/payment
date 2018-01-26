package com.itpay.mp.base.shiro.stateless;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author lfeng1
 * @date 2018/1/26 0026
 * 无状态的Subject 工厂
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session ,每次都调用验证
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
