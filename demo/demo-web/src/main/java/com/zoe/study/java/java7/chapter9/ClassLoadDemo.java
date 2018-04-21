package com.zoe.study.java.java7.chapter9;

public class ClassLoadDemo {
	
	public void loadClass() throws Exception{
		ClassLoader cl = getClass().getClassLoader();
		Class<? > clazz = cl.loadClass("java.lang.String");
		Object obj = clazz.newInstance();
		System.out.println(obj.getClass());
	}
	
	public void displayParents() {
		ClassLoader current = getClass().getClassLoader();
		while(current.getParent() != null) {
			System.out.println(current.toString());
			current = current.getParent();
		}
		System.out.println(current.toString());
	}

	public static void main(String[] args) throws Exception {
		ClassLoadDemo cd = new ClassLoadDemo();
//		cd.loadClass();
		cd.displayParents();
	}

}
