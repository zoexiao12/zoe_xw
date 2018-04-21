package com.zoe.study.java.jvm.chapter11;

/**
 * Created by zoe on 2017/7/16.
 * -XX:+PrintCompilation  虚拟机在即时编译时会将被编译成本地代码的方法名称打印出来。
 * -XX:+PrintInlining  虚拟机输出方法内联信息
 * -XX:+PrintAssembly  虚拟机打印编译方法的汇编代码 需要装插件
 */
public class TestCode {
    public static final int NUM=15000;
    public static int doubleValue(int i){
        for (int j =0;j<10000;j++);
        return i * 2;
    }

    public static long calcSum() {
        long sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }
    public static void main(String [] args) {
        for (int i = 0; i <NUM ; i++) {
            System.out.println("i = "+ i + " value = "+ calcSum());
        }
    }
}
