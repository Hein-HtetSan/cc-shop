package DAO;

import java.sql.*;
import java.util.*;
import Models.Product;

public class ProductDAO {
	
	Connection con = null;
	Statement statement = null;
	PreparedStatement stmt = null;
	ResultSet resultSet = null;
	
	// constructor
	public ProductDAO() throws ClassNotFoundException, SQLException{
		con = Config.config.getConnections();
	}
	
	// get all products
	public List<Product> get(){
	    List<Product> products = new ArrayList<Product>();
	    String query = "SELECT products.*, categories.name as category_name, sellers.name as seller_name FROM products LEFT JOIN categories ON products.category_id = categories.id LEFT JOIN sellers ON products.seller_id = sellers.id";
	    try {
	        statement = con.createStatement();
	        resultSet = statement.executeQuery(query);
	        while(resultSet.next()) {
	            Product product = new Product();
	            product.setId(resultSet.getInt("id"));
	            product.setName(resultSet.getString("name"));
	            product.setPrice(resultSet.getInt("price"));
	            product.setDescription(resultSet.getString("description"));
	            product.setCount(resultSet.getInt("count"));
	            product.setRating(resultSet.getInt("rating"));
	            product.setCategory_id(resultSet.getInt("category_id"));
	            product.setSeller_id(resultSet.getInt("seller_id"));
	            product.setSeller_name(resultSet.getString("seller_name"));
	            product.setCategory_name(resultSet.getString("category_name"));
	            // Add the product to the list
	            products.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close resources in the finally block to ensure they are always closed
	        try {
	            if(resultSet != null) resultSet.close();
	            if(statement != null) statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return products;
	}

	
	
	// testing method
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		ProductDAO dao = new ProductDAO();
		System.out.println(dao.get());
	}
}
