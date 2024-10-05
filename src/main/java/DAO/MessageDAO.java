package DAO;

import java.sql.*;
import java.util.*;
import Models.*;

public class MessageDAO {
		
	Connection con = null;
	PreparedStatement pst = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public MessageDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	
	// get all message
	public List<Message> get(){
		List<Message> messages = new ArrayList<Message>();
		Message msg = null;
		String query = "SELECT * FROM messages ";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				msg = new Message();
				msg.setId(rs.getInt("id"));
				msg.setEmail(rs.getString("email"));
				msg.setName(rs.getString("name"));
				msg.setMessage(rs.getString("message"));
				msg.setUpdated_at(rs.getString("updated_at"));
				messages.add(msg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messages;
	}
	
	// insert the message
	public boolean create(Message msg) {
		boolean flag = false;
		String query = "INSERT INTO messages (name, email, phone, message) VALUES (?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, msg.getName());
			pst.setString(2, msg.getEmail());
			pst.setString(3, msg.getPhone());
			pst.setString(4, msg.getMessage());
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	// get message by id
	public Message getById(int id) {
		Message msg = null;
		String query = "SELECT * FROM messages WHERE id = " + id;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				msg = new Message();
				msg.setId(rs.getInt("id"));
				msg.setEmail(rs.getString("email"));
				msg.setName(rs.getString("name"));
				msg.setPhone(rs.getString("phone"));
				msg.setMessage(rs.getString("message"));
				msg.setUpdated_at(rs.getString("updated_at"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
	
	// delete message by id
//	public boolean destory(int id) {
//		boolean flag = false;
//		String query = "DELETE FROM messages WHERE id = " + id;
//		try {
//			stmt = con.createStatement();
//			int deleted = stmt.executeUpdate(query);
//			if(deleted > 0) flag = true;
//
//
//		}
//		
//	}
}
