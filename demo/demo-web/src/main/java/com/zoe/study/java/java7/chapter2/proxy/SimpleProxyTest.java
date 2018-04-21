package com.zoe.study.java.java7.chapter2.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SimpleProxyTest {
	
	public void useProxy() {
		String str = "hello world";
		LoggingInvocationHandler loghandler = new LoggingInvocationHandler(str);
		ClassLoader cl = SimpleProxyTest.class.getClassLoader();
		
		@SuppressWarnings("unchecked")
		Comparable<String> cp = (Comparable<String>)Proxy.newProxyInstance(cl, new Class [] {Comparable.class}, loghandler);

		
		int returnValue = cp.compareTo("good");
		System.out.println(returnValue);
	}
	
	public void useGenericProxy() throws Exception {
		String str = "hello world";
		
		@SuppressWarnings("unchecked")
		Comparable<String> cp = ProxyFactory.makeProxy(Comparable.class, str, LoggingInvocationHandler.class);
		int returnValue = cp.compareTo("tianya");
		System.out.println(returnValue);
		
		List<String> list = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<String> proxy = ProxyFactory.makeProxy(List.class, list, LoggingInvocationHandler.class);
		proxy.add("Alex");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void proxyMultipleInterface()  throws Exception {
		List<String> receiverList = new ArrayList<>();
		ClassLoader cl = SimpleProxyTest.class.getClassLoader();
		LoggingInvocationHandler loghandler = new LoggingInvocationHandler(receiverList);
		
//		Class <?> proxyClass = Proxy.getProxyClass(cl,new Class<?> [] {List.class,Set.class});
		Class <?> proxyClass = Proxy.getProxyClass(cl,new Class<?> [] {Set.class,List.class});
		Object proxy = proxyClass.getConstructor(new Class [] {InvocationHandler.class}).newInstance(new Object[]{loghandler});
		
		
		List list = (List)proxy;
		list.add("Hello");
		
		Set set = (Set)proxy;
		set.add("World");
		
	}

	public static void main(String[] args) throws Exception {
		SimpleProxyTest test = new SimpleProxyTest();
//		test.useProxy();
//		test.useGenericProxy();
		test.proxyMultipleInterface();
	}

}
