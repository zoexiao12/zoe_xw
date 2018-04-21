package com.zoe.study.java.effective.chapter10;

/**
 * Created by  on 2017/8/8.
 */
public class SlowCountDownLatch {
    private  int count;
    public SlowCountDownLatch(int count) {
        if(count < 0) {
            throw  new IllegalArgumentException(count + "< 0");
        }
        this.count = count;
    }

    public void  await() {
        while (true) {
            synchronized (this) {
                if (count == 0) return;
            }
        }
    }

    public synchronized  void countDown() {
        if(count != 0) {
            count--;
        }
    }
    public static void main(String [] args) {

    }
}
