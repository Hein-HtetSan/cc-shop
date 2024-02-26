package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.*;

public class AddressDAO {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public AddressDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	
	// create
	// read
	// delete
	
	
	// create address
	public boolean create(Address address) {
		boolean flag = false;
		String query = "INSERT INTO addresses (customer_id, street_address, city, state, postal_code, country) Values "
				+ "(?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, address.getCustomer_id());
			pst.setString(2, address.getStreet_address());
			pst.setString(3, address.getCity());
			pst.setString(4, address.getState());
			pst.setString(5, address.getPostal_code());
			pst.setString(6, address.getCountry());
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// delete the address
	public boolean delete(int address_id) {
		boolean flag = false;
		String query = "DELETE FROM addresses WHERE id = " + address_id;
		try {
			stmt = con.createStatement();
			int deleted = stmt.executeUpdate(query);
			if(deleted > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// get all the address
	public List<Address> get(){
		List<Address> addresses = new ArrayList<Address>();
		String query = "SELECT * FROM addresses";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("id"));
				address.setStreet_address(rs.getString("street_address"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setPostal_code(rs.getString("postal_code"));
				address.setCountry(rs.getString("country"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addresses;
	}
	
	// get all the address
		public Address getById(int id){
			Address address = null;
			String query = "SELECT * FROM addresses WHERE id = " + id;
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					address = new Address();
					address.setId(rs.getInt("id"));
					address.setStreet_address(rs.getString("street_address"));
					address.setCity(rs.getString("city"));
					address.setState(rs.getString("state"));
					address.setPostal_code(rs.getString("postal_code"));
					address.setCountry(rs.getString("country"));
					address.setCustomer_id(rs.getInt("customer_id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return address;
		}
	
	// get the address by user id
	public List<Address> getByUserID(int id){
		List<Address> addresses = new ArrayList<Address>();
		String query = "SELECT * FROM addresses WHERE customer_id = " + id;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("id"));
				address.setStreet_address(rs.getString("street_address"));
				address.setCity(rs.getString("city"));
				address.setState(rs.getString("state"));
				address.setPostal_code(rs.getString("postal_code"));
				address.setCountry(rs.getString("country"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addresses;
	}
	
}








