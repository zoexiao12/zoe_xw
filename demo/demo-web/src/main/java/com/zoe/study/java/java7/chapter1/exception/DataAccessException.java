package com.zoe.study.java.java7.chapter1.exception;

public class DataAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8300272956161698628L;
	
	public DataAccessException(Exception e) {
		super(e);
	}

}
