package com.zoe.study.java.java7.chapter10.init;

public class Dog extends Animal {
	
	@SuppressWarnings("unused")
	private String name = "<default>";
	
	public Dog(int legs) {
		super(legs);
		System.out.println("Dog 初始化！");
	}

}
