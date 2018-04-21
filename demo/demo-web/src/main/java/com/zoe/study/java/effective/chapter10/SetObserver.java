package com.zoe.study.java.effective.chapter10;

/**
 * 观察者接口
 * Created by  on 2017/8/5.
 */
public interface SetObserver<E> {
    void added(ObservableSet<E> set, E e);
}
