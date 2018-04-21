package com.zoe.study.java.effective.chapter08;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by zoe on 2017/8/18.
 */
public class ReflectiveTest {

    /**
     * 此处用反射的缺点：
     *      1 产生3个运行时错误，不使用反射，则会在编译时提示无法通过。
     *      2 根据类名生成实例需要一段（20行）冗余的代码。用构造器则只需要1行。
     *      3 实例化后，很难区分对象的具体类型（到底是HashSet还是TreeSet）
     * @param className
     * @param args
     */
    public void refTest(String className,String ... args) {
        Class<?> c = null;
        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("类 " + className + " 没有找到！");
            return;
        }

        Set<String> s = null;
        try {
            s = (Set<String>)c.newInstance();
        } catch (InstantiationException e) {
            System.out.println("类 "  + className + " 不能实例化！");
            return;
        } catch (IllegalAccessException e) {
            System.out.println("类 "  + className + " 不能转换！");
            return;
        }
        s.addAll(Arrays.asList(args));
        System.out.println(className + ": " + s);
    }

    public static void main(String [] args) {
        ReflectiveTest rt = new ReflectiveTest();
        String className = "";
        String [] rargs = {"yyyyy","ddddd","xxxxx","aaaaa"};
        if(args.length>0) {
            className = args[0];
            args = new String[args.length -1];
            System.arraycopy(args, 1, rargs, 0,args.length-2);
        }else {
            className = "java.util.HashSet";
        }
        rt.refTest(className,rargs);

        className = "java.util.TreeSet";
        rt.refTest(className,rargs);
    }
}
