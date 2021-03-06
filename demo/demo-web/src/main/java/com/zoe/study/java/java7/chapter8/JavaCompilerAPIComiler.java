package com.zoe.study.java.java7.chapter8;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class JavaCompilerAPIComiler {
	
	public void compile (Path src, Path output) throws IOException {
		JavaCompiler compiler =  ToolProvider.getSystemJavaCompiler();
		try(StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);){
			Iterable <? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(src.toFile());
			Iterable <String> options = Arrays.asList("-d",output.toString());
			CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits);
			boolean result = task.call();
			System.out.println(result);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
