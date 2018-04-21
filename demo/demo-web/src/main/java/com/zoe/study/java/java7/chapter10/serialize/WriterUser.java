package com.zoe.study.java.java7.chapter10.serialize;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import com.zoe.study.java.java7.chapter10.User;

public class WriterUser {
	
	public void writer(User user) throws IOException{
		Path path = Paths.get("use.bin1");
		try(ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(path))) {
			os.writeObject(user);
		}
	}

	public static void main(String[] args) throws IOException{
		WriterUser wu = new WriterUser();
//		User user = new User("zoe","009988@163.com","123456",32);
		User user = new User("zoe","009988@163.com","123456",new Date());
		wu.writer(user);
	}

}
