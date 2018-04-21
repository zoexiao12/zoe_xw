package com.zoe.study.java.jvm.chapter12;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zoe on 2017/6/27.
 */
public class Singletom {

    private static Singletom singletom;

    private Singletom(){}


    public static Singletom getInstance() {
        //多线程模式下无法保持单例  但本机测试多线程模式下还是单例
//        if(singletom == null) {
//            singletom = new Singletom();
//        }

        if(singletom == null) {
            synchronized(Singletom.class) {
                if(singletom == null){
                    singletom = new Singletom();
                }
            }
        }
        return singletom;
    }

    public  static void  main(String [] args) throws Exception{
        final Set<String> set = new HashSet<>();
        ExecutorService cachePool = Executors.newCachedThreadPool();

        Future<?> [] fu = new Future[10000];
        for (int i=0;i<10000;i++) {
            fu[i] =  cachePool.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j =0;j<10;j++) {
                        set.add(Singletom.getInstance().toString());
                    }
                }
            });
        }

        boolean finished = true;
        for(Future f : fu){
            if(f.get() != null) {
                finished = false;
            }
        }

        if(finished) {
            cachePool.shutdown();
        }

        for(String s :set) {
            System.out.println(s);
        }

    }
}
