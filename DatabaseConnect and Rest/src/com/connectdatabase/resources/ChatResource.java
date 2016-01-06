package com.connectdatabase.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.connectdatabase.dao.ChatDao;
import com.connectdatabase.dto.ChatDto;

/**
 * Resource for ChatRoom application
 * @author adawer
 *
 */
@Path("/chats")
public class ChatResource {

	/**
	 * An Api to fetch all the chats in the chatroom
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ChatDto> getChats(){
		List<ChatDto>list=new ArrayList<ChatDto>();
		ChatDao dao=new ChatDao();
		list=dao.getChats();
		return list;
	}
	

	/**
	 * An Api to add new chat in the chatroom
	 * @param dto
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addChat(ChatDto dto){
		ChatDao dao=new ChatDao();
		return dao.addChat(dto);
	}
}
