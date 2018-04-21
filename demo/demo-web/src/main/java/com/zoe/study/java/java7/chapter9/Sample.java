package com.zoe.study.java.java7.chapter9;

public class Sample {
	
	@SuppressWarnings("unused")
	private Sample obj ;
	
	public void setSample(Object obj){
		this.obj = (Sample)obj;
		System.out.print(obj);
	}
}
