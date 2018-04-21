package com.zoe.study.java.effective.chapter08;

/**
 * Created by  on 2017/8/18.
 */
public class Unbelivable {

    /**
     * 此处i的值为null。
     * null对象引用被自动拆箱，抛出NullPointerException异常。
     * */
//    static Integer i;
    /**
     *  修正方式 ： 将Integer 改为 int
     * */
    static int i;
    public static void main(String [] args) {
//        if(i == 42) {
//            System.out.println(" Unbel ivable!");
//        }

        /**
         * 性能严重下降
         * sum声明为Long类型，计算过程中会导致频繁的拆箱。
         * 解决办法： 将sum声明为long
         */
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (long j = 0;  j < Integer.MAX_VALUE ;  j++) {
            sum += j;
        }
        System.out.println("Long 类型变量使用时间：" + ( System.currentTimeMillis() - start));
        System.out.println("sum =" + sum);


        long sum2 = 0L;
        start = System.currentTimeMillis();
        for (long j = 0;  j < Integer.MAX_VALUE ;  j++) {
            sum2 += j;
        }
        System.out.println("long 类型变量使用时间：" + ( System.currentTimeMillis() - start));
        System.out.println("sum2 =" + sum2);
    }

}
