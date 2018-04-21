package com.zoe.study.java.java7.chapter10.clone;

public class MulableObjectClone {

	public static void main(String[] args) throws CloneNotSupportedException {
		MulableObject mo = new MulableObject();
		mo.increase();
		System.out.println("mo.value = "+mo.getValue());
		
		MulableObject moclone = (MulableObject)mo.clone();
		moclone.increase();
		
		System.out.println("after clone  increase  mo.value = "+mo.getValue());
		System.out.println("after clone  increase  moclone.value = "+moclone.getValue());
		
	}

}
