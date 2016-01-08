package com.anubhav.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import com.anubhav.entity.Chats;
import com.connectdatabase.dto.ChatDto;

/**
 * This is the dao class which interacts with H2 database 
 * @author adawer
 *
 */
public class ChatsDao {

	/**
	 * This method fetchs the chats from the database
	 * @return
	 */
	public List<Chats> getChats() {
		Session session = new AnnotationConfiguration().configure().buildSessionFactory().openSession();
		Transaction t;
		t = session.beginTransaction();
		Query q = session.createQuery("From Chats");
		List<Chats> chatList = q.list();
		return chatList;
	}

	/**
	 * This method adds a new chat in the database
	 * @param dto
	 * @return
	 */
	public String addChat(ChatDto dto) {
		Session session = new AnnotationConfiguration().configure().buildSessionFactory().openSession();
		Transaction t;
		t = session.beginTransaction();
		Chats chat = new Chats();
		chat.setChatmessage(dto.getChatmessage());
		chat.setChatuser(dto.getChatuser());
		session.save(chat);
		t.commit();
		session.close();
		System.out.println("Chat successfully added");

		return "chat successfully added";
	}
}
	
