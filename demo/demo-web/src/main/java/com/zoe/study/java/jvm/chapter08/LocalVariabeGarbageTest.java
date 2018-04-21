package com.zoe.study.java.jvm.chapter08;

/**
 * Created by zoe on 2017/11/7.
 * -verbose:gc 查看垃圾收集的过程
 */
public class LocalVariabeGarbageTest {
    public static void main(String [] args) {
        /** System.gc()的时候，变量placeholder还处于作用域内，
         *  虚拟机不会回收作用域内的内存。
         *  [GC (System.gc())  84751K->67579K(247296K), 0.0031040 secs]
         *  [Full GC (System.gc())  67579K->67465K(247296K), 0.0100748 secs]
         * */
//        byte [] placeholder = new byte[64 *1024 * 1024];

        /** 变量placeholder的作用域虽然被限制在花括号之内
         * 但局部变量表中的slot还存有关于placeholder数组对象的引用。
         * 因为在此之后，没有任何局部变量表的读写操作，
         * 即placeholder原本占有的slot没被其他变量所复用，
         * GC Roots一部分的局部变量仍然保持对它的关联
         * [GC (System.gc())  84751K->67547K(247296K), 0.0025346 secs]
         * [Full GC (System.gc())  67547K->67467K(247296K), 0.0101725 secs]
         * */
        {
            byte [] placeholder = new byte[64 *1024 * 1024];
        }


        /** 把placeholder变量对应的局部变量表的slot清空
         * [GC (System.gc())  84751K->67611K(247296K), 0.0044858 secs]
         * [Full GC (System.gc())  67611K->1929K(247296K), 0.0110059 secs]
         * */
        int a = 0;
        System.gc();

        /** 局部变量没有一个准备阶段，所以不会赋予系统初始值，
         * 以下的写法会导致编译不通过，即使手动生成字节码，也会在验证环节失败。
         * */
//        int a;
    }
}
