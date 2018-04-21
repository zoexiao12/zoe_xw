package com.zoe.study.java.java7.chapter10.serialize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ExternalizableUser implements Externalizable {
	
	private String name;
	private String mail;
	
	public ExternalizableUser() {}
	
	public ExternalizableUser(String name,String mail) {
		this.name = name;
		this.mail = mail;
	}
	

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(getName());
		out.writeUTF(getMail());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.name = in.readUTF();
		this.mail = in.readUTF();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}
