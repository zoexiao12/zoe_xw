package com.zoe.study.java.java7.chapter7.reference;

import java.util.ArrayList;
import java.util.List;

public class LeakingQueue<T> {
	
	private List<T> queue = new ArrayList<>();
	
	private int topIndex = 0;
	
	public void push(T t) {
		queue.add(t);
	} 

	public T pop() {
		/* 此处有内存泄露，在LeakingQueue的实例对象一直存活的情况，小于topIndex的T对象一直存活着，
		   但无法被获取。加上下面这句queue.add(topIndex, null);  消除内存泄露
		 * */
		 
		T result = queue.get(topIndex);
		topIndex++;
		return result;
	}
}
