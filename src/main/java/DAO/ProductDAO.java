package DAO;

import java.sql.*;
import java.util.*;

import Models.Image;
import Models.Product;
import Models.Seller;

public class ProductDAO {
	
	Connection con = null;
	Statement statement = null;
	PreparedStatement stmt = null;
	ResultSet resultSet = null;
	private int noOfRecords;
	
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
	
	// get product count by seller id
	public List<Product> getProductBySellerId(int id) throws SQLException {
		List<Product> products = new ArrayList<Product>();
		String query = "SELECT products.name, MAX(products.rating) FROM products LEFT JOIN sellers ON sellers.id = products.seller_id WHERE sellers.id=" + id + " GROUP BY products.name";
		statement = con.createStatement();
		resultSet = statement.executeQuery(query);
		Product product = null;
		while(resultSet.next()) {
			product = new Product();
			product.setName(resultSet.getString("name"));
			product.setRating(resultSet.getInt("rating"));
			products.add(product);
		}
		return products;
	}
	
	// get product by product Id
	public Product getProductById(int id) throws SQLException{
		Product product = null;
		String query = "select products.*, categories.name as category_name, sellers.name as seller_name FROM products LEFT JOIN categories ON products.category_id = categories.id LEFT JOIN sellers ON products.seller_id = sellers.id WHERE products.id="+id;
		statement = con.createStatement();
		resultSet = statement.executeQuery(query);
        while(resultSet.next()) {
            product = new Product();
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
        }
        return product;
	}
	
	// create product content
	public boolean createProductContent(Product product) throws SQLException {
		boolean flag = false;
		String query = "INSERT INTO products (name, price, description, count, category_id, seller_id) VALUES (?,?,?,?,?,?)";
		stmt = con.prepareStatement(query);
		stmt.setString(1, product.getName());
		stmt.setInt(2, product.getPrice());
		stmt.setString(3, product.getDescription());
		stmt.setInt(4, product.getCount());
		stmt.setInt(5, product.getCategory_id());
		stmt.setInt(6, product.getSeller_id());
		int insertedRow = stmt.executeUpdate();
		if(insertedRow > 0) flag = true;
		return flag;
	}
	
	// create product image
	public boolean createProductImage(Image image, int product_id) throws SQLException {
		boolean flag= false;
		// query for inserting image
		String query = "INSERT INTO images (name) VALUES (?)";
		stmt = con.prepareStatement(query);
		stmt.setString(1, image.getName());
		int insertedImage = stmt.executeUpdate(); // insert into image table
		stmt.close();
		if(insertedImage > 0) {
			// query for inserting product + image table (we need image_id and product_id)
			// we get image id after inserting image
			
			// so we need to retrieve that image id (I think this might be done with image name
			int image_id = 0;
			String query_for_getting_img_id = "SELECT * from images WHERE name=?";
			stmt = con.prepareStatement(query_for_getting_img_id);
			stmt.setString(1, image.getName());
			resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				image_id = resultSet.getInt("id"); // now we get image id
			}
			stmt.close();
			
			// product_id is passed from url parameter
			String query_for_product_image = "INSERT INTO product_images (product_id,image_id) VALUES (?,?)";
			stmt = con.prepareStatement(query_for_product_image);
			stmt.setInt(1, product_id);
			stmt.setInt(2, image_id);
			int insertedImageProduct = stmt.executeUpdate();
			if(insertedImageProduct > 0) {
				flag = true;
			}
		}
		return flag;
	}
	
	// update product content
	public boolean updateProductContent(Product product) throws SQLException {
		boolean flag = false;
		String query = "update products SET name=?, price=?, count=?, description=?, category_id=?, seller_id=? WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, product.getName());
		stmt.setInt(2, product.getPrice());
		stmt.setInt(3, product.getCount());
		stmt.setString(4, product.getDescription());
		stmt.setInt(5, product.getCategory_id());
		stmt.setInt(6, product.getSeller_id());
		stmt.setInt(7, product.getId());
		int updatedRow = stmt.executeUpdate();
		if(updatedRow > 0) flag = true;
		return flag;
	}
	
	// update product image
//	public boolean updateProductImage(Image image, int seller_id) throws SQLException {
//		boolean flag = false;
//	}
	
	// get all product
		public List<Product> getAll(int offset, int noOfRecords) throws SQLException{
			List<Product> products = new ArrayList<Product>();  // create empty admin list to store admins
			Product product = null; // create admin object which is from model
			String query = "select SQL_CALC_FOUND_ROWS products.*, categories.name as category_name, sellers.name as seller_name FROM products LEFT JOIN categories ON products.category_id = categories.id LEFT JOIN sellers ON products.seller_id = sellers.id ORDER BY updated_at DESC limit " + offset + ", " + noOfRecords;
			statement = con.createStatement();
	        resultSet = statement.executeQuery(query);
	        while(resultSet.next()) {
	             product = new Product();
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
			resultSet.close();
			resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
	        if(resultSet.next()) {
	        	 this.noOfRecords = resultSet.getInt(1);
	        }
			return products; // return that list
		}

		// get number of records
		public int getNoOfRecords() {
	        return noOfRecords;
	    }
		
		// delete product
		public boolean delete(int id) throws SQLException {
				boolean flag = false;
				String query = "DELETE FROM products WHERE id = " + id;
				statement = con.createStatement();
				int deletedRow = statement.executeUpdate(query);
				if(deletedRow > 0) flag = true;
				return flag;
		}
		
		
	
	
	// testing method
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		ProductDAO dao = new ProductDAO();
		System.out.println(dao.get());
	}
}
