package com.dupenghao.a20;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;

/**
 * Created by 杜鹏豪 on 2022/8/31.
 */
public class AutoGetArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        AutoGet autoGet = methodParameter.getMethodAnnotation(AutoGet.class);
        Annotation[] methodAnnotations = methodParameter.getMethodAnnotations();
        System.out.println("methodAnnotations: ");
        for (Annotation methodAnnotation : methodAnnotations) {
            System.out.println(methodAnnotation);
        }
        System.out.println(autoGet != null);
        return autoGet != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return null;
    }
}
