package com.dupenghao.a03;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杜鹏豪 on 2022/8/26.
 */
public class MyTestMethodTemplate {
    public static void main(String[] args) {
        MyBeanFactory myBeanFactory = new MyBeanFactory();
        myBeanFactory.addBeanPostProcessor(bean -> System.out.println("解析@Autowired"));
        Object bean = myBeanFactory.getBean();
    }

    static class MyBeanFactory {
        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造 " + bean);
            System.out.println("依赖注入 " + bean);
            processors.forEach(processor -> processor.inject(bean));
            System.out.println("初始化 " + bean);
            return bean;
        }

        private final List<BeanPostProcessor> processors = new ArrayList<>();

        public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
            processors.add(postProcessor);
        }
    }

    interface BeanPostProcessor {
        void inject(Object bean);
    }
}
