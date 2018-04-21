package com.zoe.study.java.java7.chapter10.clone;

public class MulableObject implements Cloneable {

	private Counter counter = new Counter();
	
	public int getValue() {
		return counter.getValue();
	}
	public void setValue(int value) {
		counter.setValue(value);
	}
	
	public void increase() {
		counter.increase();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		//浅复制无法解决问题
//		return super.clone();
		
		//深复制
		MulableObject mo_clone = (MulableObject)super.clone();
		mo_clone.counter =(Counter)mo_clone.counter.clone();
		return mo_clone;
	}
	
}
