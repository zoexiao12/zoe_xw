package com.zoe.study.java.java7.chapter7.reference;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class BookKeeper {
//	private Map<Book,Set<User>> books = new HashMap<>();
	//键弱引用
	private Map<Book,Set<User>> books = new WeakHashMap<>();
	
	public void browerBook(Book book,User user) {
		Set<User> users = null;
		if(books.containsKey(book)) {
			users = books.get(book);
		}else {
			users = new HashSet<>();
			books.put(book, users);
		}
		users.add(user);
	}
	
	public void returnBook(Book book,User user){
		if(books.containsKey(book)) {
			Set<User> users = books.get(book);
			users.remove(user);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
