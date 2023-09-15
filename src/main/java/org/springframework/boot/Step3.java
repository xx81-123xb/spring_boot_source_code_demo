package org.springframework.boot;

import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
//-DJAVA_HOME=123  第三步进行了environment对象的创建
public class Step3 {
    public static void main(String[] args) throws IOException {
        ApplicationEnvironment env = new ApplicationEnvironment(); // 系统环境变量, properties, yaml
        env.getPropertySources().addLast(new ResourcePropertySource("classpath:application.properties"));
        env.getPropertySources().addFirst(new SimpleCommandLinePropertySource(args));//第三步只加了这个properties的来源:命令行properties
        for (PropertySource<?> ps : env.getPropertySources()) {
            System.out.println(ps);
        }
        System.out.println(env.getProperty("JAVA_HOME"));

        System.out.println(env.getProperty("server.port"));
        System.out.println(env.getProperty("dph"));
    }
}
