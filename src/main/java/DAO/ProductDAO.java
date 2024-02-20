package DAO;

import java.sql.*;
import java.util.*;
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
