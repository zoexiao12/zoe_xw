package com.zoe.study.java.java7.chapter7.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Random;

public class PhantomAlloctor {
	private byte [] data = null;
	private ReferenceQueue<byte [] > queue = new ReferenceQueue<byte []> ();
	private Reference<? extends byte []> ref = null;
	
	public byte [] get(int size) {
		if(ref == null) {
			data = new byte[size];
			ref = new PhantomReference<byte[]>(data, queue);
		}else {
			data = null;
			System.gc();
			try {
				ref = queue.remove();
				ref.clear();
				ref = null;
				System.gc();
				
				data = new byte[size];
				ref = new PhantomReference<byte[]>(data, queue);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		return data;
	}

	public static void main(String[] args) {
		PhantomAlloctor pa = new PhantomAlloctor();
		Random r = new Random();
		for(int i =0;i<100;i++) {
			pa.get(r.nextInt(1024*10));
		}
	}

}
