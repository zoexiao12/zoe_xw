package com.zoe.study.java.java7.chapter1.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DataAccessGateway {
	
	public void load() throws DataAccessException {
		try {
			@SuppressWarnings({ "unused", "resource" })
			FileInputStream input = new FileInputStream("data.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new DataAccessException(e);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
