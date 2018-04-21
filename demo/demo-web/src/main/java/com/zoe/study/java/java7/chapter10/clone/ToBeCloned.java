package com.zoe.study.java.java7.chapter10.clone;

public class ToBeCloned implements Cloneable {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
