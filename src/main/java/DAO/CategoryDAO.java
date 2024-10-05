package DAO;

import java.util.*;
import java.sql.*;
import Models.*;

public class CategoryDAO {
	
	Connection con = null; // declare connection
	ResultSet resultset = null; // declare result set
	Statement statement = null; // declare statement
	PreparedStatement stmt = null; // declare prepare statement
	private int noOfRecords;
	
	// category data access object model ( constructor work first)
	public CategoryDAO() throws ClassNotFoundException, SQLException{
		con = Config.config.getConnections();
	}
	
	public List<Category> getAll(int offset, int noOfRecords) throws SQLException {
	    List<Category> categories = new ArrayList<Category>(); // create ArrayList to store categories
	    Category category = null; // create a category object which is from the model
	    String query = "SELECT SQL_CALC_FOUND_ROWS c.*, b.name as business_name " +
	                   "FROM categories c " +
	                   "JOIN businesses b ON c.business_id = b.id " +
	                   "ORDER BY c.updated_at DESC " +
	                   "LIMIT " + offset + ", " + noOfRecords;
	    statement = con.createStatement(); // create statement
	    resultset = statement.executeQuery(query); // execute the query and store the result in the resultset

	    while(resultset.next()) {  // iterate through the resultset
	        category = new Category(); // create a new Category object
	        category.setId(resultset.getInt("id"));  // set category id
	        category.setName(resultset.getString("name")); // set category name
	        category.setBusiness_name(resultset.getString("business_name")); // set business name
	        categories.add(category); // add category to the list
	    }
	    
	    resultset.close();
	    resultset = statement.executeQuery("SELECT FOUND_ROWS()");
	    if(resultset.next()) {
	        this.noOfRecords = resultset.getInt(1); // get the number of records found
	    }

	    return categories; // return the list of categories
	}

	
	// get all businesses
		public List<Category> get() throws SQLException{
			List<Category> categories = new ArrayList<Category>();
			String query = "SELECT * FROM categories";
			statement = con.createStatement();
			resultset = statement.executeQuery(query);
			while(resultset.next()) {
				Category category = new Category();
				category.setId(resultset.getInt("id"));
				category.setName(resultset.getString("name"));
				
				categories.add(category);
			}
			return categories;
		}
	
	// get number of records
	public int getNoOfRecords() {
        return noOfRecords;
    }
	
	// get by id
	public Category getById(int id) throws SQLException {
		Category category = new Category();
		String query = "SELECT * FROM categories WHERE id = " + id;
		statement = con.createStatement();
		resultset = statement.executeQuery(query);
		// resultset to category object
		if(resultset.next()) {
			category.setId(resultset.getInt("id"));
			category.setName(resultset.getString("name"));
		}
		return category; // return object admin
	}
	
	// get by name
	public Category getByName(String name) throws SQLException {
		Category category = null;
		String query = "SELECT * FROM categories WHERE name = ?" ;
		stmt = con.prepareStatement(query);
		stmt.setString(1, name);
		resultset = stmt.executeQuery();
		if(resultset.next()) {
			category = new Category();
			category.setId(resultset.getInt("id"));
			category.setName(resultset.getString("name"));
		}
		return category;
	}
	
	
	
	// create category
	public boolean create(Category category) throws SQLException {
		boolean flag = false;
		String query = "INSERT INTO categories (name)"
				+ "VALUES (?)";
		stmt = con.prepareStatement(query);
		stmt.setString(1, category.getName());
		int rowInserted = stmt.executeUpdate();
		if(rowInserted > 0) flag =true;
		return flag;
	}
	
	// delete category
	public boolean delete(int id) throws SQLException {
		boolean flag = false;
		String query = "DELETE FROM categories WHERE id = " + id;
		statement = con.createStatement();
		int deletedRow = statement.executeUpdate(query);
		if(deletedRow > 0) flag = true;
		return flag;
	}
	
	// update category
	public boolean update(Category category, int id) throws SQLException {
		boolean flag = false;
		String query = "UPDATE categories SET name=?, updated_at = current_timestamp WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, category.getName());
		stmt.setInt(2, id);
		int updatedRow = stmt.executeUpdate();
		if(updatedRow > 0) flag = true;
		return flag;
	}
	
	// testing method
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
			CategoryDAO dao = new CategoryDAO();
	
			// testing getting by name
//			Category categorybyname = dao.getByName("Electronic");
//			System.out.println(categorybyname.getName());
			
			// testing inserting
//			Category newCategory = new Category();
//			newCategory.setName("Clothes");
//			boolean newCategoryFlag = dao.create(newCategory);
//			if(newCategoryFlag) {
//				System.out.println("Created new category successfully");
//			}
			
			// testing delete admin
//			boolean deleteCategoryFlag = dao.delete(1);
//			if(deleteCategoryFlag) System.out.println("deleted success");
			
			// testing update admin
//			Category updateCategory = new Category();
//			updateCategory.setId(2);
//			updateCategory.setName("mgba");
//			boolean updateCategoryFlag = dao.update(updateCategory);
//			if(updateCategoryFlag) System.out.println("update success");
			
			// testing get all admins
			
			
	}
	

}
