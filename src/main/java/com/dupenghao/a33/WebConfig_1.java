package com.dupenghao.a33;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.Controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class WebConfig_1 {
    @Bean // ⬅️内嵌 web 容器工厂
    public TomcatServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory(8080);
    }

    @Bean // ⬅️创建 DispatcherServlet
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean // ⬅️注册 DispatcherServlet, Spring MVC 的入口
    public DispatcherServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
    }

    // /c1  -->  /c1
    // /c2  -->  /c2
    @Component
    static class MyHandlerMapping implements HandlerMapping,ApplicationContextAware,InitializingBean,BeanNameAware{

        private ApplicationContext context;

        private Map<String,Controller> collect;

        @PostConstruct
        public void init(){
            Map<String, Controller> map = context.getBeansOfType(Controller.class);
            Map<String, Controller> result = map.entrySet().stream().filter(e -> e.getKey().startsWith("/")).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
            this.collect=result;
            System.out.println(result);
        }

//        @Override
//        public boolean usesPathPatterns() {
//            return false;
//        }

        @Override
        public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
            String uri = request.getRequestURI();
            Controller controller = collect.get(uri);
            if(controller!=null){
                return new HandlerExecutionChain(controller);
            }
            return null;
        }

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            context=applicationContext;
            System.out.println("application context autowired success!!!");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("aaa");
        }

        @Override
        public void setBeanName(String s) {
            System.out.println(s+">>=======================");
        }
    }

    @Component
    static class MyHandlerAdapter implements HandlerAdapter{

        @Override
        public boolean supports(Object o) {
            return o instanceof Controller;
        }

        @Override
        public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            Controller controller = (Controller) handler;
            ModelAndView modelAndView = controller.handleRequest(request, response);
            //null标识不会走视图渲染
            return null;
        }

        @Override
        public long getLastModified(HttpServletRequest httpServletRequest, Object o) {
            return -1;
        }
    }

    @Component("/c1")
    public static class Controller1 implements Controller {
        @Override
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.getWriter().print("this is c1");
            return null;
        }
    }

    @Component("c2")
    public static class Controller2 implements Controller {
        @Override
        public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
            response.getWriter().print("this is c2");
            return null;
        }
    }

    @Bean("/c3")
    public Controller controller3() {
        return (request, response) -> {
            response.getWriter().print("this is c3");
            return null;
        };
    }
}
