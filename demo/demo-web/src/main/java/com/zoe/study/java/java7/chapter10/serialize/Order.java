package com.zoe.study.java.java7.chapter10.serialize;

import java.io.Serializable;

import com.zoe.study.java.java7.chapter10.User;

public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4500103875301783885L;
	
	@SuppressWarnings("unused")
	private User user;
	private String id;
	
	public Order(User user,String id) {
		this.user = user;
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private Object writeReplace() {
		return new OrderTO(this);
	}
	
}
