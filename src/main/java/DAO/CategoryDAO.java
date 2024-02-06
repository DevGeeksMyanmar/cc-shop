package DAO;

import java.util.*;
import java.sql.*;
import Models.*;

public class CategoryDAO {
	
	Connection con = null; // declare connection
	ResultSet resultset = null; // declare result set
	Statement statement = null; // declare statement
	PreparedStatement stmt = null; // declare prepare statement
	
	// category data access object model ( constructor work first)
	CategoryDAO() throws ClassNotFoundException, SQLException{
		con = Config.config.getConnections();
	}
	
	// get all category
	public List<Category> get() throws SQLException{
		List<Category> categories = null;  // create empty admin list to store admins
		Category category = null; // create admin object which is from model
		categories = new ArrayList<Category>(); // create ArrayList admin object
		String query = "SELECT * FROM categories"; // prepare query
		statement = con.createStatement(); // create statement
		resultset = statement.executeQuery(query); // execute that query and store that into resultset variable
		while(resultset.next()) {  // until end
			category = new Category(); // create admin object
			category.setId(resultset.getInt("id"));  // set admin id from admin table's data
			category.setName(resultset.getString("name"));
			categories.add(category);
		}
		return categories; // return that list
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
		Category category = new Category();
		String query = "SELECT * FROM categories WHERE name = ?" ;
		stmt = con.prepareStatement(query);
		stmt.setString(1, name);
		resultset = stmt.executeQuery();
		if(resultset.next()) {
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
	
	// delete admin
	public boolean delete(int id) throws SQLException {
		boolean flag = false;
		String query = "DELETE FROM categories WHERE id = " + id;
		statement = con.createStatement();
		int deletedRow = statement.executeUpdate(query);
		if(deletedRow > 0) flag = true;
		return flag;
	}
	
	// update admin
	public boolean update(Category category) throws SQLException {
		boolean flag = false;
		String query = "UPDATE admin SET name=? WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, category.getName());
		stmt.setInt(2, category.getId());
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
			List<Category> all = dao.get();
			for(Category category : all) {
				System.out.println(category.getName());
			}
			
	}
	

}
