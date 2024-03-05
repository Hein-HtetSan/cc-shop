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
	public boolean destory(int id) {
		boolean flag = false;
		String query = "DELETE FROM messages WHERE id = " + id;
		try {
			stmt = con.createStatement();
			int deleted = stmt.executeUpdate(query);
			if(deleted > 0) flag = true;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Message;


public class MessageDAO {

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pst = null;
	ResultSet resultSet = null;
	
	public MessageDAO() throws ClassNotFoundException, SQLException{
		con = Config.config.getConnections();
	}
	
	public List<Message> get(){
		List<Message> messages = null; 
		Message mes = null; 
		try {
			messages = new ArrayList<Message>();
			String query = "SELECT * FROM message"; 
			stmt = con.createStatement(); // create statement
			resultSet = stmt.executeQuery(query); // execute that query and store that into resultset variable
			while(resultSet.next()) {  // until end
				mes = new Message();
				mes.setId(resultSet.getInt("id")); 
				mes.setName(resultSet.getString("name"));
				mes.setEmail(resultSet.getString("email"));
				mes.setPhone(resultSet.getString("phone"));
				mes.setMessage(resultSet.getString("message"));
				mes.setUpdatedAt(resultSet.getString("updated_at"));
				messages.add(mes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages; // return that list
	}
	
	public boolean create(Message mes) {

		boolean flag=false;
		
		String query ="insert into message(name,phone,email,message) values(?,?,?,?)";
		
		try {
			pst=con.prepareStatement(query);
			
			pst.setString(1,mes.getName());
			pst.setString(2,mes.getPhone());
			pst.setString(3,mes.getEmail());
			pst.setString(4,mes.getMessage());
			
			//insert ဆို
			int inserted=pst.executeUpdate();
			if(inserted>0)flag=true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}
	

		
		
		return flag;
		
	}

}
