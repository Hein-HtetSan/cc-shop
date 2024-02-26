package DAO;

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
	Statement statement = null;
	PreparedStatement stmt = null;
	ResultSet resultSet = null;
	
	public MessageDAO() throws ClassNotFoundException, SQLException{
		con = Config.config.getConnections();
	}
	
	public List<Message> get(){
		List<Message> messages = null;  // create empty admin list to store admins
		Message mes = null; // create admin object which is from model
		// surround with try and catch block 
		try {
			messages = new ArrayList<Message>(); // create ArrayList admin object
			String query = "SELECT * FROM message"; // prepare query
			statement = con.createStatement(); // create statement
			resultSet = statement.executeQuery(query); // execute that query and store that into resultset variable
			while(resultSet.next()) {  // until end
				mes = new Message(); // create message object
				mes.setId(resultSet.getInt("id"));  // set admin id from admin table's data
				mes.setName(resultSet.getString("name"));
				mes.setEmail(resultSet.getString("email"));
				mes.setPhone(resultSet.getString("phone"));
				mes.setMessage(resultSet.getString("message"));
				messages.add(mes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages; // return that list
	}
	
	public static void main(String[] args) {
		
	}

}
