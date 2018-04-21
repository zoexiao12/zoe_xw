package com.zoe.study.java.java7.chapter2.dynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleProxies;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.SwitchPoint;

public class MethodHandleChangeTest {
	
	public void dropArguments() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodType mt = MethodType.methodType(String.class, int.class,int.class);
		MethodHandle mho = lu.findVirtual(String.class, "substring", mt);
		String sub = (String) mho.invoke("Hello World",1,6);
		System.out.println(sub);
		
		
		MethodHandle mhn = MethodHandles.dropArguments(mho, 1, float.class,String.class);
		sub = (String)mhn.invoke("Hello World",0.5f,"Ingree",1,6);
		System.out.println(sub);
	}
	
	public void insertArguments() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodType mt = MethodType.methodType(String.class, String.class);
		MethodHandle mho = lu.findVirtual(String.class, "concat", mt);
		String str = (String) mho.invoke("Hello"," World");
		System.out.println(str);
		
		MethodHandle mhn = MethodHandles.insertArguments(mho, 1, " -- ");
		str = (String)mhn.invoke("hello");
		System.out.println(str);
	}
	
	public void filterArguments() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodType mt = MethodType.methodType(int.class, int.class,int.class);
		MethodHandle mhGetLength = lu.findVirtual(String.class, "length", 
				MethodType.methodType(int.class));
		
		MethodHandle mhTarget = lu.findStatic(Math.class, "max", mt);
		
		MethodHandle mhnew = MethodHandles.filterArguments(mhTarget, 0, mhGetLength,mhGetLength);
		int max = (int)mhnew.invoke("hello","New World!");
		System.out.println(max);
	}
	
	
	
	public static int targetMethod(int arg1,int arg2,int arg3) {
		System.out.println("arg1:"+arg1);
		System.out.println("arg2:"+arg2);
		System.out.println("arg3:"+arg3);
		return arg1;
	}
	public void flodArguments() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodType mtCombiner = MethodType.methodType(int.class, int.class,int.class);
		MethodHandle mhCombiner = lu.findStatic(Math.class, "max", mtCombiner);
		
		MethodType mtTarget = MethodType.methodType(int.class, int.class,int.class,int.class);
		MethodHandle mhTarget = lu.findStatic(MethodHandleChangeTest.class, "targetMethod", mtTarget);
		
		MethodHandle mhnew = MethodHandles.foldArguments(mhTarget, mhCombiner);
		int value  = (int)mhnew.invoke(9,43);
		System.out.println(value);
	}
	
	public void permuteArguments() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodType mtCompare = MethodType.methodType(int.class,int.class,int.class);
		MethodHandle mhCompare = lu.findStatic(Integer.class, "compare", mtCompare);
		int value = (int)mhCompare.invoke(4,3);
		System.out.println(value);
		
		MethodHandle mhNew = MethodHandles.permuteArguments(mhCompare, mtCompare, 1,0);
		value = (int)mhNew.invoke(4,3);
		System.out.println(value);
		
		MethodHandle mhDuplicateArgs = MethodHandles.permuteArguments(mhCompare, mtCompare, 1,1);
		value = (int)mhDuplicateArgs.invoke(4,3);
		System.out.println(value);
	}
	
	
	public int handleException(Exception e,String str) {
		System.out.println(e.getMessage());
		return 0;
	}
	public void catchExceptions() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodType mtTarget = MethodType.methodType(int.class, String.class);
		MethodHandle mhTarget = lu.findStatic(Integer.class, "parseInt", mtTarget);
		
		MethodType mtHandle = MethodType.methodType(int.class, Exception.class,String.class);
		MethodHandle mhHandle = lu.findVirtual(MethodHandleChangeTest.class, "handleException", mtHandle);
		mhHandle = mhHandle.bindTo(this);
		
		MethodHandle mhNew = MethodHandles.catchException(mhTarget, NumberFormatException.class, mhHandle);
		mhNew.invoke("hello");
	}
	
	
	public static boolean guardTest() {
		double random = Math.random();
		System.out.println(random);
		return random > 0.5;
	}
	public void guardWithTest() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodHandle mhTest = lu.findStatic(MethodHandleChangeTest.class, "guardTest", 
				MethodType.methodType(boolean.class));
		
		MethodType type = MethodType.methodType(int.class,int.class,int.class);
		MethodHandle mhTarget = lu.findStatic(Math.class, "max", type);
		MethodHandle mhFallback = lu.findStatic(Math.class, "min", type);
		
		MethodHandle mhNew = MethodHandles.guardWithTest(mhTest, mhTarget, mhFallback);
		int value = (int)mhNew.invoke(3,4);
		System.out.println(value);
	}
	
	public void filterReturnValue() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodHandle mhTarget = lu.findVirtual(String.class, "substring",
				MethodType.methodType(String.class, int.class,int.class));
		
		MethodHandle mhFilter = lu.findVirtual(String.class, "toUpperCase",
				MethodType.methodType(String.class));
		
		MethodHandle mhNew = MethodHandles.filterReturnValue(mhTarget, mhFilter);
		String sub = (String) mhNew.invoke("Hello Wrold!",1,8);
		System.out.println(sub);
	}
	
	public String testMethod(int arg1,int arg2) {
		return "ok";
	}
	public void invoker() throws Throwable {
		MethodType mtInvoker = MethodType.methodType(String.class,Object.class,int.class,int.class);
		MethodHandle mhInvoker = MethodHandles.invoker(mtInvoker);
		
		MethodType mt = MethodType.methodType(String.class, int.class,int.class);
		Lookup lu = MethodHandles.lookup();
		MethodHandle mhSub = lu.findVirtual(String.class, "substring", mt);
		MethodHandle mhtest =  lu.findVirtual(MethodHandleChangeTest.class, "testMethod", mt);
		
		
		String value = (String)mhInvoker.invoke(mhSub,"Hello World",3,5);
		System.out.println(value);
		value = (String)mhInvoker.invoke(mhtest,this,3,5);
		System.out.println(value);
	}
	
	public void invokerTransform() throws Throwable {
		MethodType mtInvoker = MethodType.methodType(String.class, String.class,int.class,int.class);
		MethodHandle mhInvoker = MethodHandles.exactInvoker(mtInvoker);
		
		Lookup lu = MethodHandles.lookup();
		MethodHandle mhUpcase = lu.findVirtual(String.class, "toUpperCase", 
				MethodType.methodType(String.class));
		
		MethodHandle mhNew = MethodHandles.filterReturnValue(mhInvoker, mhUpcase);
		
		
		MethodHandle mh1 = lu.findVirtual(String.class, "substring", 
				MethodType.methodType(String.class,int.class,int.class));
		String sub = (String)mhNew.invoke(mh1,"Hello world",1,6);
		System.out.println(sub);
	}
	
	
	public void doSomething(){
		System.out.println("work");
	}
	public int do2(int i) {
		i = i+8;
		return i;
		
	}
	public void useMethodHandProxy() throws NoSuchMethodException, IllegalAccessException {
		Lookup lu = MethodHandles.lookup();
		MethodHandle mh = lu.findVirtual(MethodHandleChangeTest.class, "doSomething",
				MethodType.methodType(void.class));
		mh = mh.bindTo(this);
		
//		Runnable run = MethodHandleProxies.asInterfaceInstance(Runnable.class, mh);
//		new Thread(run).start();
		
		mh = lu.findVirtual(MethodHandleChangeTest.class, "do2",
				MethodType.methodType(int.class,int.class));
		mh = mh.bindTo(this);
		DynamicInterface d = MethodHandleProxies.asInterfaceInstance(DynamicInterface.class,mh);
		int num = d.test(8);
		System.out.println(num);
	}
	
	
	public void callPrivateHandle() throws Throwable {
		MethodHandle mh = new AccessControl().accessContral();
		mh.invoke();
	}
	
	
	public void useSwitchPoint() throws Throwable {
		Lookup lu = MethodHandles.lookup();
		MethodType mt = MethodType.methodType(int.class,int.class,int.class);
		
		MethodHandle max = lu.findStatic(Math.class, "max", mt);
		MethodHandle min = lu.findStatic(Math.class, "min", mt);
		
		SwitchPoint sp = new SwitchPoint();
		MethodHandle mhNew = sp.guardWithTest(max, min);
		int value = (int)mhNew.invoke(3,4);
		System.out.println(value);
		
		SwitchPoint.invalidateAll(new SwitchPoint[]{sp});
		value = (int)mhNew.invoke(3,4);
		System.out.println("invalidateAll  "+value);
		
	}

	public static void main(String[] args) throws Throwable {
		MethodHandleChangeTest test = new MethodHandleChangeTest();
//		test.dropArguments();
//		test.insertArguments();
//		test.filterArguments();
//		test.flodArguments();
//		test.permuteArguments();
//		test.catchExceptions();
//		test.guardWithTest();
//		test.filterReturnValue();
//		test.invoker();
//		test.invokerTransform();
//		test.useMethodHandProxy();
//		test.callPrivateHandle();
		test.useSwitchPoint();
	}

}
