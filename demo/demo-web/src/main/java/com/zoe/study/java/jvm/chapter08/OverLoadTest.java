package com.zoe.study.java.jvm.chapter08;

import java.io.Serializable;

/**
 * Created by zoe on 2017/11/8.
 */
public class OverLoadTest {
    public void sayHello(Object arg) {
        System.out.println("Hello Object");
    }
    public void sayHello(int arg) {
        System.out.println("Hello int");
    }
    public void sayHello(long arg) {
        System.out.println("Hello long");
    }
    public void sayHello(Character arg) {
        System.out.println("Hello Character");
    }
    public void sayHello(char arg) {
        System.out.println("Hello char");
    }
    public void sayHello(char ... arg) {
        System.out.println("Hello char ... ");
    }

    public void sayHello(Serializable arg) {
        System.out.println("Hello Serializable");
    }

    /**
     * Serializable 与 Comparable 会导致JVM编译时无法判定选用那个合适的重载版本。
     * @param c
     */
    public void sayHello(Comparable c) {
        System.out.println("Hello Comparable");
    }


    public static void main(String [] args) {
        OverLoadTest olt = new OverLoadTest();
        olt.sayHello('a');
    }
}
