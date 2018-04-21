package com.zoe.study.java.java7.chapter2.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {

	public static void invokeSetter(Object obj,String field,String value) throws NoSuchMethodException, 
		SecurityException,IllegalAccessException,IllegalArgumentException,InvocationTargetException {
		
		String methodName = "set"+field.substring(0, 1).toUpperCase()+field.substring(1);
		Method method = obj.getClass().getMethod(methodName, value.getClass());
		method.invoke(obj, value);
	}
	
}
