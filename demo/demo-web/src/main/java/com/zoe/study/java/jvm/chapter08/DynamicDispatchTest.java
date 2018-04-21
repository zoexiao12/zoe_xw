package com.zoe.study.java.jvm.chapter08;

/**
 * Created by zoe on 2017/11/8.
 */
public class DynamicDispatchTest {
    static abstract  class Human {
        protected abstract  void sayHello();
    }
    static class Man extends DynamicDispatchTest.Human {
        @Override
        protected void sayHello() {
            System.out.println("Hello gentleman");
        }
    }
    static class Woman extends DynamicDispatchTest.Human {
        @Override
        protected void sayHello() {
            System.out.println("Hello lady");
        }
    }

    public static void main(String [] args) {
        DynamicDispatchTest.Human man = new DynamicDispatchTest.Man();
        DynamicDispatchTest.Human woman = new DynamicDispatchTest.Woman();
        man.sayHello();
        woman.sayHello();

        man = new DynamicDispatchTest.Woman();
        man.sayHello();
    }
}