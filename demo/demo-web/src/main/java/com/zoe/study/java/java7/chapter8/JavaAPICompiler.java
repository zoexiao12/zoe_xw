package com.zoe.study.java.java7.chapter8;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;



public class JavaAPICompiler {
	
	public void compiler(Path src,Path output) throws FileNotFoundException  {
		String [] args = new String[] {src.toString(),"-d",output.toString()};
		PrintWriter out = new PrintWriter(Paths.get("output.txt").toFile());
	//	com.sun.tools.javac.Main.compile(args,out);

	}

	public static void main(String[] args) throws FileNotFoundException {
		JavaAPICompiler jac = new JavaAPICompiler();
		Path src = Paths.get("/Users/zoe/Desktop/eclipse_workspace/bigdata/demo/src/main/java/com/demo/java/java7/chapter9/FileSystemClassLoader.java");
		Path output = Paths.get("/Users/zoe/Downloads/study/");
		jac.compiler(src, output);
	}

}
