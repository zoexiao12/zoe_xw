package com.zoe.study.java.java7.chapter10.serialize;

import com.zoe.study.java.java7.chapter10.User;

public class OrderGateway {
	
	public static Object getOrder(String orderId) {
		Order order = new Order(new User(),orderId);
		return order;
	}
}
