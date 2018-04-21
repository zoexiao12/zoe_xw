package com.zoe.study.java.effective.chapter10;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * Created by  on 2017/8/7.
 */
public class CountDownLatchTest {

    public long time(Executor executor, int concurrency, final Runnable action)
        throws Exception{
        final CountDownLatch ready =  new CountDownLatch(concurrency);
        final CountDownLatch start =  new CountDownLatch(1);
        final CountDownLatch done =  new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    ready.countDown();
                    try {
                        start.await();
                        action.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        done.countDown();
                    }
                }
            });
        }
        ready.await();
        long startNanos = System.nanoTime();
        start.countDown();
        done.await();
        return System.nanoTime() - startNanos;
    }

    public  static void  main(String [] args) {

    }
}
