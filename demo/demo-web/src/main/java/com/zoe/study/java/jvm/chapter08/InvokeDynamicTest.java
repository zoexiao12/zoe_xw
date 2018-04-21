package com.zoe.study.java.jvm.chapter08;

import java.lang.invoke.*;

/**
 * Created by zoe on 2017/11/8.
 */
public class InvokeDynamicTest {
    public static void  testMethod(String s) {
        System.out.println("Hello String :" +s);
    }

    public static CallSite bootstrapMethod(MethodHandles.Lookup lk,
            String methodName, MethodType mt )throws Throwable {
        MethodHandle mk = lk.findStatic(InvokeDynamicTest.class,methodName,mt);
        CallSite cs = new ConstantCallSite(mk);
        return cs;
    }
    private static MethodType mt_bootstrapMethod() throws Throwable{
        MethodType mt = MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles $Lookup;" +
                "Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",null);
        return mt;
    }
    private static MethodHandle mh_bootstrapMethod() throws Throwable{
        MethodHandle mh = MethodHandles.lookup().findStatic(InvokeDynamicTest.class,
                "bootstrapMethod",mt_bootstrapMethod());
        return mh;
    }
    private static MethodHandle indy_bootstrapMethod() throws Throwable{
        CallSite cs = (CallSite)mh_bootstrapMethod().invokeWithArguments(
                MethodHandles.lookup(),"testMethod");
        MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V",null);
        return  cs.dynamicInvoker();
    }

    public static void main(String [] args) throws Throwable{
        indy_bootstrapMethod().invokeExact("icyfenix");
    }
}

class  Test {
class GrandFather{
    void thinking(){
        System.out.println("i am grandfather");
    }}
        class Father extends GrandFather{
        void thinking(){
        System.out.println("i am father");
        }}
        class Son extends Father{
        void thinking(){
//请读者在这里填入适当的代码(不能修改其他地方的代码)
//实现调用祖父类的thinking()方法，打印"i am grandfather"
            try{
                MethodType mt=MethodType.methodType(void.class);
                MethodHandle mh = MethodHandles.lookup().findSpecial(
                        GrandFather.class,"thinking",mt,getClass());
                mh.invoke(this);
            }catch(Throwable e){
            }
        }}
}
//new Test().new Son()).thinking()



