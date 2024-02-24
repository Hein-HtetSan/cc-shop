package DAO;

import java.sql.*;
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
	// update
	// delete
}
