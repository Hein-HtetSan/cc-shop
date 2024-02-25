package DAO;

import java.sql.*;
import Models.*;

import java.util.*;

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
			pst.setString(1, order.getOrder_code());
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
//		public List<Order> getByUser(int id)
		public List<Order> getByUser(int user_id){
			List<Order> orders = new ArrayList<Order>();
			String query = "SELECT * "
					+ "FROM orders "
					+ "LEFT JOIN products ON orders.product_id = products.id "
					+ "LEFT JOIN addresses ON orders.address_id = addresses.id "
					+ "LEFT JOIN customers ON customers.id = orders.customer_id "
					+ "WHERE orders.customer_id = " + user_id;
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Order order = new Order();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					orders.add(order);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return orders;
		}
		
		public List<Order> getBySeller(int seller_id){
			List<Order> orders = new ArrayList<Order>();
			String query = "SELECT * "
					+ "FROM orders "
					+ "LEFT JOIN products ON orders.product_id = products.id "
					+ "LEFT JOIN addresses ON orders.address_id = addresses.id "
					+ "LEFT JOIN customers ON customers.id = orders.customer_id "
					+ "WHERE products.seller_id = " + seller_id;
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Order order = new Order();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					orders.add(order);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return orders;
		}
		
}
	
	
		
	

	
	
	
