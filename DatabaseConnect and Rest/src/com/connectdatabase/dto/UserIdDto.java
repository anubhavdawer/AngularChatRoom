package com.connectdatabase.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserIdDto {

	private String old_userId;
	private String new_userId;
	private String password;
	public String getOld_userId() {
		return old_userId;
	}
	public void setOld_userId(String old_userId) {
		this.old_userId = old_userId;
	}
	public String getNew_userId() {
		return new_userId;
	}
	public void setNew_userId(String new_userId) {
		this.new_userId = new_userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
