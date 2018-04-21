package com.zoe.study.java.jvm.chapter12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zoe on 2017/6/27.
 */
public class VolatileTest {

    private volatile int race = 0;

    private final static int THREAD_COUNT = 100;

//    ++并不是原子操作
//    public void  increa() {
//         race++;
//    }

    public synchronized  void  increa() {
        race++;
    }

    public static  void main(String [] args)  throws Exception{
        final VolatileTest vt = new VolatileTest();
        ExecutorService pools = Executors.newFixedThreadPool(THREAD_COUNT);
        Future<?> [] fu = new Future[THREAD_COUNT];

        for(int i=0;i<THREAD_COUNT;i++) {
            final int out = i;
            fu[i] = pools.submit(new Runnable(){
                @Override
                public void run() {
                    for (int j =0;j<1000;j++) {
                        vt.increa();
//                        System.out.println(out +"--------" + j +"---------" + vt.increa());
                    }
                }
            });
        }

//        /**
//         * 达不到等待线程都执行结束的效果
//         * */
//        boolean finished = true;
//        for(Future f : fu){
//            if(f.get() != null) {
//                finished = false;
//            }
//        }
//        if(finished) {
//            pools.shutdown();
//        }

        pools.shutdown();
         while(true) {
             if(pools.isTerminated()) {
                 break;
             }
         }
        System.out.println(vt.race);

    }
}
