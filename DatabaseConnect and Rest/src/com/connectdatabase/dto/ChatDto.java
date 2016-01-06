package com.connectdatabase.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Dto for the Chat Object
 * @author adawer
 *
 */
@XmlRootElement
public class ChatDto {

	private int chatid;
	private String chatuser;
	private String chatmessage;
	public int getChatid() {
		return chatid;
	}
	public void setChatid(int chatid) {
		this.chatid = chatid;
	}
	public String getChatuser() {
		return chatuser;
	}
	public void setChatuser(String chatuser) {
		this.chatuser = chatuser;
	}
	public String getChatmessage() {
		return chatmessage;
	}
	public void setChatmessage(String chatmessage) {
		this.chatmessage = chatmessage;
	}
}
