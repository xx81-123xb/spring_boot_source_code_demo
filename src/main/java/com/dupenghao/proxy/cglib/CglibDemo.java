package com.dupenghao.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by 杜鹏豪 on 2023/9/19.
 */
public class CglibDemo {

    public static void main(String[] args) throws IOException {
        Target target = new Target();

        Target proxy = (Target) Enhancer.create(
                Target.class,
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    System.out.println("cglib before..."+method.getName());
                    Object rlt = method.invoke(target, objects);
                    System.out.println("cglib after..."+method.getName());
                    return rlt;
                }
        );

        System.out.println(proxy.getClass());

        proxy.foo();
        proxy.bar();
        proxy.foo_final();
        System.in.read();
    }

}

class Target {
    public void foo() {
        System.out.println("foo");
    }

    public int foo_return1(){
        System.out.println("foo_return1");
        return 1;
    }

    public static void bar() {
        System.out.println("bar");
    }

//    private void foo_private() {
//        System.out.println("foo_private");
//    }

    public final void foo_final() {
        System.out.println("foo_final");
    }

}
