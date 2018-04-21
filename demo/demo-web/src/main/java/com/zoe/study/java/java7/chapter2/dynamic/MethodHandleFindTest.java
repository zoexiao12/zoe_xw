package com.zoe.study.java.java7.chapter2.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class MethodHandleFindTest {
	private String name;
	private static int value;
	
	
	private void privateMethod() {
		System.out.println("privateMethod invoke");
	}
	
	public void lookupMethod() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		//构造方法
		lu.findConstructor(String.class, MethodType.methodType(void.class, byte [].class));
		
		//  一般实例方法 String substring(beginIndex, endIndex)
		lu.findVirtual(String.class, "substring", 
				MethodType.methodType(String.class, int.class,int.class));
		
		//静态方法 String.format(format, args)
		lu.findStatic(String.class, "format", 
				MethodType.methodType(String.class, String.class,Object [].class));
	}
	
	public void lookupSpecial() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		
		//此处会出现错误，当前类无法访问的私有方法，是无法调用的，没有java反射功能强大
//		MethodHandle mh = lu.findSpecial(Varargs.class, "privateMethod", 
//				MethodType.methodType(void.class), Varargs.class);
//		mh.invoke(new Varargs());
		
		MethodHandle mh = lu.findSpecial(MethodHandleFindTest.class, "privateMethod", 
				MethodType.methodType(void.class), MethodHandleFindTest.class);
		mh.invoke(this);
	}
	
	public void lookupFieldAccessor() throws NoSuchFieldException, IllegalAccessException {
		Lookup lu = MethodHandles.lookup();
//		lu.findGetter(Sample.class, "name", String.class);
//		lu.findSetter(Sample.class, "name", String.class);
//		lu.findGetter(Sample.class, "value", int.class);
//		lu.findSetter(Sample.class, "value", int.class);
		
		lu.findGetter(MethodHandleFindTest.class, "name", String.class);
		lu.findSetter(MethodHandleFindTest.class, "name", String.class);
		lu.findStaticGetter(MethodHandleFindTest.class, "value", int.class);
		lu.findStaticSetter(MethodHandleFindTest.class, "value", int.class);
	}
	
	public void unreflect() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		Constructor<String> sc = String.class.getConstructor(String.class);
		MethodHandle scmh = lu.unreflectConstructor(sc);
		System.out.println(scmh.invoke("name"));
		
		Method sm = String.class.getMethod("substring",int.class,int.class);
		MethodHandle smmh = lu.unreflect(sm);
		String sub = (String)smmh.invoke("Hello Wowld",1,5);
		System.out.println(sub);
		
		Method pm = Varargs.class.getDeclaredMethod("privateMethod", String.class);
		pm.setAccessible(true);
		pm.invoke(new Varargs() , "Alex"); //正确
		//错误 只能在可以访问的情况下
//		MethodHandle pmh = lu.unreflectSpecial(pm, Varargs.class);
//		pmh.invoke(new Varargs(),"Alex");
		
		Field field = Sample.class.getDeclaredField("name");
		Sample  sample = new Sample();
		field.setAccessible(true);
//		field.set(sample, "tianya");
//		System.out.println(field.get(sample));
		MethodHandle fsmh = lu.unreflectSetter(field);
		MethodHandle fgmh = lu.unreflectGetter(field);
		fsmh.invoke(sample,"qq");
		String name  = (String)fgmh.invoke(sample);
		System.out.println(name);
	}
	
	public void arrayHandles() throws Throwable {
		int [] array = new int [] {1,2,3,4,5};
		MethodHandle setter = MethodHandles.arrayElementSetter(int [].class);
		setter.invoke(array,3,6);
		MethodHandle getter = MethodHandles.arrayElementGetter(int [].class);
		int value = (int) getter.invoke(array,3);
		System.out.println(value);
	}
	
	public void identy() throws Throwable{
		MethodHandle mh = MethodHandles.identity(String.class);
		String str  = (String)mh.invoke("Hello");
		System.out.println(str);
		
	}
	
	public void constant() throws Throwable {
		MethodHandle mh = MethodHandles.constant(int.class, 30);
		int num = (int) mh.invoke();
		System.out.println(num);
	}
	
	

	public static void main(String[] args) throws Throwable {
		MethodHandleFindTest test = new MethodHandleFindTest();
		test.lookupSpecial();
//		test.lookupFieldAccessor();
//		test.unreflect();
//		test.arrayHandles();
//		test.identy();
//		test.constant();
	}

}
