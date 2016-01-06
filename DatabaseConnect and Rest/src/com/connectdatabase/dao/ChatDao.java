package com.connectdatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.connectdatabase.dto.ChatDto;

/**
 * 
 * @author adawer
 *
 */
public class ChatDao {

	/**
	 * This method makes a connection to database at localhost
	 * 
	 * @return
	 */
	public Connection connectDB() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sys as sysdba",
					"admin");
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * This method returns all the chats from the database
	 * 
	 * @return
	 */
	public List<ChatDto> getChats() {
		List<ChatDto> chatList = new ArrayList<ChatDto>();
		Connection conn = connectDB();
		String sql = ("Select * from chat");

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ChatDto dto = new ChatDto();
				int chatid = rs.getInt("chatid");
				String chatuser = rs.getString("chatuser");
				String chatmessage = rs.getString("chatmessage");

				dto.setChatid(chatid);
				dto.setChatmessage(chatmessage);
				dto.setChatuser(chatuser);

				chatList.add(dto);
			}
			return chatList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

			}
		}
		return null;
	}

	/**
	 * This method gets the recent chatid from the database
	 * 
	 * @return
	 */
	public int getChatid() {
		Connection conn = connectDB();
		String sql = ("select * from chat order by chatid desc");
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int chatid = rs.getInt("chatid");
				return chatid;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}
		return 0;
	}

	/**
	 * This method adds new chat in the database
	 * 
	 * @param dto
	 * @return
	 */
	public String addChat(ChatDto dto) {
		Connection conn = connectDB();
		int chatid = getChatid();
		String sql = ("insert into chat values(?,?,?)");
		PreparedStatement stmt;
		try {
			int new_chatid = chatid + 1;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, new_chatid);
			stmt.setString(2, dto.getChatuser());
			stmt.setString(3, dto.getChatmessage());
			stmt.executeUpdate();
			return "chat added successfully";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

				}
			}
		}
		return "chat can't be added";
	}
}
