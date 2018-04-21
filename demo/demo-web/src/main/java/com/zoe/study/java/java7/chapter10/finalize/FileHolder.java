package com.zoe.study.java.java7.chapter10.finalize;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

//错误的finalize
public class FileHolder {
	
	private Path path;
	private InputStream input;
	
	public void open() throws IOException {
		
		input = Files.newInputStream(path, StandardOpenOption.WRITE);
		//正确做法
//		try(InputStream inputx = Files.newInputStream(path, StandardOpenOption.WRITE);) {
//			input = inputx;
//		}
	}

	@Override
	protected void finalize() throws Throwable {
		if(input != null) {
			input.close();
		}
		super.finalize();
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
