package com.zoe.study.java.java7.chapter10.clone;

public class SimpleClone {

	public static void main(String[] args) throws CloneNotSupportedException {
		ToBeCloned tb = new ToBeCloned();
		tb.setValue(100);
		ToBeCloned newtb = (ToBeCloned)tb.clone();
		System.out.println("tb == newtb:"+(tb==newtb)+ "--value:"+newtb.getValue());
	}

}
