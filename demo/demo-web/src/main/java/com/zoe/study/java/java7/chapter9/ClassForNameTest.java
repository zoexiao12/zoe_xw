package com.zoe.study.java.java7.chapter9;

public class ClassForNameTest {
	static {
		System.out.println("初始化");
	}
	
	@SuppressWarnings("unused")
	public void classForNameVsLoader() throws Exception {
		String className = "com.demo.java.java7.chapter9.ClassForNameTest";
		Class<?> clazz1 = Class.forName(className);
		ClassLoader loader = this.getClass().getClassLoader();
		Class<?> clazz2 = loader.loadClass(className);
	}
	
	public static void main(String[] args) throws Exception{
		ClassForNameTest ct = new ClassForNameTest();
		ct.classForNameVsLoader();
	}
}
