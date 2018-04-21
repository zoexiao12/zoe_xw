package com.zoe.study.java.effective.chapter01.staticfactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Services {
	
	private Services() {}
	
	public final static String DEFAUT_PROVIDER_NAME = "<def>";
	private final static Map<String,Provider> providers = new ConcurrentHashMap<>();
	
	public static void registerProvider(String name,Provider p) {
		providers.put(name, p);
	}
	public static void registerDefaultProvider(Provider p){
		registerProvider(DEFAUT_PROVIDER_NAME,p);
	}
	
	public static Service newInstance(String name) {
		Provider p = providers.get(name);
		if(p == null) {
			throw new IllegalArgumentException("No provider registered with name:" + name);
		}
		return p.newService();
	}
	public static Service newInstance() {
		return newInstance(DEFAUT_PROVIDER_NAME);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
