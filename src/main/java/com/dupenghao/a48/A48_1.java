package com.dupenghao.a48;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// 事件解耦例子
@Configuration
public class A48_1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A48_1.class);
        context.getBean(MyService.class).doBusiness();
        context.close();
    }

    static class MyEvent extends ApplicationEvent {
        public MyEvent(Object source) {
            super(source);
        }
    }

    @Component
    static class MyService implements ApplicationContextAware {
        private static final Logger log = LoggerFactory.getLogger(MyService.class);
        //        @Autowired
        private ApplicationEventPublisher publisher; // applicationContext

        public void doBusiness() {
            log.debug("主线业务");
            // 主线业务完成后需要做一些支线业务，下面是问题代码
            publisher.publishEvent(new MyEvent("MyService.doBusiness()"));
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            publisher = applicationContext;
        }
    }

    //    @Component
    static class SmsApplicationListener implements ApplicationListener<MyEvent> {
        private static final Logger log = LoggerFactory.getLogger(SmsApplicationListener.class);

        @Override
        public void onApplicationEvent(MyEvent event) {
            log.debug("发送短信");
        }
    }

    @Component
    @Slf4j
    static class EmailApplicationListener implements ApplicationListener<MyEvent> {
        //        private static final Logger log = LoggerFactory.getLogger(EmailApplicationListener.class);
        @Override
        public void onApplicationEvent(MyEvent event) {
            log.debug("发送邮件");
            System.out.println(event.getSource().toString());
        }
    }

}