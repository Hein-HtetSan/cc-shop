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
	public boolean create(Order order) {
		boolean flag = false;
		String query = "INSERT INTO orders (order_code, price, count, status, product_id, customer_id, shipping_id) VALUES "
				+ "(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(0, order.getOrder_code());
			pst.setInt(2, order.getPrice());
			pst.setInt(3, order.getCount());
			pst.setInt(4, order.getStatus());
			pst.setInt(5, order.getProduct_id());
			pst.setInt(6, order.getCustomer_id());
			pst.setInt(7, order.getShipping_id());
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// cancel specific order by delete method
	public boolean delete(int id) {
		boolean flag = false;
		String query = "DELETE FROM orders WHERE id = " + id;
		try {
			stmt = con.createStatement();
			int deleted = stmt.executeUpdate(query);
			if(deleted > 0) flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	// read order -> to track the order (for user)
//	public List<Order> getByUser(int id)
	
}
