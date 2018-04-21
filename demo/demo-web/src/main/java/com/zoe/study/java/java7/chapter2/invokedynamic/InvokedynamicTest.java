package com.zoe.study.java.java7.chapter2.invokedynamic;

import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;

public class InvokedynamicTest {
	
	public void useConstantCallSite() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodHandle mh = lu.findVirtual(String.class, "substring", 
				MethodType.methodType(String.class, int.class,int.class));
		ConstantCallSite callSite = new ConstantCallSite(mh);
		MethodHandle mhDy = callSite.dynamicInvoker();
		String sub = (String)mhDy.invoke("Hello World",1,5);
		System.out.println(sub);
	}

	public void useMutableCallSite() throws Throwable {
		MethodType type = MethodType.methodType(int.class, int.class,int.class);
		MutableCallSite callSite = new MutableCallSite(type);
		MethodHandle dyinvoker = callSite.dynamicInvoker();
		
		Lookup lu = MethodHandles.lookup();
		MethodHandle mhMax = lu.findStatic(Math.class, "max", type);
		MethodHandle mhMin = lu.findStatic(Math.class, "min", type);
		
		callSite.setTarget(mhMax);
		int value = (int)dyinvoker.invoke(3,5);
		System.out.println(value);
		
		
		callSite.setTarget(mhMin);
		value = (int)dyinvoker.invoke(3,5);
		System.out.println(value);
	}
	
	public static void main(String[] args) throws Throwable {
		InvokedynamicTest test = new InvokedynamicTest();
//		test.useConstantCallSite();
		test.useMutableCallSite();
	}

}
