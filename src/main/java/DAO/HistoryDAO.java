package DAO;

import java.sql.*;
import java.util.*;
import Models.*;

public class HistoryDAO {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public HistoryDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	
	// create
	public boolean create(History history) {
		boolean flag = false;
		String query = "INSERT INTO history (price, order_code, count, product_id, customer_id, shipping_id, seller_id) VALUES "
				+ "	(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, history.getPrice());
			pst.setString(2, history.getOrder_code());
			pst.setInt(3, history.getCount());
			pst.setInt(4, history.getProduct_id());
			pst.setInt(5, history.getCustomer_id());
			pst.setInt(6, history.getShipping_id());
			pst.setInt(7, history.getSeller_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
}
