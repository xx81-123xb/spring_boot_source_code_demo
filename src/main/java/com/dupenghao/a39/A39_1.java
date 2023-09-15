package com.dupenghao.a39;

import com.dupenghao.util.SetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Method;
import java.util.Set;

@Configuration
public class A39_1 {

    private final Logger log = LoggerFactory.getLogger(A39_1.class);

    public static void main(String[] args) throws Exception {
        System.out.println("1. 演示获取 Bean Definition 源");
        SpringApplication spring = new SpringApplication(A39_1.class);
        spring.setSources(SetUtil.of("classpath:b01.xml"));
        System.out.println("2. 演示推断应用类型");//根据当前类路径下是否有某些类去判断
        Method method = WebApplicationType.class.getDeclaredMethod("deduceFromClasspath");
        method.setAccessible(true);
        System.out.println("\t应用类型为: " + method.invoke(null));
        System.out.println("3. 演示 ApplicationContext 初始化器");
        spring.addInitializers(context -> {
            GenericApplicationContext gac = (GenericApplicationContext) context;
            gac.registerBean("bean3", Bean3.class);
            System.out.println("=================================");
        });
        System.out.println("4. 演示监听器与事件");
        spring.addListeners(applicationEvent -> System.out.println("\t事件为:" + applicationEvent.getClass()));
        System.out.println("5. 演示主类推断");
        Method deduceMainApplicationClass = SpringApplication.class.getDeclaredMethod("deduceMainApplicationClass");
        deduceMainApplicationClass.setAccessible(true);
        System.out.println("主类是: " + deduceMainApplicationClass.invoke(spring));

        ConfigurableApplicationContext context = spring.run();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("name: " + name + "  source: " + context.getBeanFactory().getBeanDefinition(name).getResourceDescription());
        }

        // 创建 ApplicationContext
        // 调用初始化器 对 ApplicationContext 做扩展
        // ApplicationContext.refresh

        context.close();
        /*
            学到了什么
            a. SpringApplication 构造方法中所做的操作
                1. 可以有多种源用来加载 bean 定义
                2. 应用类型推断
                3. 容器初始化器
                4. 演示启动各阶段事件
                5. 演示主类推断
         */
    }

    static class Bean1 {

    }

    static class Bean2 {

    }

    static class Bean3 {

    }

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }

    @Bean
    public TomcatServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

}
