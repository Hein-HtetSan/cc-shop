package DAO;

import java.sql.*;
import java.util.*;

import Models.Cart;
import Models.Product;

public class CartDAO {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public CartDAO() throws ClassNotFoundException, SQLException {
		super();
		con = Config.config.getConnections();
	}
	
	// get all cart
	public List<Cart> get(){
		List<Cart> carts = new ArrayList<Cart>();
		Cart cart = null;
		String query = "SELECT * FROM carts";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				cart = new Cart();
				cart.setId(rs.getInt("id"));
				cart.setProduct_id(rs.getInt("product_id"));
				cart.setCustomer_id(rs.getInt("customer_id"));
				cart.setCount(rs.getInt("count"));
				cart.setPrice(rs.getInt("price"));
				carts.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carts;
	}
	
	// get by cart id
	public Cart getById(int id) {
		Cart cart = null;
		String query = "SELECT * FROM carts WHERE id =" +id;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				cart = new Cart();
				cart.setId(rs.getInt("id"));
				cart.setProduct_id(rs.getInt("product_id"));
				cart.setCustomer_id(rs.getInt("customer_id"));
				cart.setCount(rs.getInt("count"));
				cart.setPrice(rs.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	// create the cart
	public boolean create(Cart cart) {
		boolean flag = false;
		String query = "INSERT INTO carts (product_id, customer_id, count, price) VALUES (?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, cart.getProduct_id());
			pst.setInt(2, cart.getCustomer_id());
			pst.setInt(3, cart.getCount());
			pst.setInt(4, cart.getPrice());
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// delete the cart
	public boolean delete(int id) {
		boolean flag = false;
		String query = "DELETE FROM carts WHERE id = " + id;
		try {
			stmt = con.createStatement();
			int deleted = stmt.executeUpdate(query);
			if(deleted > 0) flag = true; 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// get cart by user id
	public List<Cart> getProductinCartByUserId(int id){
		List<Cart> carts = new ArrayList<Cart>();
		Cart cart = null;
		String query = "SELECT carts.*, products.name as product_name, products.price as price, images.name as image"
				+ " FROM carts "
				+ "LEFT JOIN products ON products.id = carts.product_id "
				+ "LEFT JOIN customers ON customers.id = carts.customer_id "
				+ "LEFT JOIN images ON products.id = images.product_id "
				+ "WHERE carts.customer_id =" + id;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				cart = new Cart();
				cart.setProduct_name(rs.getString("product_name"));
				cart.setProduct_id(rs.getInt("product_id"));
				cart.setCustomer_id(rs.getInt("customer_id"));
				cart.setCount(rs.getInt("count"));;
				cart.setPrice(rs.getInt("price"));
				cart.setImage(rs.getString("image"));
				carts.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carts;
	}

}
