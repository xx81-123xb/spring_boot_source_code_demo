package com.dupenghao.proxy.jdk;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by 杜鹏豪 on 2023/9/19.
 */
public class JdkDemo {

    public static void main(String[] args) throws IOException {
        Target target = new Target();
        ClassLoader loader = Target.class.getClassLoader();
        Foo proxy = (Foo) Proxy.newProxyInstance(
                loader,
                new Class[]{Foo.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before...");
                        Object rlt = method.invoke(target, args);
                        System.out.println("after...");
                        return rlt;
                    }
                }
        );
        proxy.foo();
        System.out.println(proxy.getClass());
        System.in.read();
    }

}

class Target implements Foo {
    @Override
    public void foo() {
        System.out.println("foo");
    }
}

interface Foo {
    void foo();
}
