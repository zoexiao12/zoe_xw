package com.zoe.study.java.java7.chapter8;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

public class StringSourceJavaFileObject extends SimpleJavaFileObject {
	private String content;

	public  StringSourceJavaFileObject(String name,String content) {
		super(URI.create("string:///"+name.replace(',', '/') + Kind.SOURCE.extension),Kind.SOURCE);
		this.content = content;
	}
	
	public CharSequence getCharContent(boolean ingoreEncodingErrors) throws IOException{
		return content;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
