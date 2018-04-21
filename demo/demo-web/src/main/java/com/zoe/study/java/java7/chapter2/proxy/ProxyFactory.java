package com.zoe.study.java.java7.chapter2.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public final class ProxyFactory {
	
	
	@SuppressWarnings("unchecked")
	public static <T> T makeProxy(Class<T> intf, final T obj ,Class <? extends InvocationHandler> hc) 
		throws Exception {
		ClassLoader cl = obj.getClass().getClassLoader();
		
		InvocationHandler handler = (InvocationHandler)hc.getConstructor(Object.class).newInstance(obj);
		
		return (T)Proxy.newProxyInstance(cl, new Class <?>[] {intf}, handler);
	}
	
	
	public static Object proxyAll(Object obj, Class <? extends InvocationHandler> hc) throws Exception {
		ClassLoader cl = obj.getClass().getClassLoader();
		
		InvocationHandler handler = (InvocationHandler)hc.getConstructor(Object.class).newInstance(obj);
		
		Class <?> [] interfaces = obj.getClass().getInterfaces();
		
		return Proxy.newProxyInstance(cl, interfaces, handler);
	}

}
