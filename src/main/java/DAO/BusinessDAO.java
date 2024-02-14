package DAO;

import java.sql.*;
import java.util.*;
import Models.Business;
import Models.Category;

public class BusinessDAO {
	
	Connection con = null;
	PreparedStatement stmt = null;
	Statement statement = null;
	ResultSet resultset = null;
	private int noOfRecords;
	
	public BusinessDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	
	// get all business
	// get all business
		public List<Business> getAll(int offset, int noOfRecords) throws SQLException{
			List<Business> businesses = null;  // create empty admin list to store admins
			Business business = null; // create admin object which is from model
			businesses = new ArrayList<Business>(); // create ArrayList admin object
			String query = "select SQL_CALC_FOUND_ROWS * from businesses limit " + offset + ", " + noOfRecords;
			statement = con.createStatement(); // create statement
			resultset = statement.executeQuery(query); // execute that query and store that into resultset variable
			while(resultset.next()) {  // until end
				business = new Business(); // create admin object
				business.setId(resultset.getInt("id"));  // set admin id from admin table's data
				business.setName(resultset.getString("name"));
				businesses.add(business);
			}
			resultset.close();
			resultset = statement.executeQuery("SELECT FOUND_ROWS()");
	        if(resultset.next()) {
	        	 this.noOfRecords = resultset.getInt(1);
	        }
			return businesses; // return that list
		}
		
		// get number of records
		public int getNoOfRecords() {
	        return noOfRecords;
	    }
	
	// get all businesses
	public List<Business> get() throws SQLException{
		List<Business> businesses = new ArrayList<Business>();
		String query = "SELECT * FROM businesses";
		statement = con.createStatement();
		resultset = statement.executeQuery(query);
		while(resultset.next()) {
			Business business = new Business();
			business.setId(resultset.getInt("id"));
			business.setName(resultset.getString("name"));
			
			businesses.add(business);
		}
		return businesses;
	}
	
	// create business
		public boolean create(Business business)  {
			boolean flag = false;
			String query = "INSERT INTO businesses (name) VALUES (?)";
			try {
				stmt = con.prepareStatement(query);
				stmt.setString(1, business.getName());
				int rowInserted = stmt.executeUpdate();
				if(rowInserted > 0) flag =true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return flag;
		}
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		List<Business> bus = new ArrayList<Business>();
		BusinessDAO busDAO = new BusinessDAO();
		bus = busDAO.get();
		
		for(Business buss : bus) {
			System.out.println(buss.getId());
			System.out.println(buss.getName());
		}
		
	}
}
