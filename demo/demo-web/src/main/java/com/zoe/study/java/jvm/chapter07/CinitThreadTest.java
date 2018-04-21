package com.zoe.study.java.jvm.chapter07;

/**
 * Created by zoe on 2017/11/7.
 */
public class CinitThreadTest {
    static class DeadLoopClass {
        static {
            if (true) {
                System.out.println(Thread.currentThread() + " init CinitThreadTest");
//                while (true) {}
            }
        }
    }

    public static void main(String [] args) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start ");
                DeadLoopClass dl = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over ");
            }
        };
        Thread t1 = new Thread(run);
        Thread t2 = new Thread(run);
        t1.start();
        t2.start();
    }
}
