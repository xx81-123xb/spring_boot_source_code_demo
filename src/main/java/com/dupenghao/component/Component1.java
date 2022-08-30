package com.dupenghao.component;

import com.dupenghao.event.UserRegisteredEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by 杜鹏豪 on 2022/8/26.
 */
@Slf4j
@Component
public class Component1 {

    @EventListener
    public void receiveUserRegisterEvent(UserRegisteredEvent event){
        log.debug("{}",event);
        log.debug("收到注册事件!发送短信!");
    }

}
