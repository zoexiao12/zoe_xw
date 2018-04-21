package com.zoe.study.java.java7.chapter10;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Date;
import com.zoe.study.java.java7.chapter10.serialize.UserValidator;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2523457935865975289L;
	
	private String name;
	private String email;

	private String pwd;
//	private transient String pwd;
	
//	private transient int age;
	private transient Date birthDate;
	

	
	private static final ObjectStreamField [] serialPersistentFields =  {
			new ObjectStreamField("name",String.class),
			new ObjectStreamField("email",String.class),
//			new ObjectStreamField("age",int.class)
	};
	public User() {}
	public User(String name,String email) {
		this.name = name;
		this.email = email;
	}
	public User(String name,String email,String pwd) {
		this(name,email);
		this.pwd = pwd;
	}
	/*
	public User(String name,String email,String pwd,int age) {
		this(name,email,pwd);
		this.age = age;
	}
	*/
	
	public User(String name,String email,String pwd,Date birthDate) {
		this(name,email,pwd);
		this.birthDate = birthDate;
		
	}
	public User(User user) {//复制构造方法
		this.name = user.name;
		this.email = user.email;
	}
	  java.io.Externalizable aa;
	/*
	private void writeObject(ObjectOutputStream os) throws IOException {
		os.defaultWriteObject();
		os.writeInt(getAge());
	}
	private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
		is.defaultReadObject();
		this.age = is.readInt();
	}
	*/
	
	private void writeObject(ObjectOutputStream os) throws IOException {
		os.defaultWriteObject();
		
		int age = dateToAge(birthDate);
		os.writeInt(age);
		
		
	}
	private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
		is.defaultReadObject();
		
		int age = is.readInt();
		this.birthDate = ageToDate(age);
		
		is.registerValidation(new UserValidator(this), 0);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/*
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	*/
	
	public int getAge() {
		return dateToAge(birthDate);
	}
	private Date ageToDate(int age) {
		return new Date();
	}
	private int dateToAge(Date date) {
		return 32;
	}
	
	@Override
	public String toString() {
		return "name = "+this.getName()+"----email = "+this.getEmail()+"---age = "
				+this.getAge()+"----pwd = "+this.getPwd();
	}
}
