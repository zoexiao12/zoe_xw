package com.zoe.study.java.java7.chapter10.init;

public class StaticOrder {
	static int x = 20;
	static int y = 2 * x;
	static {
		x =30;
	}

	public static void main(String[] args) {
		System.out.println("x="+x);
		System.out.println("y="+y);
	}

}
