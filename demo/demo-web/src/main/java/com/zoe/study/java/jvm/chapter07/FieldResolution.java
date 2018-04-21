package com.zoe.study.java.jvm.chapter07;

/**
 * Created by zoe on 2017/11/7.
 */
public class FieldResolution {
    interface  Interface_0 {
        int A = 0;
    }
    interface  Interface_1 extends Interface_0 {
        int A = 1;
    }
    interface  Interface_2 {
        int A = 2;
    }

    static class Parent implements Interface_1 {
        public static int A = 3;

        static {
            A_1 = 33;
        }
        public static int A_1 = 3;
    }
    static class Sub extends Parent implements Interface_2 {
        public static int A = 4;
        public static int B = A_1;
    }

    /**静态语句块中
     * 定义在块之前的变量--访问 赋值
     * 定义在块之后的变量--赋值
     * */
    static {
        i = 123; //给变量赋值可以正常通过
//        System.out.println(i); //访问则提示 Illegal forward reference (非法向前引用)
    }
    public static  int i;
    public static  void main(String [] args) {
        /** 将Sub中的A定义注释，此处将无法通过编译
         * 因为接口和父类中同时存在同名字段A
         * */
//        System.out.println(Sub.A);

        System.out.println(Sub.B);
    }

}
