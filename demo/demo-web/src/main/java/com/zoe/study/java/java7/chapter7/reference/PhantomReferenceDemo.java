package com.zoe.study.java.java7.chapter7.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {
	
	
	
	private static class ReferencedObject {
		@Override
		protected void finalize() throws Throwable {
			System.out.println("finalize方法被调用");
			super.finalize();
		}
	}
	
	
	
	public void phantomReferenceQueue() {
		ReferenceQueue<ReferencedObject> queue = new ReferenceQueue<>();
		ReferencedObject obj = new ReferencedObject();
		PhantomReference<ReferencedObject> ref = new PhantomReference<>(obj,queue);
		
		//运行多次结果不一定相同
//		WeakReference<ReferencedObject> ref = new WeakReference<> (obj,queue);
//		SoftReference<ReferencedObject> ref = new SoftReference<>(obj,queue);
		
		obj = null;
		
		Reference<? extends ReferencedObject> tempRef = null;
		while((tempRef = queue.poll()) == null) {
			System.gc();
		}
		ref.clear();
		System.out.println(tempRef == ref);
		System.out.println("对象被清除");
	}
	

	public static void main(String[] args) {
		PhantomReferenceDemo pd = new PhantomReferenceDemo();
		pd.phantomReferenceQueue();
	}

}
