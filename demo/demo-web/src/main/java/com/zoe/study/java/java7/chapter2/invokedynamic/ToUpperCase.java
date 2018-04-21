package com.zoe.study.java.java7.chapter2.invokedynamic;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class ToUpperCase {
	public static CallSite bootstrap(Lookup lookpu,String name,MethodType type,String value) 
			throws NoSuchMethodException, IllegalAccessException{
		
		MethodHandle mh = MethodHandles.lookup().findVirtual(String.class, "toUpperCase", 
				MethodType.methodType(String.class)).bindTo(value);
		return new ConstantCallSite(mh);
	}

}
