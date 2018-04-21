package com.zoe.study.java.effective.chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by  on 2017/8/5.
 */
public class GenerateNumber {

//    /**
//     * 错误实现方式：
//     * 增量操作符++不是原子的，这会产生安全性失败（safety failure）
//     */
//    private   volatile int nextNum = 0;
//    public int getNextNum() {
//        return nextNum++;
//    }


//    /**
//     * 正确方式一： synchronized 同步实现
//     */
//    private long nextNum = 0;
//    public synchronized long getNextNum() {
//        return nextNum++;
//    }


    /**
     * 正确方式二： 原子类实现
     * 比synchronized更简洁，执行更好。
     * 虚拟机层面采用基于CAS+自旋锁实现，不用转换到内核状态，所以效率更高
     */
    private AtomicLong nextNum = new AtomicLong();
    public  long getNextNum() {
        return nextNum.getAndIncrement();
    }


    public static void main(String [] args) throws Exception{

        final GenerateNumber gn = new GenerateNumber();
        ExecutorService pools = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            pools.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <100 ; j++) {
                        gn.getNextNum();
                    }
                }
            });
        }

        TimeUnit.SECONDS.sleep(1);
        pools.shutdown();

        while (true) {
            if(pools.isTerminated()) {
                System.out.println("nextNum = " + gn.nextNum);
                break;
            }
        }
    }
}
