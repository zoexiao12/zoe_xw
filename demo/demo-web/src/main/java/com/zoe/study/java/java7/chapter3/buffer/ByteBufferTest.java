package com.zoe.study.java.java7.chapter3.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferTest {

	public void useByteBuffer() {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.put((byte)1);
		buffer.put(new byte[3]);
		buffer.putChar('A'); // 2个字节 
		buffer.putFloat(0.5f); // 4个字节
		buffer.putLong(10l);  // 8个字节 
		buffer.putInt((int)8);
		System.out.println("buffer.getChar(4)--"+buffer.getChar(4));
		System.out.println("buffer.getFloat(5)--"+buffer.getFloat(6));
		System.out.println("buffer.getLong(10)--"+buffer.getLong(10));
		System.out.println("buffer.getInt(14)--"+buffer.getInt(18));
	}
	
	public void byteOrder(){
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(10);
		
//		buffer.flip(); buffer.getInt(0)
		buffer.order(ByteOrder.BIG_ENDIAN);
		System.out.println("大端－－buffer.getInt()--"+buffer.getInt());
		
//		buffer.flip(); or  buffer.getInt(0)
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		System.out.println("小端－－buffer.getInt()--"+buffer.getInt(0));
		
		System.out.println("默认底层的表示法："+ByteOrder.nativeOrder());
	}
	
	public void compant() {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.put(new byte[16]);
		buffer.flip();
		
		buffer.getInt();
		System.out.println("buffer.getInt()--当前位置："+buffer.position());
		System.out.println("buffer.limit()--限制："+buffer.limit());
		
		buffer.compact();
		System.out.println("buffer.compact()--当前位置："+buffer.position());
		System.out.println("compact()之后的limit()--限制："+buffer.limit());
	}
	
	public static void main(String[] args) {
		ByteBufferTest test = new ByteBufferTest();
//		test.useByteBuffer();
//		test.byteOrder();
		test.compant();
	}

}
