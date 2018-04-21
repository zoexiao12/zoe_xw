package com.zoe.study.java.effective.chapter10;

import java.util.concurrent.TimeUnit;

/**
 * Created by zoe on 2017/8/5.
 */
public class StopThread {

//    /**
//     * 错误的方式： 导致活性失败
//     */
//    private  boolean stopFlag;
//    public void threadRun() throws  Exception{
//        Thread t = new Thread(new Runnable() {
//            int i = 0;
//            @Override
//            public void run() {
//                /*
//                * while (!stopFlag) 在不同的虚拟机上可能会优化成 if(!stopFlag) {while(true)}
//                * 以上的优化是可接受的，称提升（hoisting）
//                * 结果导致活性失败。
//                */
//                while (!stopFlag) {
//                    i++;
//                    System.out.println(i);
//                }
//                System.out.println("finish!");
//            }
//        });
//        t.start();
//        TimeUnit.SECONDS.sleep(2);
//        stopFlag = true;
//    }




//    /**
//     * 正确方式一： synchronized 同步实现
//     */
//    private boolean stopFlag;
//    /**
//     * 此处被同步的动作即使没有同步也是原子的，此处也不需要互斥访问，而仅仅是通信效果。
//     * 所以此处的synchronized同步是多余的，可用其他更简洁，高效的方式替代。
//     * */
//    public synchronized  void setStopFlag() {
//        stopFlag = true;
//    }
//    public synchronized  boolean getStopFlag(){
//        return stopFlag;
//    }
//
//    public void threadRun() throws  Exception{
//        Thread t = new Thread(new Runnable() {
//            int i = 0;
//            @Override
//            public void run() {
//                while (!getStopFlag()) {
//                    i++;
//                    System.out.println(i);
//                }
//                System.out.println("finish!");
//            }
//        });
//        t.start();
//        TimeUnit.SECONDS.sleep(2);
//        setStopFlag();
//    }



    /**
     * 正确方式二： volatile 实现
     *  任何一个线程读取该域的时候会看到最近刚刚被写入的值。但不能执行互斥访问
     *  此处也不需要互斥访问，所以用volatile比较合适
     */
    private volatile boolean stopFlag;
    public void threadRun() throws  Exception{
        Thread t = new Thread(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                while (!stopFlag) {
                    i++;
                    System.out.println(i);
                }
                System.out.println("finish!");
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(2);
        stopFlag = true;
    }



    public static void main(String [] args) throws  Exception{
        StopThread st = new StopThread();
        st.threadRun();
    }
}
