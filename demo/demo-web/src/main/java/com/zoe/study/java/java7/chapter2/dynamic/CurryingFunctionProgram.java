package com.zoe.study.java.java7.chapter2.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class CurryingFunctionProgram {
	
	public static MethodHandle curring(MethodHandle mh,int value){
		return MethodHandles.insertArguments(mh, 0, value);
	}
	

	public static int add(int a,int b){
		return a+b;
	}
	
	public void add5(int a) throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodHandle mhAdd = lu.findStatic(CurryingFunctionProgram.class, "add", 
				MethodType.methodType(int.class, int.class,int.class));
		MethodHandle mh = curring(mhAdd,5);
		int value = (int)mh.invoke(a);
		System.out.println(value);
	}
	
	public static void main(String [] args) throws Throwable {
		CurryingFunctionProgram cfp = new CurryingFunctionProgram();
		cfp.add5(6);
	}

}
