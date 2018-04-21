package com.zoe.study.java.java7.chapter9;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassIdentity {
	
	public void test() throws Exception{
		Path path = Paths.get("/Users/zoe/Desktop/eclipse_workspace/bigdata/demo/target/classes");
		FileSystemClassLoader fc1  = new FileSystemClassLoader(path);
		FileSystemClassLoader fc2  = new FileSystemClassLoader(path);
		
		String className = "com.demo.java.java7.chapter9.Sample";
		Class<?> clazz1 = fc1.loadClass(className);
		Class<?> clazz2 = fc2.loadClass(className);
		
		Object obj1 = clazz1.newInstance();
		Object obj2 = clazz2.newInstance();
		
		Method method = clazz1.getMethod("setSample", Object.class);
		method.invoke(obj1, obj2);
		
	}

	public static void main(String[] args) throws Exception{
		ClassIdentity ci = new ClassIdentity();
		ci.test();
	}

}
