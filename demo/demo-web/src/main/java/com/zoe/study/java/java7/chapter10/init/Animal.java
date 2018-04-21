package com.zoe.study.java.java7.chapter10.init;

public class Animal {
	
	@SuppressWarnings("unused")
	private int legs;
	
	public Animal(int legs) {
		this.legs = legs;
		System.out.println("Animal 初始化！");
	}
	
}
