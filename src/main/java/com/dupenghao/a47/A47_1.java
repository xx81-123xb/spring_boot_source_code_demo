package com.dupenghao.a47;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

@Configuration
public class A47_1 {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A47_1.class);
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // 1. 根据成员变量的类型注入
        DependencyDescriptor dd1=new DependencyDescriptor(Bean1.class.getDeclaredField("bean2"),false);
        Object bean2 = beanFactory.doResolveDependency(dd1, "bean1", null, null);
        System.out.println(bean2);
        // 2. 根据参数的类型注入
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(Bean1.class.getDeclaredMethod("setBean2", Bean2.class), 0), false);
        Object bean2_method = beanFactory.doResolveDependency(dd2, "bean1", null, null);
        System.out.println(bean2_method);
        // 3. 结果包装为 Optional<Bean2>
        DependencyDescriptor dd3 = new DependencyDescriptor(Bean1.class.getDeclaredField("bean3"), false);
//        System.out.println(beanFactory.doResolveDependency(dd3,"bean1",null,null));
        if(dd3.getDependencyType() == Optional.class){
            dd3.increaseNestingLevel();
            Object result = beanFactory.doResolveDependency(dd3, "bean1", null, null);
            System.out.println(result);
            Optional<Object> optional = Optional.ofNullable(result);
        }
        // 4. 结果包装为 ObjectProvider,ObjectFactory
        //类似于Optional
        DependencyDescriptor dd4=new DependencyDescriptor(Bean1.class.getDeclaredField("bean4"),false);
        if(dd4.getDependencyType() == ObjectFactory.class){
            dd4.increaseNestingLevel();
            ObjectFactory objectFactory= () -> {
                Object result = beanFactory.doResolveDependency(dd4, "bean1", null, null);
                return result;
            };
            System.out.println(objectFactory.getObject());
        }

        // 5. 对 @Lazy 的处理

        /*
            学到了什么
                1. Optional 及 ObjectFactory 对于内嵌类型的处理, 源码参考 ResolvableType
                2. ObjectFactory 懒惰的思想
                3. @Lazy 懒惰的思想
            下一节, 继续学习 doResolveDependency 内部处理
         */
    }

    static class Bean1 {
        @Autowired @Lazy private Bean2 bean2;
        @Autowired public void setBean2(Bean2 bean2) {
            this.bean2 = bean2;
        }
        @Autowired private Optional<Bean2> bean3;
        @Autowired private ObjectFactory<Bean2> bean4;
    }

    @Component("bean2")
    static class Bean2 {
        /*@Override
        public String toString() {
            return super.toString();
        }*/
    }
}
