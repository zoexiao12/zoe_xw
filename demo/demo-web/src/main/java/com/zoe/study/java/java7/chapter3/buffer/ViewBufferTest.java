package com.zoe.study.java.java7.chapter3.buffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ViewBufferTest {
	
	public void viewBuffer() {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.putInt(1);
		buffer.putLong(100000L);
		System.out.println("buffer.position()--"+buffer.position());
		System.out.println("buffer.limit()--"+buffer.limit());
		System.out.println("-----------------------");
		
		IntBuffer intBuffer = buffer.asIntBuffer();
		intBuffer.put(20);
//		intBuffer.flip();
		System.out.println("intBuffer.get()--"+intBuffer.get(0));
		System.out.println("intBuffer.position()--"+intBuffer.position());
		System.out.println("intBuffer.limit()--"+intBuffer.limit());
	}

	public static void main(String[] args) {
		ViewBufferTest test = new ViewBufferTest();
		test.viewBuffer();
	}

}
