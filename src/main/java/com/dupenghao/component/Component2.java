package com.dupenghao.component;

import com.dupenghao.event.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Created by 杜鹏豪 on 2022/8/26.
 */
@Slf4j
@Component
public class Component2 {

    @Autowired
    private ApplicationEventPublisher context;

    public void register(){
        log.debug("用户注册!");
        context.publishEvent(new UserRegisteredEvent(this));
    }

}
