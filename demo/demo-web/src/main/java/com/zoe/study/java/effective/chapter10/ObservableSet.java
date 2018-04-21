package com.zoe.study.java.effective.chapter10;

import com.zoe.study.java.effective.chapter04.ForwardingSet;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by  on 2017/8/5.
 * 被观察的集合类
 */
public class ObservableSet<E> extends ForwardingSet<E> {
    public ObservableSet(Set s) {
        super(s);
    }

//    /**
//     * 传统的集合，结合同步实现观察者集合。
//     * 观察者预增加和取消预定
//     * */
//    private final List<SetObserver<E>> observers = new ArrayList<>();
//    public void addObserver(SetObserver<E> o){
//        synchronized (observers) {
//            observers.add(o);
//        }
//    }
//    public boolean removeObserver(SetObserver<E> o) {
//        synchronized (observers) {
//            return observers.remove(o);
//        }
//    }


//    /**
//      * 错误实现方式   同步块中调用外来方法
//     * 可防止并发的修改，但无法防止迭代线程本身毁掉到被观察的结合（ObservableSet中的公有方法），
//     * 也无法防止修改观察者列表 （List<SetObserver<E>> observers）
//     * */
//    private void notifyElementAdded(E e) {
//        synchronized(observers) {
//            for (SetObserver<E> o: observers) {
//                o.added(this,e);  //同步块中调用外来方法
//            }
//        }
//    }


//    /**
//     * 修正方式
//     * 将外来方法的调用移出同步的代码块。
//     * 通过给observers一个快照的方式解决，没有锁也可以安全地遍历列表了
//     * */
//    private void notifyElementAdded(E e) {
//        List<SetObserver<E>> snapshot = null;
//        synchronized(observers) {
//            snapshot = new ArrayList<>(observers);
//        }
//        for (SetObserver<E> o: snapshot) {
//            o.added(this,e);
//        }
//    }



    /**
     * 利用JDK1.5之后提供的并发集合实现观察者集合。
     * 不用加入任何同步，更加简洁高效
     * 观察者预定通知和取消预定
     * */
    private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>();
    public void addObserver(SetObserver<E> o){
        observers.add(o);
    }
    public boolean removeObserver(SetObserver<E> o) {
        return observers.remove(o);
    }
    private void notifyElementAdded(E e) {
        for (SetObserver<E> o: observers) {
            o.added(this,e);
        }
    }



    @Override
    public boolean add(E e) {
        boolean added = super.add(e);
        if(added) {
            notifyElementAdded(e);
        }
        return added;
    }
    @Override
    public boolean addAll(Collection <? extends E> c) {
        boolean result = false;
        for (E e: c) {
            result |= add(e);
        }
        return result;
    }





    public static  void main(String [] args) {
        //被观察者
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<Integer>());

//        /**
//         * 观察者一
//         * 加入一个观察者，增加预定通知。 简单，所以无误
//         * */
//        set.addObserver(new SetObserver<Integer>(){
//            @Override
//            public void added(ObservableSet<Integer> s, Integer e) {
//                System.out.println(e);
//            }
//        });


//        /**
//         * 观察者二： 发生异常
//         * notifyElementAdded 调用观察者的added方法时，观察者集合observers正处于遍历中
//         * 但观察者却可以调用到被观察的集合类ObservableSet的removeObserver 删除观察者。
//         * 企图在遍历列表的过程中，将列表元素从列表中删除，是非法。
//         * */
//        set.addObserver(new SetObserver<Integer>(){
//            @Override
//            public void added(ObservableSet<Integer> s, Integer e) {
//                System.out.println(e);
//                if(e == 23) {
//                    /**
//                     * 虽然此处removeObserver也会锁定observers（之前已经通过notifyElementAdded锁定了observers）
//                     * 但属于同一个线程，而Java程序设计语言中的锁是可重入的，所以不会出现死锁。
//                     * 因为之前调用线程已经获得了锁，所以此处当线程试图再次获得该锁是会成功，但在此处，后果却是灾难性的。
//                     * 此处锁虽然没有尽到它的职责，但可重入锁却简化了多线程的面向对象程序的构造。
//                     * 但可能会产生活性失败和安全性失败。
//                     * */
//                    s.removeObserver(this);
//                }
//            }
//        });


        /**
         * 观察者三： 发生死锁
         *  通过线程调用ObservableSet 的removeObserver方法时，需要锁定observers
         *  但observers其实已经被主线程锁定了，即notifyElementAdded调用时，已经锁定。
         * */
        set.addObserver(new SetObserver<Integer>(){
            @Override
            public void added(final ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if(e == 23) {
                    ExecutorService pools = Executors.newSingleThreadExecutor();
                    final  SetObserver<Integer> obs = this;
                    try {
                        pools.submit(new Runnable() {
                            @Override
                            public void run() {
                                s.removeObserver(obs);
                            }
                        }).get();
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }finally {
                        pools.shutdown();
                    }
                }
            }
        });


        for (int i = 0; i < 100; i++) {
            /**
             * 此处调用notifyElementAdded
             * notifyElementAdded方法中会调用外来方法即观察者的added方法
             * 观察者的added会调用被观察对象（set）的removeObserver
             * */
            set.add(i);
        }
    }

}
