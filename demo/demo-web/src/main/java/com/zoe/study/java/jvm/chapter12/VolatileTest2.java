package com.zoe.study.java.jvm.chapter12;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zoe on 2017/6/27.
 */
public class VolatileTest2 {

    private Map<String,String> configMap = new HashMap<>();

    //volatile变量的适合场景 阻止指令重排
//    private volatile boolean hasInited = false ;
    private  boolean hasInited = false ;

    //只是赋值操作，是原子的 ，所以并不需要synchronized
    public void setHasInited() {
        hasInited = true;
    }
    public boolean getHasInited() {
        return hasInited;
    }


    public void readFile() {
        //进行初始化，可读取文件，从数据库获取等
    }

    public void doInit(){
        readFile();
        //为了配合测试
        try {
            Thread.sleep(1000);
//            setHasInited();
            hasInited = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " Config has inited!");
    }

    public void dowork(){
        while (true) {
            if (hasInited) {
                System.out.println(Thread.currentThread().getName() + "----- used ");
                //do something
                break;
            } else {
                try {

                    System.out.println(Thread.currentThread().getName() + "----- no inited! ");
                    Thread.sleep(1000);  //减少频繁的切换线程，不适合用户响应的前台应用
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String [] args) {
        final VolatileTest2 vt = new VolatileTest2();
        //启动单线程的线程池初始化资源配置
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    vt.doInit();
                }
        });

        //启动包含10个线程的缓存线程池来获取数据
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++) {
            cacheThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    vt.dowork();
                }
            });
        }
    }



//    public static void main(String [] args) {
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            try {
//                Thread.sleep(index * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            cachedThreadPool.execute(new Runnable() {
//                public void run() {
//                    System.out.println(index);
//                }
//            });
//        }
//    }
}
