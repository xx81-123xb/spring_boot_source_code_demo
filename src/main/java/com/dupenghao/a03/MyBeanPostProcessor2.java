package com.dupenghao.a03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by 杜鹏豪 on 2024/6/19.
 */
public class MyBeanPostProcessor2 implements BeanPostProcessor {

    private final Logger logger = LoggerFactory.getLogger(MyBeanPostProcessor2.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessAfterInitialization");
        return bean;
    }
}
