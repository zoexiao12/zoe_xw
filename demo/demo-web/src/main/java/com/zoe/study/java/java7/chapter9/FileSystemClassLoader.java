package com.zoe.study.java.java7.chapter9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSystemClassLoader extends ClassLoader {
	
	private Path path;
	
	public FileSystemClassLoader(Path path) {
		this.path = path;
	}
	

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			byte [] data = getClassData(name);
			//如果加密，此处可以解密 还可以增强字节码处理
			return defineClass(name,data, 0, data.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.findClass(name);
	}
	
	private byte [] getClassData(String className) throws IOException {
		Path path = classNameToPath(className);
		return Files.readAllBytes(path);
	}
	
	private Path classNameToPath(String className) throws IOException {
		return  path.resolve(className.replace(".", File.separator)+".class");
	}

	public static void main(String[] args) throws Exception {
		
		Path path = Paths.get("/Users/zoe/Downloads/cache/WEB-INF/classes/");
		FileSystemClassLoader fc = new FileSystemClassLoader(path);
		
		Class<?> clazz = fc.loadClass("com.cachelayer.pub.Constants");
		System.out.println(clazz);

	}

}
