package DAO;

import java.sql.*;
import Models.*;

public class OrderDAO {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public OrderDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	// create by (
	// read
	// update (optional)
	// delete
	
	// create (customer_id, product_id, shipping_id, count , orderCode generated random)
	
	
	
}
