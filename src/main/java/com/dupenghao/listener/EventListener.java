package com.dupenghao.listener;

import com.dupenghao.pojo.Person;
import com.dupenghao.util.AppInstance;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by 杜鹏豪 on 2022/8/25.
 */
@Component
public class EventListener implements ApplicationListener<ApplicationEvent>{
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
//        Object source = event.getSource();
//        System.out.println(event);
//        System.out.println("===============event===============");
//        if(event instanceof ContextRefreshedEvent){
//            Person person = (Person) AppInstance.getContext().getBean("dupenghao");
//            System.out.println(person);
//        }
//        System.out.println(event);
    }
}
