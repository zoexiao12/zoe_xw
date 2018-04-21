package com.zoe.study.java.java7.chapter7.reference;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
	private Map<String,Object> cache = new HashMap<>();
	
	public Object calculate(String key) {
		if(cache.containsKey(key)) {
			return cache.get(key);
		}
		
		/**
		 * 假设此处计算非常耗时，所以将计算结果缓存；
		 * 有点 ： 此程序速度得到提升，因为将计算耗时的结果得到类缓存
		 * 缺点： 会消耗内存。计算结果将一直保存到cache没有地方被引用。随着计算越来越多，
		 * 		 缓存着计算结果到cache所耗内存也会越来越大。
		 */
		Object obj = doCalculate();
		cache.put(key, obj);
		return obj;
	}
	
	private Object doCalculate() {
		//假设此处计算非常耗时
		return new Object();
	}
	
	

}
