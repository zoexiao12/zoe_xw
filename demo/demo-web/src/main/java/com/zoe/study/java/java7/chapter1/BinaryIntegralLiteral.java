package com.zoe.study.java.java7.chapter1;

import static java.lang.System.out;

public class BinaryIntegralLiteral {
	
	public void display(){
		out.println(0b001001);
		out.println(0B001110);
	}

	public static void main(String[] args) {
		BinaryIntegralLiteral bi = new BinaryIntegralLiteral();
		bi.display();
	}

}
