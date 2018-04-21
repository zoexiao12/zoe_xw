package com.zoe.study.java.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zoe on 2017/7/27.
 */
public class ThreadPoolsTest {

    public static void main(String []  args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        final ThreadLocal<String> tl = new ThreadLocal<>();
        final String[] threadName = {null};

        es.submit(new Runnable() {
            @Override
            public void run() {
                tl.set("hello world");
                threadName[0] = Thread.currentThread().getName();
                System.out.println(threadName[0] + "-------ThreadLocal=="+tl.get());
            }
        });
        System.out.println("----------------------------");

        for(int i=0;i<10;i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + "--ThreadLocal=="+tl.get());
//                    if (name.equals(threadName[0])) {
//
//                    }
                }
            });
        }

    }


//    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
//    ThreadLocal<String> stringLocal = new ThreadLocal<String>();
//
//
//    public void set() {
//        longLocal.set(Thread.currentThread().getId());
//        stringLocal.set(Thread.currentThread().getName());
//    }
//
//    public long getLong() {
//        return longLocal.get();
//    }
//
//    public String getString() {
//        return stringLocal.get();
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        final ThreadPoolsTest test = new ThreadPoolsTest();
//
//
//        test.set();
//        System.out.println(test.getLong());
//        System.out.println(test.getString());
//
//
//        Thread thread1 = new Thread(){
//            public void run() {
//                test.set();
//                System.out.println(test.getLong());
//                System.out.println(test.getString());
//            };
//        };
//        thread1.start();
//        thread1.join();
//
//        System.out.println(test.getLong());
//        System.out.println(test.getString());
//    }
}
