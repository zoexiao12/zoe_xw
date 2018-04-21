package com.zoe.study.java.java7.chapter9;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadResource {
	
	public Properties loadConfig() throws IOException {
		ClassLoader loader = getClass().getClassLoader();
		String resourceName = "com/demo/java/java7/chapter9/config.properties";
		InputStream input = loader.getResourceAsStream(resourceName);
		if(input == null) {
			throw new IOException("找不到配置文件");
		}
		Properties props = new Properties();
		props.load(input);
		return props;
	}

	public static void main(String[] args) {

	}

}
