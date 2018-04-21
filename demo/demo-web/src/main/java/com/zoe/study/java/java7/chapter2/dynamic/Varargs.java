package com.zoe.study.java.java7.chapter2.dynamic;

public class Varargs {
	public void normalMethod(String arg1,int arg2,int [] arg3) {
		System.out.println("arg1:"+arg1);
		System.out.println("arg2:"+arg2);
		System.out.println("arg3.length:"+arg3.length);
	}
	
	public void toBeSpreaded(String arg1,int arg2,int arg3,int arg4) {
		System.out.println("arg1:"+arg1);
		System.out.println("arg2:"+arg2);
		System.out.println("arg3:"+arg3);
		System.out.println("arg4:"+arg4);
	}
	
	public void varargsMethod(String arg1,int ...args){
		System.out.println("arg1:"+arg1);
		System.out.print("arg2:");
		for(int x : args){
			System.out.print(x+",");
		}
	}

}
