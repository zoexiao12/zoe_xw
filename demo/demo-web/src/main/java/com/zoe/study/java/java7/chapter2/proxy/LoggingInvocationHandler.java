package com.zoe.study.java.java7.chapter2.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.log4j.Level;

public class LoggingInvocationHandler implements InvocationHandler {
	private Object receiverObj;
	
	public LoggingInvocationHandler(Object receiverObj){
		this.receiverObj =  receiverObj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(Level.INFO +"代理接口"+proxy.getClass().getSimpleName()
				+" 调用方法 " + method.getName()+":参数为"+Arrays.deepToString(args));
		Object obj = method.invoke(receiverObj, args);
		
		return obj;
	}
	
	

}
