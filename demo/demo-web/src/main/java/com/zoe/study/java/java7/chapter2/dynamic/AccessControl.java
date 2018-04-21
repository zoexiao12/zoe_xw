package com.zoe.study.java.java7.chapter2.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class AccessControl {
	
	@SuppressWarnings("unused")
	private void privateMethod(){
		System.out.println("private method");
	}
	
	public MethodHandle accessContral() throws NoSuchMethodException, IllegalAccessException {
		Lookup lu = MethodHandles.lookup();
		MethodHandle mh = lu.findSpecial(AccessControl.class, "privateMethod",
				MethodType.methodType(void.class), AccessControl.class);
		mh = mh.bindTo(this);
		return mh;
	}

}
