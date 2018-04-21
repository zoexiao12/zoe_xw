package com.zoe.study.java.java7.chapter3.pipeline;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelTest {
	
	public void openAndWrite() throws IOException {
		FileChannel channel = FileChannel.open(Paths.get("my.txt"), 
				StandardOpenOption.CREATE,StandardOpenOption.WRITE);
		ByteBuffer buffer = ByteBuffer.allocate(64);
		buffer.putChar('A').flip();
		channel.write(buffer);
	}
	
	public void readWriteAbsolute() throws IOException {
		FileChannel channel = FileChannel.open(Paths.get("absolute.txt"), 
				StandardOpenOption.CREATE,StandardOpenOption.WRITE,StandardOpenOption.READ);
		ByteBuffer writeBuffer = ByteBuffer.allocate(4).putChar('A').putChar('B');
		writeBuffer.flip();
		channel.write(writeBuffer, 1024);
		
		ByteBuffer readBuffer = ByteBuffer.allocate(2);
		channel.read(readBuffer, 1026);
		readBuffer.flip();
		System.out.println(readBuffer.getChar());
		
	}
	
	public void loadWebPage(String url) throws IOException {
		try(FileChannel channel = FileChannel.open(Paths.get("content.txt"),
				StandardOpenOption.CREATE,StandardOpenOption.WRITE)){
			InputStream input = new URL(url).openStream(); //乱码
			
			ReadableByteChannel readChannel = Channels.newChannel(input);
			
			channel.transferFrom(readChannel, 0, Integer.MAX_VALUE);
		}
	}
	
	public void copyUseByteBuffer(String sourceFileName,String destFileName) throws IOException{
		ByteBuffer buffer = ByteBuffer.allocate(3*1024);
		try(FileChannel source = FileChannel.open(Paths.get(sourceFileName), 
				StandardOpenOption.READ)){
			FileChannel dest = FileChannel.open(Paths.get(destFileName), 
					StandardOpenOption.CREATE,StandardOpenOption.WRITE);
			while(source.read(buffer) >0 || source.position() != 0){
				buffer.flip();
				dest.write(buffer);
				buffer.compact();
			}
			
		}
	}
	
	public void copyUseChannelTransfer(String sourceFileName,String destFileName) throws IOException {
		try(FileChannel source = FileChannel.open(Paths.get(sourceFileName), StandardOpenOption.READ)) {
			FileChannel dest = FileChannel.open(Paths.get(destFileName), 
					StandardOpenOption.CREATE,StandardOpenOption.WRITE);
			source.transferTo(0, Integer.MAX_VALUE, dest);
			
		}
	}
	
	public void mapFile() throws IOException {
		try(FileChannel channel = FileChannel.open(Paths.get("copy1.txt"), 
				StandardOpenOption.READ,StandardOpenOption.WRITE)){
			MappedByteBuffer mapBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
			byte b = mapBuffer.get(700*1024);
			System.out.println(b+"channel :"+channel.size());
			mapBuffer.put(710*1024, b);
			mapBuffer.force();
		}
	}
	
	public void updateWithLock() throws IOException {
		try(FileChannel channel = FileChannel.open(Paths.get("copy1.txt"), 
				StandardOpenOption.READ,StandardOpenOption.WRITE)){
			FileLock lock = channel.lock();
//			FileLock lock  = channel.lock(position, size, shared)
			//更新操作；
			lock.release();
		}
	}
	
	public void loadWebPageUseSocket() throws IOException {
		try(FileChannel destChannel = FileChannel.open(Paths.get("content2.txt"),
				StandardOpenOption.CREATE,StandardOpenOption.WRITE)){
			SocketChannel sc = SocketChannel.open(new InetSocketAddress("www.baidu.com",80));
			
			String request = "GET / HTTP/1.1\r\n\r\nHost:www.baidu.com\r\n\r\n";
			ByteBuffer header = ByteBuffer.wrap(request.getBytes("UTF-8"));
			sc.write(header);
			destChannel.transferFrom(sc, 0, Integer.MAX_VALUE);
		}
	}
	
	public void startSimpleServer() throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.bind(new InetSocketAddress("localhost", 8080));
		while (true) {
			try(SocketChannel client = serverChannel.accept()){
				client.write(ByteBuffer.wrap("hello".getBytes("UTF-8")));
			}
		}
		
	}

	public static void main(String[] args) throws IOException { 
		FileChannelTest test = new FileChannelTest();
//		test.openAndWrite();
//		test.readWriteAbsolute();
//		test.loadWebPage("http://www.163.com");
//		test.copyUseByteBuffer("content.txt", "copy1.txt");
//		test.copyUseChannelTransfer("content.txt", "copy2.txt");
//		test.mapFile();
		test.loadWebPageUseSocket();
	}

}
