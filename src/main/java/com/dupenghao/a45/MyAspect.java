package com.dupenghao.a45;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MyAspect {

    // 故意对所有方法增强
    @Before("execution(* com.dupenghao.a45.Bean1.*(..))")
    public void before() {
//        log.info("==============");
        System.out.println("before");
    }
}
