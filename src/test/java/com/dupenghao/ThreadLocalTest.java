package com.dupenghao;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 杜鹏豪 on 2022/8/30.
 */
public class ThreadLocalTest {

    private static ThreadLocal<People> threadLocal=new ThreadLocal<>();
    private static ThreadLocal<People> threadLocal_2=new ThreadLocal<>();

    //    @Test
//    public void test1(){
//        System.out.println();
//    }
    public static void main(String[] args) {
//        m1();
//        m2();
//        m3();
        m4();
    }

    private static void m4(){
        System.out.println("0123456789".substring(2));
    }

    //测试ThreadLocal
    private static void m3(){

        List<Thread> threads=new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            Thread thread = new Thread(() -> {
                threadLocal.set(new People("第个"+random.nextInt(100)+"名字", random.nextInt(100)));
                System.out.println("current Thread:"+Thread.currentThread().getName()+"===>"+threadLocal.get());
                System.out.println("current Thread:"+Thread.currentThread().getName()+"===>"+threadLocal_2.get());
            }, "第" + i + "个线程");
            threads.add(thread);

        }
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("结束");

    }

    private static void m1(){
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set("123");
        try {
            Field threadLocals = Thread.class.getDeclaredField("threadLocals");
            threadLocals.setAccessible(true);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void m2(){

    }

    private static class People{
        private String name;
        private int age;

        People(String name,int age){
            this.name=name;
            this.age=age;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
