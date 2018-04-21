package com.zoe.study.java.java7.chapter8;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class JavaCompilerDemo {
	
	public void compiler(Path src,Path output)  {
		ProcessBuilder pb = new ProcessBuilder("javac", src.toString(),"-d",output.toString());
		
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JavaCompilerDemo jc = new JavaCompilerDemo();
		Path src = Paths.get("/Users/zoe/Desktop/eclipse_workspace/bigdata/demo/src/main/java/com/demo/java/java7/chapter9/FileSystemClassLoader.java");
		Path output = Paths.get("/Users/zoe/Downloads/study/");
		jc.compiler(src,output);
	}

}
