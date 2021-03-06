package com.zoe.study.java.java7.chapter1.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public abstract class LocalizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -480955460954806643L;
	
	
	private static final String DEFAULT_BASE_NAME = "com/java/java7/chapter1/exception/messages";
	private String baseName = DEFAULT_BASE_NAME;
	protected ResourceBundle resourceBundle;
	private String messageKey;
	
	public LocalizedException(String messageKey){
		this.messageKey = messageKey;
		initResourceBundle();
	}

	public LocalizedException(String messageKey,String baseName){
		this.messageKey = messageKey;
		this.baseName = baseName;
		initResourceBundle();
	}
	
	private void initResourceBundle() {
		resourceBundle = ResourceBundle.getBundle(baseName);
	}
	
	protected void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	
	protected void setMessageKey(String key) {
		messageKey = key;
	}
	public abstract String getLocalizedMessage();
	public String getMessage() {
		return getLocalizedMessage();
	}
	
	protected String format(Object ...args) {
		String message = resourceBundle.getString(messageKey);
		return MessageFormat.format(message, args);
	}
}
