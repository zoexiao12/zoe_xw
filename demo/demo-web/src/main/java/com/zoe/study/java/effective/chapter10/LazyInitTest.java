package com.zoe.study.java.effective.chapter10;

import java.sql.Connection;

/**
 * Created by  on 2017/8/7.
 */
public class LazyInitTest {

//    /**
//     * 正常初始化实例域的典型声明。
//     * */
//    private final Connection conn = connInit();


//    /**
//     * 同步方法访问方法的延迟初始化  完全适用静态域的延迟初始化。
//     * 使用同步方法实现；最简单，最清楚。
//     * */
//    private Connection conn;
//    public synchronized Connection getConn() {
//        if(conn == null) {
//            conn = connInit();
//        }
//        return conn;
//    }

//    /**
//     * lazy initialization holder class idiom 静态域的延迟初始化
//     * 当getConnection方法第一次调用时，它第一次读取ConnectionHolder.conn，
//     * 导致ConnectionHolder类得到初始化。
//     * 优点：方法没有被同步，且只执行一个域访问，延迟访问实际上并未增加任何访问成本。
//     * */
//    private static class ConnectionHolder {
//        static final Connection conn = connInit();;
//    }
//    static public Connection getConnection() {
//        return ConnectionHolder.conn;
//    }


//    /**
//     * 双重检查模式（double-check idiom）
//     * 避免了在域被初始化之后访问这个域时的锁定开销。
//     * 模式背后的思想：两次检查域的值。一次检查时没有锁定；
//     * 如果没有被初始化，进入第二次检查，这次检查有锁定。
//     * 第二次如果还没有初始化，则调用延迟初始化方法。如果已经被初始化则不会有锁定。
//     * 声明为volatile的作用：
//     *
//     * */
//    private volatile  Connection conn;
//    public Connection getConnection() {
//        /**
//         * 下面这句的作用：确保延迟初始化的域只在已经被初始化的情况下读取一次。以提升性能。
//         * volatile 关键词每次读取的时候，根据Java内存模型，会有工作内存和主内存的同步。
//         * */
//        Connection result = conn;
//        if(result == null) {  //第一次检查，没有锁定
//            synchronized(this) {
//                result = conn;
//                if(result == null) {   //第二次检查，有锁定
//                    conn = result = connInit();
//                }
//            }
//        }
//        return result;
//    }

    /**
     * 单重检查模式（single-check idiom）
     * 适用于一个可以接受重复初始化的实例域
     *双重检查惯用法的一个变形，省去了第二次检查
     * */
    private volatile  Connection conn;
    public Connection getConnection() {
        Connection result = conn;
        if (result == null) {
            conn = result = connInit();
        }
        return result;
    }
    private static Connection connInit(){
        //init connection
        return null;
    }



}
