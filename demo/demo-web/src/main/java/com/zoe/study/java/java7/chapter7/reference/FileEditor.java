package com.zoe.study.java.java7.chapter7.reference;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileEditor {
	
	private FileData currentFileData;
	private Map<String,FileData> openFiles = new HashMap<>();
	
	public void switchTo(Path filePath,String key ) {
		if(openFiles.containsKey(key)) {
			currentFileData = openFiles.get(key);
		}else {
			currentFileData = new FileData(filePath);
			openFiles.put(key, currentFileData);
		}
	}
	public void useFile(int i) throws IOException{
		if(currentFileData != null) {
			System.out.println(i+"---- 当前文件:"+currentFileData.getPath()+"  的大小为 : "+ currentFileData.getData().length);
		}
	}
	
	private static class FileData {
		private Path filePath;
		private SoftReference<byte[]> refData;
		
		public FileData(Path filePath) {
			this.filePath = filePath;
			refData = new SoftReference<>(new byte[0]);
		}
		
		public Path getPath() {
			return this.filePath;
		}
		
		public byte[] getData()  throws IOException {
			byte []  dataArray = refData.get();
			if(dataArray == null || dataArray.length ==0) {
				dataArray = readFile();
				refData = new SoftReference<>(dataArray);
				dataArray = null;
			}
			return refData.get();
			
			//不采用软引用
//			byte []  dataArray = readFile();
//			return dataArray;
		}
		
		public byte[] readFile()  throws IOException {
			return Files.readAllBytes(filePath);
		}
	}

	public static void main(String[] args) throws IOException{
		FileEditor fe = new FileEditor();
		/**
		 * 分别设置为软引用，强引用，看是否都会内存溢出；
		 */
		int step = 100000;
		for(int i=0;i<step;i++) {
			fe.switchTo(Paths.get("copy1.txt"),"copy1.txt_"+i);
			fe.useFile(i);
		}
	}

}
