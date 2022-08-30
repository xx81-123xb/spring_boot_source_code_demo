package com.dupenghao.a04.mya04;

import com.dupenghao.a04.Bean1;
import com.dupenghao.a04.Bean2;
import com.dupenghao.a04.Bean3;
import com.dupenghao.a04.Bean4;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created by 杜鹏豪 on 2022/8/26.
 */
public class MyA04 {

    public static void main(String[] args) {
        //是一个干净的容器
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("bean1",Bean1.class);
        context.registerBean("bean2",Bean2.class);
        context.registerBean("bean3",Bean3.class);
        context.registerBean("bean4",Bean4.class);

        //加了这个后可以做@Value中值的获取
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);//@Autowired @Value

        context.registerBean(CommonAnnotationBeanPostProcessor.class);//@Resource @PostConstruct @PreDestroy

        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());//@ConfigurationProperties

        context.refresh();//执行beanFactory,添加bean后处理器,初始化所有单例

        Object bean4 = context.getBean("bean4");

        System.out.println(bean4);
    }

}
