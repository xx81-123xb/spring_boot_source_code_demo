package com.dupenghao.config;

import com.dupenghao.pojo.Person;
import com.dupenghao.util.AppInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by 杜鹏豪 on 2022/8/25.
 */
@Configuration
@Slf4j
@Component
public class WebConfig implements WebMvcConfigurer, ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        servletContext
        log.info("boot is on startup!");
    }

    //    @Override
//    public void initialize(ConfigurableApplicationContext applicationContext) {
////        Person person = (Person) applicationContext.getBean("dupenghao");
////        System.out.println(person);
//        AppInstance.setContext(applicationContext);
//        log.info("设置context成功!");
//    }
    @Bean
    public Docket customImplation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(myApiInfo())
                .groupName("2.X版本")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dupenghao.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo myApiInfo() {
        ApiInfo info = new ApiInfoBuilder()
                .description("# swagger-bootstrap-ui-demo RESTful APIs")
                .termsOfServiceUrl("http://www.xx.com/")
//                .contact("xx@qq.com")
                .version("1.0.0")
                .build();
        return info;
    }
}
