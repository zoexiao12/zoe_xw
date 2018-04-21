package com.zoe.study.java.java7.chapter2.dynamic;

import java.lang.invoke.MethodHandle;

public class ArrayFunctionProgramUtil {
	
	public static void forEach(Object [] array,MethodHandle mh) throws Throwable{
		for(int i=0, len = array.length ;i<len ;i++){
			mh.invoke(array[i],i);
		}
	}
	
	public static Object [] map(Object [] array,MethodHandle mh) throws Throwable {
		Object [] result = new Object[array.length];
		for(int i=0, len = array.length ;i<len ;i++){
			result[i] = mh.invoke(array[i],mh);
		}
		return result;
	}
	
	public static Object reduce(Object [] array, Object initValue,MethodHandle mh) throws Throwable  {
		Object result = initValue;
		for(int i=0, len = array.length ;i<len ;i++){
			result = mh.invoke(result,array[i]);
		}
		return result;
	}
}
