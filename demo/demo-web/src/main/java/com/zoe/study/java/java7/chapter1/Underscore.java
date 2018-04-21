package com.zoe.study.java.java7.chapter1;

import static java.lang.System.out;

public class Underscore {
	public void display() {
		out.println(1_500_000);
		double valuel = 6_6.3_4;
		int value2 = 89_3_1;
		out.println(valuel);
		out.println(value2);
	}

	public static void main(String[] args) {
		Underscore us = new Underscore();
		us.display();
	}

}
