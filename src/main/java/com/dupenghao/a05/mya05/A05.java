package com.dupenghao.a05.mya05;

import com.dupenghao.a05.Config;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

/**
 * Created by 杜鹏豪 on 2022/8/26.
 */
public class A05 {
    public static void main(String[] args) throws Exception {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        BeanNameGenerator generator=new AnnotationBeanNameGenerator();
        if (componentScan != null){
            for (String p : componentScan.basePackages()) {
                System.out.println(p);
                //com.dupenghao.a05.component -> classpath*:com/dupenghao/a05/component/**/*.class
                String path = "classpath*:" + p.replace(".", "/") + "/**/*.class";
                System.out.println(path);
                CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
                Resource[] resources = context.getResources(path);

                for (Resource resource : resources) {
                    MetadataReader reader = factory.getMetadataReader(resource);
                    System.out.println("类名"+reader.getClassMetadata().getClassName());
                    AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
                    System.out.println("是否加了@Component注解" + annotationMetadata.hasAnnotation(Component.class.getName()));
                    System.out.println("是否加了@Component派生注解" + annotationMetadata.hasMetaAnnotation(Component.class.getName()));

                    if(annotationMetadata.hasAnnotation(Component.class.getName())
                            ||annotationMetadata.hasMetaAnnotation(Component.class.getName())){
                        AbstractBeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(reader.getClassMetadata().getClassName()).getBeanDefinition();
                        String beanName = generator.generateBeanName(bd, beanFactory);
                        beanFactory.registerBeanDefinition(beanName,bd);

                    }
                }
            }
        }

        context.refresh();

        for (String s : context.getDefaultListableBeanFactory().getBeanDefinitionNames()) {
            System.out.println(s);
        }
        context.close();
    }
}
