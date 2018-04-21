package com.zoe.study.java.java7.chapter1.exception;

public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6295755396242196777L;
	
	public BaseException(Exception e) {
		super(e);
	}

}
