package com.zoe.study.java.java7.chapter10.serialize;

import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;

import com.zoe.study.java.java7.chapter10.User;

public class UserValidator implements ObjectInputValidation {
	private User user;
	
	public UserValidator (User user) {
		this.user = user;
	}
	

	@Override
	public void validateObject() throws InvalidObjectException {
		if(user.getAge()<0){
			throw new InvalidObjectException("非法年龄");
		}
	}

}
