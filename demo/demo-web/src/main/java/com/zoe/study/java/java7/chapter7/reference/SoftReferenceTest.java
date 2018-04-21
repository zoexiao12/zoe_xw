package com.zoe.study.java.java7.chapter7.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftReferenceTest {
	
	public SoftReference<Object> create(){
		Object obj = new Object();
		SoftReference<Object> ref = new SoftReference<Object>(obj);
		//错误用法 在SoftReference类的对象创建对象之后，垃圾回收器正好回收了SoftReference类的对象所指向的对象
//		SoftReference<Object> ref = new SoftReference<Object>(new Object());
		obj = null;
		return ref;
	}
	public SoftReference<Object> createByQueue(){
		Object obj = new Object();
		ReferenceQueue<Object> queue = new ReferenceQueue<>();
		SoftReference<Object> ref = new SoftReference<Object>(obj,queue);
		obj = null;
		return ref;
	}
	
	public Object softGet(SoftReference<Object> ref) {
		/**
		 * 错误用法 垃圾回收可能在if判断语句之后运行，回收了引用对象所指向的对象；
		 * if(ref.get() != null) {
		 *	return ref.get();
		 *	}
		 * 
		 */
		 Object obj = ref.get();
		 return obj;
		 
		 
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	

}
