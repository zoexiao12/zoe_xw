package com.zoe.study.java.java7.chapter10.serialize;

import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.zoe.study.java.java7.chapter10.User;

public class ReadUser {
	
	public User read() throws Exception {
		User user = null;
		Path path = Paths.get("use.bin1");
		try(ObjectInputStream is = new ObjectInputStream(Files.newInputStream(path))) {
			user = (User)is.readObject();
		}
		return user;
	}

	public static void main(String[] args) throws Exception{
		ReadUser ru = new ReadUser();
		User user = ru.read();
		System.out.println(user);
	}

}
