package com.zoe.study.java.jvm.chapter08;

/**
 * Created by zoe on 2017/11/8.
 */
public class Dispatch {
    static class QQ {}
    static class _360 {}

    public static class  Father {
        public void  hardChoise (QQ arg) {
            System.out.println("father choise qq");
        }
        public void  hardChoise (_360 arg) {
            System.out.println("father choise _360");
        }
    }

    public static class Son extends Father {
        public void  hardChoise (QQ arg) {
            System.out.println("Son choise qq");
        }
        public void  hardChoise (_360 arg) {
            System.out.println("Son choise _360");
        }
    }

    public static void main(String [] args) {
        Father f = new Father();
        Father s = new Son();

        f.hardChoise(new _360());
        s.hardChoise(new QQ());
    }
}
