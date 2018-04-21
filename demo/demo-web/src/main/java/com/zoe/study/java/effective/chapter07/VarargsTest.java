package com.zoe.study.java.effective.chapter07;

import java.util.Arrays;

/**
 * Created by  on 2017/8/15.
 */
public class VarargsTest {

    public int sum(int ... args) {
        int sum = 0;
        for (int arg :args) {
           sum += arg;
        }
        return sum;
    }

//    /**
//     * 此处定义不太好，此处需要1个或多个某种类型的参数的方法。
//     * 客户端调用时，没有传递参数进来，会在运行时失败，而不是在编译时失败。
//     * @param args
//     * @return
//     */
//    public int min(int ... args) {
//        if(args.length ==0) {
//            throw new IllegalArgumentException("Too few arguments");
//        }
//        int min = args[0];
//        for (int i = 1; i <args.length ; i++) {
//            if(args[i]<min) {
//                min = args[i];
//            }
//        }
//        return min;
//    }


    /**
     * 声明一个指定类型的正常参数，解决上面示例中的各种问题。
     * @param firstArg
     * @param args
     * @return
     */
    public int min(int firstArg,int ... args) {
        int min = firstArg;
        for (int arg :args) {
            if(arg < min) {
                min = arg;
            }
        }
        return min;
    }

    public static void main(String [] args) {
//        List<String> nums = Arrays.asList("to","too","two");


        /**
         * 将int类型数组digits，包装成List<int[]>的实例
         * 打印的时候调用int[]的toString()，没有得到预期的效果。
         * 想要的效果可通过Arrays.toString();
         * */
        int [] digits = {3,1,4,1,5,9,2,6,5,4};
//        System.out.println(Arrays.asList(digits));
        System.out.println(Arrays.toString(digits));



    }


}
