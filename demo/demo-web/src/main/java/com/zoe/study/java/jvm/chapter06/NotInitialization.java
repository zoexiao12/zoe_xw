package com.zoe.study.java.jvm.chapter06;

/**
 * Created by zoe on 2017/11/6.
 */
public class NotInitialization {
    public  static void  main(String [] args) {
        /** 静态字段，只有定义这个字段的类才会被初始化，
         *  即使通过子类来引用父类的静态字段，也只会触发父类的初始化
         * */
        System.out.println(SubClass.value);

        /** 创建数组由字节码指令newarrsy触发，数组类的初始化，是由虚拟机自动生成的类，
         * 即[Lcom.zoe.study.java.jvm.chapter06.SuperClass 继承于java.lang.Object;
         * length属性和clone()方法，都由此类产生
         * 数组越界检查，封装在数组访问的(T)aload,(T)astore字节码指令中
         * */
        SuperClass [] sca = new SuperClass[10];

        /** 常量传播优化
         * 常量在编译阶段会存入调用类的常量池中，
         * 本质上没有直接引用到定义常量的类，因此不会触发定义常量类的初始化
         * */
        System.out.println(ConstClass.HELLOWORLD);
    }
}
class SuperClass {
    static  {System.out.println("SuperClass Init");}
    public static  int value =123;
}
class SubClass extends  SuperClass {
    static  {System.out.println("SubClass Init");}
}
class ConstClass {
    static  {System.out.println("ConstClass Init");}
    public static final String HELLOWORLD="hello world!";
}
