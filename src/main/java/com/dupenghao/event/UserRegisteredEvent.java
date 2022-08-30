package com.dupenghao.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * Created by 杜鹏豪 on 2022/8/26.
 */
public class UserRegisteredEvent extends ApplicationEvent {
    public UserRegisteredEvent(Object source) {
        super(source);
    }

    public UserRegisteredEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
