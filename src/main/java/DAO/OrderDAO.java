package DAO;

import java.sql.*;
import java.time.LocalDate;

import Models.*;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class OrderDAO {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	private int noOfRecords;
	
	public OrderDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	// create by (
	// read
	// update (optional)
	// delete
	
	// create (customer_id, product_id, shipping_id, count , orderCode generated random)
	public boolean create(Orders orders) {
		boolean flag = false;
		String query = "INSERT INTO orders (order_code, price, count, status, product_id, customer_id, shipping_id) VALUES "
				+ "(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, orders.getOrder_code());
			pst.setInt(2, orders.getPrice());
			pst.setInt(3, orders.getCount());
			pst.setInt(4, orders.getStatus());
			pst.setInt(5, orders.getProduct_id());
			pst.setInt(6, orders.getCustomer_id());
			pst.setInt(7, orders.getShipping_id());
			int inserted = pst.executeUpdate();
			if(inserted > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// cancel specific order by delete method
	public boolean delete(int product_id, String order_code) {
		boolean flag = false;
		String query = "DELETE FROM orders WHERE product_id = ? AND order_code = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, product_id);
			pst.setString(2, order_code);
			int deleted = pst.executeUpdate();
			if(deleted > 0) flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// ship the order
	public boolean shipOrder(String order_code, int product_id) {
		boolean flag = false;
		String query = "UPDATE orders SET status = 2, updated_at = current_timestamp WHERE product_id = ? AND order_code = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, product_id);
			pst.setString(2, order_code);
			int updated = pst.executeUpdate();
			if(updated > 0) flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	// get order by code and product id
	public Orders getByCodeAndProductID(String order_code, int product_id) {
		Orders order = null;
		String query = "SELECT orders.*, products.seller_id as seller_id, products.name as product_name FROM orders "
				+ "LEFT JOIN products ON orders.product_id = products.id "
				+ "WHERE orders.product_id = ? AND orders.order_code = ? "
				+ "GROUP BY orders.id";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, product_id);
			pst.setString(2, order_code);
			rs = pst.executeQuery();
			if(rs.next()) {
				order = new Orders();
				order.setId(rs.getInt("id"));
				order.setCount(rs.getInt("count"));
				order.setCustomer_id(rs.getInt("customer_id"));
				order.setOrder_code(rs.getString("order_code"));
				order.setPrice(rs.getInt("price"));
				order.setProduct_id(rs.getInt("product_id"));
				order.setUpdated_at(rs.getString("updated_at"));
				order.setShipping_id(rs.getInt("shipping_id"));
				order.setSeller_id(rs.getInt("seller_id"));
				order.setProduct_name(rs.getString("product_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	
	// if seller call off then set status -> -2
	public boolean callOffOrder(int product_id, String order_code) {
		boolean flag = false;
		String query = "UPDATE orders SET status = -2, updated_at = current_timestamp WHERE product_id = ? AND order_code = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, product_id);
			pst.setString(2, order_code);
			int updated = pst.executeUpdate();
			if(updated > 0) flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// transfer order
	public boolean transferOrder(int product_id, String order_code) {
		boolean flag = false;
		String query = "UPDATE orders SET status = 1, updated_at = current_timestamp WHERE product_id = ? AND order_code = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, product_id);
			pst.setString(2, order_code);
			int updated = pst.executeUpdate();
			if(updated > 0) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	// get order count where has status one
	public List<Orders> getOrderWithStatusOne() {
		List<Orders> orders = new ArrayList<Orders>();
		String query = "SELECT orders.*,products.name as product_name, customers.name as customer_name "
				+ "FROM orders "
				+ "LEFT JOIN products ON orders.product_id = products.id "
				+ "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
				+ "LEFT JOIN customers ON customers.id = orders.customer_id "
				+ "WHERE orders.status = 1";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Orders order = new Orders();
				order.setId(rs.getInt("id"));
				order.setCustomer_id(rs.getInt("customer_id"));
				order.setOrder_code(rs.getString("order_code"));
				order.setPrice(rs.getInt("price"));
				order.setCount(rs.getInt("count"));
				order.setProduct_id(rs.getInt("product_id"));
				order.setShipping_id(rs.getInt("shipping_id"));
				order.setStatus(rs.getInt("status"));
				order.setCustomer_name(rs.getString("customer_name"));
				order.setProduct_name(rs.getString("product_name"));
				order.setUpdated_at(rs.getString("updated_at"));
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public List<Orders> getOrderWithStatusOne(int offset, int noOfRecords, String filter_value){
		List<Orders> orders = new ArrayList<Orders>();
		String query = null;
		// Define today, yesterday, and last date in MySQL date format
		String today = "CURDATE()";
		String yesterday = "DATE_SUB(CURDATE(), INTERVAL 1 DAY)";
		String lastDate = "DATE_SUB(CURDATE(), INTERVAL 2 DAY)";
		System.out.println(filter_value);
		if(filter_value.equals("today")) {
			// Construct the query
			 query = "SELECT SQL_CALC_FOUND_ROWS orders.*, products.name AS product_name, customers.name AS customer_name "
			             + "FROM orders "
			             + "LEFT JOIN products ON orders.product_id = products.id "
			             + "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
			             + "LEFT JOIN customers ON customers.id = orders.customer_id "
			             + "WHERE orders.status = 1 "
			             + "AND (DATE(orders.updated_at) = " + today + ") "
			             + "ORDER BY updated_at DESC LIMIT " + offset + ", " + noOfRecords;
		}else if(filter_value.equals("all")) {
			// Construct the query
			 query = "SELECT SQL_CALC_FOUND_ROWS orders.*, products.name AS product_name, customers.name AS customer_name "
			             + "FROM orders "
			             + "LEFT JOIN products ON orders.product_id = products.id "
			             + "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
			             + "LEFT JOIN customers ON customers.id = orders.customer_id "
			             + "WHERE orders.status = 1 "
			             + "ORDER BY updated_at DESC LIMIT " + offset + ", " + noOfRecords;
		}else if(filter_value.equals("yesterday")) {
			// Construct the query
			 query = "SELECT SQL_CALC_FOUND_ROWS orders.*, products.name AS product_name, customers.name AS customer_name "
			             + "FROM orders "
			             + "LEFT JOIN products ON orders.product_id = products.id "
			             + "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
			             + "LEFT JOIN customers ON customers.id = orders.customer_id "
			             + "WHERE orders.status = 1 "
			             + "AND (DATE(orders.updated_at) <= " + yesterday + ") "
			             + "ORDER BY updated_at DESC LIMIT " + offset + ", " + noOfRecords;
		}else if(filter_value.equals("lastday")) {
			// Construct the query
			 query = "SELECT SQL_CALC_FOUND_ROWS orders.*, products.name AS product_name, customers.name AS customer_name "
			             + "FROM orders "
			             + "LEFT JOIN products ON orders.product_id = products.id "
			             + "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
			             + "LEFT JOIN customers ON customers.id = orders.customer_id "
			             + "WHERE orders.status = 1 "
			             + "AND (DATE(orders.updated_at) <= " + lastDate +") "
			             + "ORDER BY updated_at DESC LIMIT " + offset + ", " + noOfRecords;
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Orders order = new Orders();
				order.setId(rs.getInt("id"));
				order.setCustomer_id(rs.getInt("customer_id"));
				order.setOrder_code(rs.getString("order_code"));
				order.setPrice(rs.getInt("price"));
				order.setCount(rs.getInt("count"));
				order.setProduct_id(rs.getInt("product_id"));
				order.setShipping_id(rs.getInt("shipping_id"));
				order.setStatus(rs.getInt("status"));
				order.setCustomer_name(rs.getString("customer_name"));
				order.setProduct_name(rs.getString("product_name"));
				order.setUpdated_at(rs.getString("updated_at"));
				orders.add(order);
				
			}
			rs.close();
			rs = stmt.executeQuery("SELECT FOUND_ROWS()");
	        if(rs.next()) {
	        	 this.noOfRecords = rs.getInt(1);
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}
	
	// read order -> to track the order (for user)
//		public List<Order> getByUser(int id)
		public List<Orders> getByUser(int user_id){
			List<Orders> orders = new ArrayList<Orders>();
			String query = "SELECT * "
					+ "FROM orders "
					+ "LEFT JOIN products ON orders.product_id = products.id "
					+ "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
					+ "LEFT JOIN customers ON customers.id = orders.customer_id "
					+ "WHERE orders.customer_id = " + user_id;
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Orders order = new Orders();
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
		
		public List<Orders> getBySeller(int seller_id){
			List<Orders> orders = new ArrayList<Orders>();
			String query = "SELECT orders.*,products.name as product_name, customers.name as customer_name "
					+ "FROM orders "
					+ "LEFT JOIN products ON orders.product_id = products.id "
					+ "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
					+ "LEFT JOIN customers ON customers.id = orders.customer_id "
					+ "WHERE orders.status = 0 AND products.seller_id = " + seller_id;
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Orders order = new Orders();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setCount(rs.getInt("count"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					order.setCustomer_name(rs.getString("customer_name"));
					order.setProduct_name(rs.getString("product_name"));
					orders.add(order);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return orders;
		}
		
		public List<Orders> getBySellerWithPending(int seller_id){
			List<Orders> orders = new ArrayList<Orders>();
			String query = "SELECT orders.*,products.name as product_name, customers.name as customer_name "
					+ "FROM orders "
					+ "LEFT JOIN products ON orders.product_id = products.id "
					+ "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
					+ "LEFT JOIN customers ON customers.id = orders.customer_id "
					+ "WHERE orders.status = 0 AND products.seller_id = " + seller_id;
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Orders order = new Orders();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setCount(rs.getInt("count"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					order.setCustomer_name(rs.getString("customer_name"));
					order.setProduct_name(rs.getString("product_name"));
					order.setUpdated_at(rs.getString("updated_at"));
					orders.add(order);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return orders;
		}
		
		public List<Orders> getByOrderCodeWithPending(String order_code, int seller_id){
			List<Orders> orders = new ArrayList<Orders>();
			String query = "SELECT orders.*,products.name as product_name, customers.name as customer_name "
					+ "FROM orders "
					+ "LEFT JOIN products ON orders.product_id = products.id "
					+ "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
					+ "LEFT JOIN customers ON customers.id = orders.customer_id "
					+ "WHERE orders.status = 0 AND orders.order_code = ? AND products.seller_id = ?";
			try {
				pst = con.prepareStatement(query);
				pst.setString(1, order_code);
				pst.setInt(2, seller_id);
				rs = pst.executeQuery();
				while(rs.next()) {
					Orders order = new Orders();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setCount(rs.getInt("count"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					order.setCustomer_name(rs.getString("customer_name"));
					order.setProduct_name(rs.getString("product_name"));
					order.setUpdated_at(rs.getString("updated_at"));
					orders.add(order);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return orders;
		}
		
		public List<Orders> getByOrderCodeToShip(String order_code, int product_id){
			List<Orders> orders = new ArrayList<Orders>();
			String query = "SELECT orders.*,products.name as product_name, customers.name as customer_name "
					+ "FROM orders "
					+ "LEFT JOIN products ON orders.product_id = products.id "
					+ "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
					+ "LEFT JOIN customers ON customers.id = orders.customer_id "
					+ "WHERE orders.order_code = ? AND orders.product_id = ?";
			try {
				pst = con.prepareStatement(query);
				pst.setString(1, order_code);
				pst.setInt(2, product_id);
				rs = pst.executeQuery();
				while(rs.next()) {
					Orders order = new Orders();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setCount(rs.getInt("count"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					order.setCustomer_name(rs.getString("customer_name"));
					order.setProduct_name(rs.getString("product_name"));
					order.setUpdated_at(rs.getString("updated_at"));
					orders.add(order);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return orders;
		}
		
		public List<Orders> getByOrderCodeWithComplete(String order_code, int seller_id){
			List<Orders> orders = new ArrayList<Orders>();
			String query = "SELECT orders.*,products.name as product_name, customers.name as customer_name "
					+ "FROM orders "
					+ "LEFT JOIN products ON orders.product_id = products.id "
					+ "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
					+ "LEFT JOIN customers ON customers.id = orders.customer_id "
					+ "WHERE orders.order_code = ? AND products.seller_id = ?";
			try {
				pst = con.prepareStatement(query);
				pst.setString(1, order_code);
				pst.setInt(2, seller_id);
				rs = pst.executeQuery();
				while(rs.next()) {
					Orders order = new Orders();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setCount(rs.getInt("count"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					order.setCustomer_name(rs.getString("customer_name"));
					order.setProduct_name(rs.getString("product_name"));
					order.setUpdated_at(rs.getString("updated_at"));
					orders.add(order);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return orders;
		}
		
		// order cancel by user
		public boolean orderCancelByUser(String order_code, int product_id, int order_count) {
			boolean flag = false;
			// for orders code
			String query = "UPDATE orders SET status = -1, updated_at = current_timestamp WHERE order_code = ? AND product_id = ?";
			// for product 
			String reset_product_count = "UPDATE products SET count = count + "+ order_count +" WHERE id = " + product_id;
			try {
				pst = con.prepareStatement(query);
				// for product count
				Statement st = con.createStatement();
				
				pst.setString(1, order_code);
				pst.setInt(2, product_id);
				
				int deleted = pst.executeUpdate();
				int updated_product_count = st.executeUpdate(reset_product_count);
				if(deleted > 0 && updated_product_count > 0) flag = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
		
		public int getNoOfRecords() {
	        return noOfRecords;
	    }
		
		public List<Orders> getBySellerWithPaginationWithPending(int seller_id, int offset, int noOfRecords, String date){
			List<Orders> orders = new ArrayList<Orders>();
			String query = null;
			// Get the current date
			LocalDate currentDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String todayDate = currentDate.format(formatter);

			// Calculate the date for last day
			LocalDate lastDayDate = currentDate.minusDays(1);
			String lastDayDateString = lastDayDate.format(formatter);
			if (date.equals("all")) {
			    query = "SELECT SQL_CALC_FOUND_ROWS orders.*,products.name as product_name, customers.name as customer_name "
			            + "FROM orders "
			            + "LEFT JOIN products ON orders.product_id = products.id "
			            + "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
			            + "LEFT JOIN customers ON customers.id = orders.customer_id "
			            + "WHERE orders.status = 0 AND products.seller_id = " + seller_id  + " ORDER BY updated_at DESC limit "+ offset + ", " + noOfRecords;
			} else if (date.equals("today")) {
			    query = "SELECT SQL_CALC_FOUND_ROWS orders.*,products.name as product_name, customers.name as customer_name "
			            + "FROM orders "
			            + "LEFT JOIN products ON orders.product_id = products.id "
			            + "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
			            + "LEFT JOIN customers ON customers.id = orders.customer_id "
			            + "WHERE orders.status = 0 AND products.seller_id = " + seller_id  + " AND DATE(orders.updated_at) = '" + todayDate + "' ORDER BY updated_at DESC limit "+ offset + ", " + noOfRecords;
			} else if (date.equals("lastday")) {
			    query = "SELECT SQL_CALC_FOUND_ROWS orders.*,products.name as product_name, customers.name as customer_name "
			            + "FROM orders "
			            + "LEFT JOIN products ON orders.product_id = products.id "
			            + "LEFT JOIN addresses ON orders.shipping_id = addresses.id "
			            + "LEFT JOIN customers ON customers.id = orders.customer_id "
			            + "WHERE orders.status = 0 AND products.seller_id = " + seller_id  
			            + " AND DATE(orders.updated_at) <= DATE_SUB(CURDATE(), INTERVAL 1 DAY) "
			            + "ORDER BY updated_at DESC LIMIT " + offset + ", " + noOfRecords;
			}
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Orders order = new Orders();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setCount(rs.getInt("count"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					order.setCustomer_name(rs.getString("customer_name"));
					order.setProduct_name(rs.getString("product_name"));
					order.setUpdated_at(rs.getString("updated_at"));
					orders.add(order);
					
				}
				rs.close();
				rs = stmt.executeQuery("SELECT FOUND_ROWS()");
		        if(rs.next()) {
		        	 this.noOfRecords = rs.getInt(1);
		        }

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return orders;
		}

		// get by user id
		public List<Orders> getByUserWithPaginationWithAll(int customer_id, int offset, int noOfRecords, String filter_status){
			List<Orders> orders = new ArrayList<Orders>();
			String query = null;
			if(filter_status.equals("all")) {
				query = "SELECT SQL_CALC_FOUND_ROWS orders.*, orders.count as count, products.name as product_name, customers.name as customer_name, MIN(images.name) as image_name " +
						"FROM orders " +
						"LEFT JOIN products ON orders.product_id = products.id " +
						"LEFT JOIN addresses ON orders.shipping_id = addresses.id " +
						"LEFT JOIN customers ON customers.id = orders.customer_id " +
						"LEFT JOIN ( " +
						"    SELECT product_id, MIN(name) as name " +
						"    FROM images " +
						"    GROUP BY product_id " +
						") AS images ON images.product_id = products.id " +
						"WHERE orders.status IN (0, 1) AND orders.customer_id = " + customer_id +
						" GROUP BY orders.id, products.id " +
						"ORDER BY status " +
						"LIMIT "+ offset + ", " + noOfRecords;
			}else if(filter_status.equals("0")) {
				query = "SELECT SQL_CALC_FOUND_ROWS orders.*, orders.count as count, products.name as product_name, customers.name as customer_name, MIN(images.name) as image_name " +
						"FROM orders " +
						"LEFT JOIN products ON orders.product_id = products.id " +
						"LEFT JOIN addresses ON orders.shipping_id = addresses.id " +
						"LEFT JOIN customers ON customers.id = orders.customer_id " +
						"LEFT JOIN ( " +
						"    SELECT product_id, MIN(name) as name " +
						"    FROM images " +
						"    GROUP BY product_id " +
						") AS images ON images.product_id = products.id " +
						"WHERE orders.status IN (0) AND orders.customer_id = " + customer_id +
						" GROUP BY orders.id, products.id " +
						"ORDER BY status " +
						"LIMIT "+ offset + ", " + noOfRecords;
			}else if(filter_status.equals("1")) {
				query = "SELECT SQL_CALC_FOUND_ROWS orders.*, orders.count as count, products.name as product_name, customers.name as customer_name, MIN(images.name) as image_name " +
						"FROM orders " +
						"LEFT JOIN products ON orders.product_id = products.id " +
						"LEFT JOIN addresses ON orders.shipping_id = addresses.id " +
						"LEFT JOIN customers ON customers.id = orders.customer_id " +
						"LEFT JOIN ( " +
						"    SELECT product_id, MIN(name) as name " +
						"    FROM images " +
						"    GROUP BY product_id " +
						") AS images ON images.product_id = products.id " +
						"WHERE orders.status IN (1) AND orders.customer_id = " + customer_id +
						" GROUP BY orders.id, products.id " +
						"ORDER BY status " +
						"LIMIT "+ offset + ", " + noOfRecords;
			}
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Orders order = new Orders();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setCount(rs.getInt("count"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					order.setCustomer_name(rs.getString("customer_name"));
					order.setProduct_name(rs.getString("product_name"));
					order.setImage_name(rs.getString("image_name"));
					order.setUpdated_at(rs.getString("updated_at"));
					orders.add(order);
					
				}
				rs.close();
				rs = stmt.executeQuery("SELECT FOUND_ROWS()");
		        if(rs.next()) {
		        	 this.noOfRecords = rs.getInt(1);
		        }

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return orders;
		}
		
		// get confirmed order at by userid
		// get by user id
				public List<Orders> getByUserWithPaginationWithComplete(int customer_id, int offset, int noOfRecords, String date,String status){
					List<Orders> orders = new ArrayList<Orders>();
					String query = "SELECT SQL_CALC_FOUND_ROWS orders.*, orders.count as count, products.name as product_name, customers.name as customer_name, MIN(images.name) as image_name " +
				               "FROM orders " +
				               "LEFT JOIN products ON orders.product_id = products.id " +
				               "LEFT JOIN addresses ON orders.shipping_id = addresses.id " +
				               "LEFT JOIN customers ON customers.id = orders.customer_id " +
				               "LEFT JOIN ( " +
				               "    SELECT product_id, MIN(name) as name " +
				               "    FROM images " +
				               "    GROUP BY product_id " +
				               ") AS images ON images.product_id = products.id " +
				               "WHERE orders.customer_id = " + customer_id;

				if (date.equals("recently")) {
				    query += " AND orders.updated_at > DATE_SUB(NOW(), INTERVAL 1 DAY)";
				} else if (date.equals("past")) {
				    query += " AND orders.updated_at < DATE_SUB(NOW(), INTERVAL 2 DAY)";
				}

				if (status.equals("all")) {
				    query += " AND orders.status IN (-2, 2, -1)";
				} else if (status.equals("-1") || status.equals("2")) {
				    query += " AND orders.status = " + status;
				}

				query += " GROUP BY orders.id, products.id " +
				         " ORDER BY orders.updated_at DESC " +
				         " LIMIT " + offset + ", " + noOfRecords;

					try {
						stmt = con.createStatement();
						rs = stmt.executeQuery(query);
						while(rs.next()) {
							Orders order = new Orders();
							order.setId(rs.getInt("id"));
							order.setCustomer_id(rs.getInt("customer_id"));
							order.setOrder_code(rs.getString("order_code"));
							order.setPrice(rs.getInt("price"));
							order.setCount(rs.getInt("count"));
							order.setProduct_id(rs.getInt("product_id"));
							order.setShipping_id(rs.getInt("shipping_id"));
							order.setStatus(rs.getInt("status"));
							order.setCustomer_name(rs.getString("customer_name"));
							order.setProduct_name(rs.getString("product_name"));
							order.setImage_name(rs.getString("image_name"));
							order.setUpdated_at(rs.getString("updated_at"));
							orders.add(order);
							
						}
						rs.close();
						rs = stmt.executeQuery("SELECT FOUND_ROWS()");
				        if(rs.next()) {
				        	 this.noOfRecords = rs.getInt(1);
				        }

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return orders;
				}
		
				public List<Orders> getForChartPastWeek(int seller_id) {
				    List<Orders> orders = new ArrayList<>();
				    // Get the date for one week ago
				    LocalDate oneWeekAgo = LocalDate.now().minusWeeks(1);
				    // Format the date to fit in the SQL query
				    String formattedDate = oneWeekAgo.toString();

				    String query = "SELECT orders.updated_at as updated_at, COUNT(*) AS order_count " +
				                   "FROM orders " +
				                   "LEFT JOIN products ON products.id = orders.product_id " +
				                   "WHERE products.seller_id = " + seller_id + 
				                   " AND orders.updated_at >= '" + formattedDate + "' " +
				                   "GROUP BY orders.updated_at " +
				                   "ORDER BY orders.updated_at;";
				    try {
				        stmt = con.createStatement();
				        rs = stmt.executeQuery(query);
				        while(rs.next()) {
				            Orders order = new Orders();
				            // Assuming you have setters in your Orders class for the following properties
				            order.setUpdated_at(rs.getString("updated_at"));
				            order.setCount(rs.getInt("order_count"));
				            orders.add(order);
				        }
				    } catch (SQLException e) {
				        e.printStackTrace();
				    }
				    return orders;
				}

				
		
		// get order with status 1
		public List<Orders> getBySellerWithPaginationWithComplete(int seller_id, int offset, int noOfRecords, String filter){
			List<Orders> orders = new ArrayList<Orders>();
			String query = null;
			if (filter.equals("all")) {
		        query = "SELECT SQL_CALC_FOUND_ROWS orders.*, products.name as product_name, customers.name as customer_name " +
		                "FROM orders " +
		                "LEFT JOIN products ON orders.product_id = products.id " +
		                "LEFT JOIN addresses ON orders.shipping_id = addresses.id " +
		                "LEFT JOIN customers ON customers.id = orders.customer_id " +
		                "WHERE orders.status IN (1, 2, -1, -2) AND products.seller_id = " + seller_id + 
		                " ORDER BY updated_at DESC LIMIT " + offset + ", " + noOfRecords;
		    } else {
		        query = "SELECT SQL_CALC_FOUND_ROWS orders.*, products.name as product_name, customers.name as customer_name " +
		                "FROM orders " +
		                "LEFT JOIN products ON orders.product_id = products.id " +
		                "LEFT JOIN addresses ON orders.shipping_id = addresses.id " +
		                "LEFT JOIN customers ON customers.id = orders.customer_id " +
		                "WHERE orders.status = "+Integer.parseInt(filter)+" AND products.seller_id = " + seller_id + 
		                " ORDER BY updated_at DESC LIMIT " + offset + ", " + noOfRecords;
		    }
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					Orders order = new Orders();
					order.setId(rs.getInt("id"));
					order.setCustomer_id(rs.getInt("customer_id"));
					order.setOrder_code(rs.getString("order_code"));
					order.setPrice(rs.getInt("price"));
					order.setCount(rs.getInt("count"));
					order.setProduct_id(rs.getInt("product_id"));
					order.setShipping_id(rs.getInt("shipping_id"));
					order.setStatus(rs.getInt("status"));
					order.setCustomer_name(rs.getString("customer_name"));
					order.setProduct_name(rs.getString("product_name"));
					order.setUpdated_at(rs.getString("updated_at"));
					orders.add(order);
				}
				rs.close();
				rs = stmt.executeQuery("SELECT FOUND_ROWS()");
		        if(rs.next()) {
		        	 this.noOfRecords = rs.getInt(1);
		        }

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return orders;
		}
		
		
}
	
	
		
	

	
	
	
