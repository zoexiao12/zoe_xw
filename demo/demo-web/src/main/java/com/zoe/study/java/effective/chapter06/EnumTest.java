package com.zoe.study.java.effective.chapter06;

/**
 * Created by  on 2017/8/23.
 */
public class EnumTest {

    /**
     *  int 枚举模式(int enum pattern）
     *  不足：
     *      1  类型安全性，使用方便性非常不足。
     *        (将apple传到orange的方法中，编译器完全无法感知。还能用==操作符进行比较）
     *         int i = (APPLE_FUJI - ORANGE_TEMPLE) /APPLE_PIPIN; //编译不会给出任何警告提示。
     *      2  没有命名空间。(只能以APPLE_，ORANGE_这种方式区分，）
     *      3  程序非常脆弱，int枚举模式编译时，被编译到使用它们的客户端中。
     *         int枚举关联值发生变化，客户端必须重新编译。不编译，程序可运行，但行为却不确定。
     *      4 没有便利的方法将int枚举模式翻译成可打印的字符串。(例子中打印出来就是一个数字，毫无意义）
     *      5 String枚举模式虽提供了可打印的字符串，但会导致性能问题，因为依赖字符串的比较操作。
     *        可能会导致初级用户把字符串常量硬编码到客户端代码中，而非使用适当的域名。
     *        硬编码字符串常量中如果包含书写错误，编译时是不可能被检测到的，但运行时会报错。
     */
    public static final  int APPLE_FUJI = 0;
    public static final  int APPLE_PIPIN = 1;
    public static final  int APPLE_GRANNY_SMITH = 2;

    public static final  int ORANGE_NAVEL = 0;
    public static final  int ORANGE_TEMPLE = 1;
    public static final  int ORANGE_BLOOD = 2;


    /**
     * 采用枚举的正确方式。
     */
    public enum Apple {FUJI,PIPIN,GRANNY_SMITH}
    public enum Orage {NAVEL,TEMPLE,BLOOD}

    //5965000000000000000000000
    public static void main(String [] args) {
//       测试Plant枚举
//        double earthWeight = 5.965e+24;
//        double mass = earthWeight/ Planet.EARTH.surfaceGravity();
//        for (Planet p : Planet.values()) {
//            System.out.printf("Weight on  %s is  %f%n ",p,p.surfaceWeight(mass));
//        }

//       测试Operation枚举
        for (Operation o : Operation.values()) {
            System.out.printf("97 %s 32 =   %f%n ",o.toString(),o.apply(97,32));
        }



    }
}
