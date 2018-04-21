package com.zoe.study.java.jvm.chapter08;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by zoe on 2017/11/8.
 */
public class MethodHandleTest {
    static class ClassA{
        public void println(String s) {}
    }
    private MethodHandle getPrintlnMH(Object reveiver) throws Throwable {
        /** MethodType : 方法类型，包含方法以下信息
         * 1 返回值参数类型(methodType()方法的第一个参数)，
         * 2 具体参数类型 (methodType()方法的第二个及以后的参数)，* */
        MethodType mt = MethodType.methodType(void.class,String.class);
        /** MethodHandles.lookup().findxxx()
         * 在指定类中查找符合给定的方法名称，类型，符合调用权限的的方法句柄* */
        MethodHandle mh = MethodHandles.lookup().findVirtual(reveiver.getClass(),"println",mt);
        /** 此处为虚方法，Java语言规则，方法第一个参数是隐式的，代表方法接收者，
         * 即this指向的对象，传统调用时候，放在参数列表中进行传递，
         * 此处通过bindTo()完成。* */
        mh = mh.bindTo(reveiver);
        return mh;
    }
    public static void main(String [] args) throws Throwable{
        /** 无论obj是什么对象，只要有println(s)方法就都能调用到* */
        Object obj = System.currentTimeMillis() % 2 == 0 ?System.out : new ClassA();
        MethodHandleTest test = new MethodHandleTest();
        MethodHandle mh = test.getPrintlnMH(obj);
        mh.invoke("icyfenix");
    }
}
