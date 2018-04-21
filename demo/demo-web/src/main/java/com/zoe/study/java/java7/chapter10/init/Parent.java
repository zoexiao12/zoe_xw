package com.zoe.study.java.java7.chapter10.init;

public class Parent {
	
	public Parent() {
		/**
		 * getCount()可以被子类覆写，Parent初始化时，子类还没有初始化，getCount()的值可能为默认初始值。没有任何意义，
		 * 可能还会导致错误。
		 */
		int average = 30 / getCount();
		System.out.println("average = "+ average);
	}
	
	public int getCount(){
		return 4;
	}

}
