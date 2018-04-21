package com.zoe.study.java.jvm.chapter11;

/**
 * Created by zoe on 2017/7/16.
 */
public class JavaOptimizeDemo {
    static class B {
        static int value;
        final static int get() {
            return value;
        }
    }

    B b =null;

    public void foo() {
//       int y = B.get();
//        //do stuff
//        int z = B.get();
//        int sum = y + z;

/*         //1. 内联后的大致代码
       int y = B.value;
        int z = B.value;
        int sum = y + z;
 */

/*
        //2. 冗余的消除
        int y = B.value;
        int z = y;
        int sum = y + z;
*/

/*
    //3. 复写传播
        int y = B.get();
        y = y;
        int sum = y + y;
 */

    //4. 无用代码消除
        int y = B.get();
        int sum = y + y;
    }

    public  int  testCanel(){
        //正常代码块
        if (b != null) {
            return b.value;
        }else {
            throw  new NullPointerException();
        }

 /*
        //隐式异常优化 伪代码
        try {
            return b.value;
        }catch (segment_fault) {
                uncommon_trap();
        }
*/
    }

    public static void foo_2(Object obj) {
        if(obj != null) {
            System.out.println("do something");
        }
    }

    public static void testInline(String [] args) {
        Object obj = null;
        foo_2(obj);
    }
}


