package com.dupenghao;

import com.dupenghao.component.Component2;
import com.dupenghao.event.UserRegisteredEvent;
import com.dupenghao.util.AppInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

@SpringBootApplication
@Slf4j
@EnableSwagger2WebMvc
public class SpringbootDemoApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootDemoApplication.class, args);
		AppInstance.setContext(context);
		log.info("配置context成功!");
		/**
		 * 1.到底什么是BeanFactory
		 * 		- 它是 ApplicationContext的父接口
		 * 		- 它才是 Spring 的核心容器, 主要的ApplicationContext 实现都[组合]了它的功能
		 */
		System.out.println(context);
		/**
		 * 这个getBean就是BeanFactory的功能,context只是组合了它
		 */
//		context.getBean("abc");

		/*
			2.BeanFactory 能干点啥
				-表面上只有getBean
				-实际上控制反转,基本的依赖注入,直至Bean的生命周期的各种功能,都由它的实现类提供:DefaultListableBeanFactory
		 */
		Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
		singletonObjects.setAccessible(true);
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
		map.entrySet().stream().filter(e -> e.getKey().startsWith("dupenghao"))
				.forEach(e -> {
					System.out.println(e.getKey() + "=" + e.getValue());
				});

		Resource[] resources = context.getResources("classpath*:META-INF/spring.factories");
		for (Resource resource : resources) {
			System.out.println(resource);
		}

		System.out.println(context.getEnvironment().getProperty("java_home"));
		System.out.println(context.getEnvironment().getProperty("server.port"));

//		context.publishEvent(new UserRegisteredEvent(context));
		context.getBean(Component2.class).register();

		/*
            4. 学到了什么
                a. BeanFactory 与 ApplicationContext 并不仅仅是简单接口继承的关系, ApplicationContext 组合并扩展了 BeanFactory 的功能
                b. 又新学一种代码之间解耦途径
            练习：完成用户注册与发送短信之间的解耦, 用事件方式、和 AOP 方式分别实现
         */
	}

}
