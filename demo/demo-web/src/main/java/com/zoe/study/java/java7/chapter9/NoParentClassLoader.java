package com.zoe.study.java.java7.chapter9;

public class NoParentClassLoader extends ClassLoader {
	
	public NoParentClassLoader() {
		super(null);
	}
	
	public void testLoad() throws Exception{
		Class<?> clazz = loadClass("com.demo.java.java7.nine.NoParentClassLoader");
		System.out.println(clazz.toString());
	}
	public static void main(String[] args) throws Exception {
		NoParentClassLoader nc = new NoParentClassLoader();
		//默认双亲类加载器优先策略，双亲类加载器为空，所有抛出错误。
		nc.testLoad();
	}

}
