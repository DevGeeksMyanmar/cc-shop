package DAO;

import java.sql.*;
import java.util.*;

import org.apache.tomcat.jdbc.pool.interceptor.AbstractCreateStatementInterceptor;

import Models.*;
import Models.Record;

public class SellerDAO {
	
	Connection con = null;
	Statement statement = null;
	PreparedStatement stmt = null;
	ResultSet resultset = null;
	private int noOfRecords;
	
	// default constructor
	public SellerDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	
	// get all customer
	public List<Seller> get() {
		List<Seller> sellers = new ArrayList<Seller>();
		String query = "SELECT sellers.*, businesses.name as bname FROM sellers LEFT JOIN businesses ON sellers.business_id = businesses.id";
		try {
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Seller seller = new Seller();
				seller.setId(resultSet.getInt("id"));
				seller.setName(resultSet.getString("name"));
				seller.setEmail(resultSet.getString("email"));
				seller.setPhone(resultSet.getString("phone"));
				seller.setImage(resultSet.getString("image"));
				seller.setAddress(resultSet.getString("address"));
				seller.setCompany(resultSet.getString("company"));
				seller.setBusiness_id(resultSet.getInt("business_id"));
				seller.setBname(resultSet.getString("bname"));
				sellers.add(seller);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sellers;
	}
	
	// get all seller
	public List<Seller> getAll(int offset, int noOfRecords) throws SQLException{
		List<Seller> sellers = new ArrayList<Seller>();  // create empty admin list to store admins
		Seller seller = null; // create admin object which is from model
		String query = "select SQL_CALC_FOUND_ROWS sellers.*, businesses.name as bname FROM sellers LEFT JOIN businesses ON sellers.business_id = businesses.id ORDER BY updated_at DESC limit " + offset + ", " + noOfRecords;
		statement = con.createStatement();
		resultset = statement.executeQuery(query);
		while(resultset.next()) {
			seller = new Seller();
			seller.setId(resultset.getInt("id"));
			seller.setName(resultset.getString("name"));
			seller.setEmail(resultset.getString("email"));
			seller.setPhone(resultset.getString("phone"));
			seller.setImage(resultset.getString("image"));
			seller.setAddress(resultset.getString("address"));
			seller.setCompany(resultset.getString("company"));
			seller.setBusiness_id(resultset.getInt("business_id"));
			seller.setBname(resultset.getString("bname"));
			sellers.add(seller);
		}
		resultset.close();
		resultset = statement.executeQuery("SELECT FOUND_ROWS()");
        if(resultset.next()) {
        	 this.noOfRecords = resultset.getInt(1);
        }
		return sellers; // return that list
	}
	
	// get number of records
		public int getNoOfRecords() {
	        return noOfRecords;
	    }
	
	// get by customer id
	public Seller getById(int id) throws SQLException {
		Seller seller = new Seller();
		String query = "SELECT sellers.*, businesses.name as business, MAX(products.rating) as rating FROM sellers "
				+ "LEFT JOIN businesses ON sellers.business_id = businesses.id "
				+ "LEFT JOIN products ON sellers.id = products.seller_id "
				+ "WHERE sellers.id=" + id;
		statement = con.createStatement();
		resultset = statement.executeQuery(query);
		if(resultset.next()) {
			seller.setId(resultset.getInt("id"));
			seller.setName(resultset.getString("name"));
			seller.setEmail(resultset.getString("email"));
			seller.setPhone(resultset.getString("phone"));
			seller.setAddress(resultset.getString("address"));
			seller.setCompany(resultset.getString("company"));
			seller.setBname(resultset.getString("business"));
			seller.setImage(resultset.getString("image"));
			seller.setRating(resultset.getInt("rating"));
		}
		return seller;
	}
	
	// get by email
		public Seller getSellerByEmail(String email) throws SQLException {
			Seller seller = null;
			String query = "SELECT * FROM sellers WHERE email=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, email);
			resultset = stmt.executeQuery();
			while(resultset.next()) {
				seller = new Seller();
				seller.setId(resultset.getInt("id"));
				seller.setName(resultset.getString("name"));
				seller.setEmail(resultset.getString("email"));
				seller.setPhone(resultset.getString("phone"));
				seller.setImage(resultset.getString("image"));
				seller.setPassword(resultset.getString("password"));
				seller.setCompany(resultset.getString("company"));
				seller.setBusiness_id(resultset.getInt("business_id"));
			}
			return seller;
		}
	
	// get by customer name
	public Seller getByName(String name) throws SQLException {
		Seller seller = new Seller();
		String query = "SELECT * FROM sellers WHERE name = ?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, name);
		resultset = stmt.executeQuery();
		if(resultset.next()) {
			seller.setId(resultset.getInt("id"));
			seller.setName(resultset.getString("name"));
			seller.setEmail(resultset.getString("email"));
			seller.setPhone(resultset.getString("phone"));
			seller.setAddress(resultset.getString("address"));
			seller.setCompany(resultset.getString("company"));
			seller.setBusiness_id(resultset.getInt("id"));
			seller.setBusiness_id(resultset.getInt("business_id"));
		}
		return seller;
	}
	
	// create new customer
	public boolean create(Seller seller) throws SQLException {
	    boolean flag = false;
	    String query = "INSERT INTO sellers (name, email, password, phone, address, image, company, business_id) VALUES (?,?,?,?,?,?,?,?)";
	    stmt = con.prepareStatement(query);
	    stmt.setString(1, seller.getName());
	    stmt.setString(2, seller.getEmail());
	    stmt.setString(3, seller.getPassword());
	    stmt.setString(4, seller.getPhone());
	    stmt.setString(5, seller.getAddress());
	    stmt.setString(6, seller.getImage());
	    stmt.setString(7, seller.getCompany());
	    stmt.setInt(8, seller.getBusiness_id()); // Adjusted to match the SQL query
	    int insertedRow = stmt.executeUpdate();
	    if(insertedRow > 0) flag = true;
	    return flag;
	}

	
	// delete customer
	public boolean delete(int id) throws SQLException {
		boolean flag = false;
		String query = "DELETE FROM sellers WHERE id = " +id;
		statement = con.createStatement();
		int deletedRow = statement.executeUpdate(query);
		if(deletedRow > 0) flag = true;
		return flag;
	}
	
	// update customer
	public boolean update(Seller seller) throws SQLException {
		boolean flag = false;
		String query = "UPDATE sellers SET name=?, email=?, phone=?, address=?, company=?, business_id=? WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, seller.getName());
		stmt.setString(2, seller.getEmail());
		stmt.setString(3, seller.getPhone());
		stmt.setString(4, seller.getAddress());
		stmt.setString(5, seller.getCompany());
		stmt.setInt(6, seller.getBusiness_id());
		stmt.setInt(7, seller.getId());
		int updatedRow = stmt.executeUpdate();
		if(updatedRow > 0) flag = true;
		return flag;
	}
	
	// update image
	public boolean updateImage(Seller seller) throws SQLException {
		boolean flag = false;
		String query = "UPDATE sellers SET image=? WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, seller.getImage());
		stmt.setInt(2, seller.getId());
		int updatedRow = stmt.executeUpdate();
		if(updatedRow > 0) flag = true;
		return flag;
	}
	
	// testing method
	public static void main(String args[]) throws SQLException, ClassNotFoundException {
		// create new instance of boject Customer DAO
		SellerDAO dao = new SellerDAO();
		Seller seller = new Seller();
		
		// testing get customer by id
//		customer = customerDAO.getById(2);
//		System.out.println(customer.getName());
		
		// testing customer delete
//		boolean deleteFlag = customerDAO.delete(1);
//		if(deleteFlag) System.out.println("Deleted success");
		
		// testing update customer
//		Customer newcustomer = new Customer();
//		newcustomer.setName("Aung Aung");
//		newcustomer.setEmail("aung@gmail.com");
//		newcustomer.setPhone("111222333");
//		newcustomer.setAddress("Pyay");
//		newcustomer.setImage("aung.img");
//		newcustomer.setId(2);
//		boolean updateFlag = customerDAO.update(newcustomer);
//		if (updateFlag) System.out.println("updated success");
		
		// testing get customer by name
//		seller = dao.getByName("Mg Mg");
//		System.out.println(seller.getEmail());
		
//		 testing create new seller
//		seller.setName("Ma Ma");
//		seller.setEmail("mama@gmail.com");
//		seller.setPassword("mama123");
//		seller.setPhone("123456");
//		seller.setAddress("Yangon");
//		seller.setImage("mama.jpg");
//		boolean createFlag = dao.create(seller);
//		if(createFlag) System.out.println("created success");
		
//		 testing get all seller
		List<Seller> sellers = dao.get();
		for(Seller user : sellers) {
			System.out.println("Name " + user.getName());
			System.out.println("Email " + user.getEmail());
			System.out.println("Address " + user.getAddress());
			System.out.println("Image " + user.getImage());
			System.out.println("--------------------------");
		}
	}
	
//	get all order
	public List<Order> getAllOrder(){
		List<Order> orders = new ArrayList<Order>();
		String query = "SELECT * From Order";
		try {
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Order order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setOrder_code(resultSet.getString("order_code"));
				order.setPrice(resultSet.getInt("price"));
				order.setCount(resultSet.getInt("count"));
				order.setStatus(resultSet.getInt("status"));
				order.setProduct_id(resultSet.getInt("produt_id"));
				order.setCustomer_id(resultSet.getInt("customer_id"));
				order.setShipping_id(resultSet.getInt("shipping_id"));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}
//get all records
	public List<Record> getAllRecords(){
		List<Record> records = new ArrayList<Record>();
		String query = "SELECT * FROM records";
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				Record record = new Record();
				record.setId(rs.getInt("id"));
				record.setPrice(rs.getInt("price"));
				record.setOrder_code(rs.getString("order_code"));
				record.setCount(rs.getInt("count"));
				record.setProduct_id(rs.getInt("product_id"));
				record.setCustomer_id(rs.getInt("customer_id"));
				record.setShipping_id(rs.getInt("shipping_id"));
				record.setSeller_id(rs.getInt("seller_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	}
	
}
