package com.dupenghao;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * Created by 杜鹏豪 on 2022/8/30.
 */
public class ThreadLocalTest {

    //    @Test
//    public void test1(){
//        System.out.println();
//    }
    public static void main(String[] args) {
        m1();
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

}
