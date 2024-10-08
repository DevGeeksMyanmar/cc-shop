package DAO;

import java.sql.*;
import java.util.*;
import Models.*;
import DAO.*;

public class WhistlistDAO {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	
	public WhistlistDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	
	// add to whistlist table
	public boolean create(int product_id, int customer_id) {
		boolean flag = false;
		String query = "INSERT INTO whistlists (product_id, customer_id) VALUES (?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, product_id);
			pst.setInt(2, customer_id);
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	// get by customer id and product_id
	public int get_by_customer_id_and_product_id(int product_id, int customer_id) {
		int flag = 0;
		String query = "SELECT * FROM whistlists WHERE product_id = ? AND customer_id = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, product_id);
			pst.setInt(2, customer_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				flag = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	// get count by customer id
	public int getCountByCustomerID(int customer_id) {
		int count = 0;
		String query = "SELECT * FROM whistlists WHERE customer_id = " + customer_id;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	// delete wishlist
	public boolean remove(int wish_id) {
		boolean flag = false;
		String query = "DELETE FROM whistlists WHERE id =" + wish_id;
		try {
			stmt = con.createStatement();
			int deleted = stmt.executeUpdate(query);
			if(deleted > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	// get product id by customer id
	public List<Integer> getProductIDByUserID(int user_id){
		List<Integer> list = new ArrayList<Integer>();
		String query = "SELECT * from whistlists WHERE customer_id = " + user_id;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getInt("product_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return list;
	}
	
	// get wishlist product by customer id
	public List<Product> getByCustomerID(int customer_id) {
		List<Product> products = new ArrayList<Product>();
		String query = "SELECT products.*, categories.name AS category_name, MIN(images.name) AS image_name, sellers.name AS seller_name FROM products "
	             + "LEFT JOIN categories ON products.category_id = categories.id "
	             + "LEFT JOIN sellers ON products.seller_id = sellers.id "
	             + "LEFT JOIN images ON images.product_id = products.id "
	             + "WHERE EXISTS (SELECT 1 FROM whistlists WHERE whistlists.product_id = products.id AND whistlists.customer_id = " + customer_id + ") "
	             + "GROUP BY products.id";

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				 Product product = new Product();
		            product.setId(rs.getInt("id"));
		            product.setName(rs.getString("name"));
		            product.setPrice(rs.getInt("price"));
		            product.setDescription(rs.getString("description"));
		            product.setCount(rs.getInt("count"));
		            product.setRating(rs.getInt("rating"));
		            product.setCategory_id(rs.getInt("category_id"));
		            product.setSeller_id(rs.getInt("seller_id"));
		            product.setSeller_name(rs.getString("seller_name"));
		            product.setCategory_name(rs.getString("category_name"));
		            product.setImage(rs.getString("image_name"));
		            
		         products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	
	
}
