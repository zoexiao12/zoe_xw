package com.zoe.study.java.java7.chapter10.clone;

public class Counter  implements Cloneable {
	private int value = 0;

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public void increase() {
		value++;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
}
