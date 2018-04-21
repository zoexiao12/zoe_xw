package com.zoe.study.java.java7.chapter10.clone;

public class CloneableObject implements Cloneable {
	
	
	@Override
	public Object clone()   {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new Error(e);
		}
	}

	public static void main(String[] args) {
		CloneableObject co = new CloneableObject();
		co.clone();
	}

}
