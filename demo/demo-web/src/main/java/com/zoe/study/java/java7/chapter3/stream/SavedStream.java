package com.zoe.study.java.java7.chapter3.stream;

//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
//import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.SequenceInputStream;

public class SavedStream {
	
	private InputStream input;
	private byte [] data = new byte[0];
	
//	BufferedInputStream bb;
//	DataInputStream xx;
//	PushbackInputStream pp;
//	PipedInputStream pi;
//	
//	ObjectInputStream oo;
//	FileInputStream ff;
//	SequenceInputStream ss;
	
//	Reader rr; Writer ww;
//	StringReader sr; StringWriter sw;
//	CharArrayReader cr;CharArrayWriter cw;
//	BufferedReader br; BufferedWriter bw;
//	InputStreamReader isr;
	
	
	public SavedStream(InputStream input) {
		this.input = input;
	}
	
	public void save() throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte [] buffer = new byte[1024];
		int len = -1;
		while((len = input.read(buffer)) != -1){
			output.write(buffer,0,len);
		}
		data = output.toByteArray();
	}
	
	
	public InputStream getInputStream() {
		return new ByteArrayInputStream(data);
	}

}
