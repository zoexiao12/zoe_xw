package com.zoe.study.java.java7.chapter2.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

public class MethodHandleInvokeTest {
	
	public void invokeExact() throws Throwable {
		//String substring(int beginIndex, int endIndex)
		Lookup lu = MethodHandles.lookup();
		MethodType mt = MethodType.methodType(String.class, int.class,int.class);
		MethodHandle  mh = lu.findVirtual(String.class, "substring", mt);
		String str = (String)mh.invokeExact("Hello World!",1,3);
		//String str = (String)mh.invoke("Hello World!",1,3); //等于上一个调用
		System.out.println(str);
		
		// Integer Math.max(Integer, Integer)
		mt =  MethodType.methodType(Integer.class, Integer.class,Integer.class);
		mh = lu.findVirtual(Math.class, "max", mt);
		Integer max = (Integer)mh.invokeExact(100,400);
		System.out.println(max);
	}
	
	
	
	public void asVarargsCollector() throws Throwable{
		Lookup lu = MethodHandles.lookup();
		MethodHandle mh = lu.findVirtual(Varargs.class, "normalMethod", 
				MethodType.methodType(void.class,String.class,int.class,int [].class));
		
		mh = mh.asVarargsCollector(int [].class);
//		mh.invoke(new Varargs(),"Hello",1,2,4,5,6);
		
		mh.invoke(new Varargs(),"Hello",1,new int [] {2,4,5,6});
	}
	
	public void asCollector() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodHandle mh = lu.findVirtual(Varargs.class, "normalMethod", 
				MethodType.methodType(void.class,String.class,int.class,int [].class));
		mh = mh.asCollector(int [].class, 2);
		mh.invoke(new Varargs(),"Hello",2,3,4);
	}
	
	public void asSpreader() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodHandle mh = lu.findVirtual(Varargs.class, "toBeSpreaded", 
				MethodType.methodType(void.class,String.class,int.class,int.class,int.class));
		mh = mh.asSpreader(int [].class, 3);
		mh.invoke(new Varargs(),"hello",new int[]{2,3,4});
	}
	
	public void asFixedArity() throws Throwable {
		Lookup up = MethodHandles.lookup();
		MethodHandle mh = up.findVirtual(Varargs.class, "varargsMethod", 
				MethodType.methodType(void.class, String.class,int [].class));
		mh = mh.asFixedArity();
		mh.invoke(new Varargs(),"Hello",new int []{6,7,8,9,21,33,45});
	}
	
	public void bindTo()  throws Throwable {
		Lookup up = MethodHandles.lookup();
		MethodHandle mh = up.findVirtual(String.class, "length", 
				MethodType.methodType(int.class));
		int len = (int)mh.invoke("Hello");
		System.out.println("Hello length:"+len);
		
		mh = mh.bindTo("Hello world!");
		len = (int)mh.invoke();
		System.out.println("Hello world! length:"+len);
	}
	
	public void multipleBindTo() throws Throwable {
		Lookup up = MethodHandles.lookup();
		MethodHandle mh = up.findVirtual(String.class, "indexOf", 
				MethodType.methodType(int.class,String.class,int.class));
		mh = mh.bindTo("Hello").bindTo("l");
		int index = (int) mh.invoke(2);
		System.out.println("Hello world! indexOf:"+index);
	}
	
	public void baseTypeBindTo() throws Throwable {
		Lookup up = MethodHandles.lookup();
		MethodType mt = MethodType.methodType(String.class, int.class,int.class);
		MethodHandle mh = up.findVirtual(String.class, "substring", mt);
		mh = mh.asType(mh.type().wrap());
		mh = mh.bindTo("Hello World!").bindTo(new Integer(1)).bindTo(new Integer(5));
		String sub = (String)mh.invoke();
		System.out.println("Hello world! substring(1,5):"+sub);
		
	}

	public static void main(String[] args)  throws Throwable{
		MethodHandleInvokeTest test = new MethodHandleInvokeTest();
//		test.invokeExact();
//		test.asVarargsCollector();
//		test.asCollector();
//		test.asSpreader();
//		test.asFixedArity();
//		test.bindTo();
//		test.multipleBindTo();
		test.baseTypeBindTo();
	}

}
