package com.connectdatabase.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorizationDto {
	
	private String userId;
	private String old_password;
	private String new_password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	
}

