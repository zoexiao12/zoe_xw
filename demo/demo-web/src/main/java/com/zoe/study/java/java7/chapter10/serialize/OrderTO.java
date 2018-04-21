package com.zoe.study.java.java7.chapter10.serialize;

import java.io.Serializable;

public class OrderTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4433384437330109246L;
	
	private String orderId;
	
	public OrderTO(Order order){
		this.orderId = order.getId();
	}
	
	private Object readResolve() {
		return OrderGateway.getOrder(orderId);
	}
	
}
