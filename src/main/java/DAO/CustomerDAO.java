package DAO;

import java.sql.*;
import java.util.*;
import Models.*;

public class CustomerDAO {
	
	Connection con = null;
	Statement statement = null;
	PreparedStatement stmt = null;
	ResultSet resultset = null;
	private int noOfRecords;
	
	// default constructor
	public CustomerDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	
	// get all customer
	public List<Customer> get() {
		List<Customer> customers = new ArrayList<Customer>();
		String query = "SELECT * FROM customers";
		try {
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getInt("id"));
				customer.setName(resultSet.getString("name"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPhone(resultSet.getString("phone"));
				customer.setImage(resultSet.getString("image"));
				customer.setAddress(resultSet.getString("address"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return customers;
	}
	
	// get all customer
				public List<Customer> getAll(int offset, int noOfRecords) throws SQLException{
					List<Customer> customers = new ArrayList<Customer>();  // create empty admin list to store admins
					
					String query = "select SQL_CALC_FOUND_ROWS * from customers ORDER BY updated_at DESC limit " + offset + ", " + noOfRecords;
					statement = con.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					while(resultSet.next()) {
						Customer customer = new Customer();
						customer.setId(resultSet.getInt("id"));
						customer.setName(resultSet.getString("name"));
						customer.setEmail(resultSet.getString("email"));
						customer.setPhone(resultSet.getString("phone"));
						customer.setImage(resultSet.getString("image"));
						customer.setAddress(resultSet.getString("address"));
						customers.add(customer);
					}
					resultSet.close();
					resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
			        if(resultSet.next()) {
			        	 this.noOfRecords = resultSet.getInt(1);
			        }
					return customers; // return that list
				}
				
				// get number of records
				public int getNoOfRecords() {
			        return noOfRecords;
			    }
	
	// get by customer id
	public Customer getById(int id) throws SQLException {
		Customer customer = new Customer();
		String query = "SELECT * FROM customers WHERE id=" + id;
		statement = con.createStatement();
		resultset = statement.executeQuery(query);
		if(resultset.next()) {
			customer.setId(resultset.getInt("id"));
			customer.setName(resultset.getString("name"));
			customer.setEmail(resultset.getString("email"));
			customer.setPhone(resultset.getString("phone"));
			customer.setAddress(resultset.getString("address"));
			customer.setImage(resultset.getString("image"));
			customer.setPassword(resultset.getString("password"));
		}
		return customer;
	}
	
	// update password
		public boolean updatePassword(String password, int id) throws SQLException {
			boolean flag = false;
			String query = "UPDATE customers SET password=? WHERE id=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, password);
			stmt.setInt(2, id);
			int updatedRow = stmt.executeUpdate();
			if(updatedRow > 0) flag = true;
			return flag;
		}
	
	// get by customer name
	public Customer getByName(String name) throws SQLException {
		Customer customer = new Customer();
		String query = "SELECT * FROM customers WHERE name = ?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, name);
		resultset = stmt.executeQuery();
		if(resultset.next()) {
			customer.setId(resultset.getInt("id"));
			customer.setName(resultset.getString("name"));
			customer.setEmail(resultset.getString("email"));
			customer.setPhone(resultset.getString("phone"));
			customer.setAddress(resultset.getString("address"));
		}
		return customer;
	}
	
	// get customer email by id
	public String getEamilByID(int id) {
		String email = null;
		String query = "SELECT * FROM customers WHERE id = " + id;
		try {
			statement = con.createStatement();
			resultset = statement.executeQuery(query);
			if(resultset.next()) {
				email = resultset.getString("email");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}
	
	// create new customer
	public boolean create(Customer customer) throws SQLException {
		boolean flag = false;
		String query = "INSERT INTO customers (name, email, password, phone, address, image)"
				+ "VALUES (?,?,?,?,?,?)";
		stmt = con.prepareStatement(query);
		stmt.setString(1, customer.getName());
		stmt.setString(2, customer.getEmail());
		stmt.setString(3, customer.getPassword());
		stmt.setString(4, customer.getPhone());
		stmt.setString(5, customer.getAddress());
		stmt.setString(6, customer.getImage());
		int insertedRow = stmt.executeUpdate();
		if(insertedRow > 0) flag = true;
		return flag;
	}
	
	// get by email
			public Customer getUserByEmail(String email) throws SQLException {
				Customer customer = null;
				String query = "SELECT * FROM customers WHERE email=?";
				stmt = con.prepareStatement(query);
				stmt.setString(1, email);
				resultset = stmt.executeQuery();
				while(resultset.next()) {
					customer = new Customer();
					customer.setId(resultset.getInt("id"));
					customer.setName(resultset.getString("name"));
					customer.setEmail(resultset.getString("email"));
					customer.setPhone(resultset.getString("phone"));
					customer.setImage(resultset.getString("image"));
					customer.setAddress(resultset.getString("address"));;
					customer.setPassword(resultset.getString("password"));
				}
				return customer;
			}
	
	// delete customer
	public boolean delete(int id) throws SQLException {
		boolean flag = false;
		String query = "DELETE FROM customers WHERE id = " +id;
		statement = con.createStatement();
		int deletedRow = statement.executeUpdate(query);
		if(deletedRow > 0) flag = true;
		return flag;
	}
	
	// update customer
	public boolean update(Customer customer) throws SQLException {
		boolean flag = false;
		String query = "UPDATE customers SET name=?, email=?, phone=?, address=?, updated_at = current_timestamp WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, customer.getName());
		stmt.setString(2, customer.getEmail());
		stmt.setString(3, customer.getPhone());
		stmt.setString(4, customer.getAddress());
		stmt.setInt(5, customer.getId());
		int updatedRow = stmt.executeUpdate();
		if(updatedRow > 0) flag = true;
		return flag;
	}
	
	public boolean updateImage(Customer customer) throws SQLException {
		boolean flag = false;
		String query = "UPDATE customers SET image=? WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, customer.getImage());
		stmt.setInt(2, customer.getId());
		int updatedRow = stmt.executeUpdate();
		if(updatedRow > 0) flag = true;
		return flag;
	}
	
	// testing method
	public static void main(String args[]) throws SQLException, ClassNotFoundException {
		// create new instance of boject Customer DAO
		CustomerDAO customerDAO = new CustomerDAO();
		Customer customer = new Customer();
		
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
//		customer = customerDAO.getByName("Mg Mg");
//		System.out.println(customer.getEmail());
		
		// testing create new customer
//		customer.setName("Mg Mg");
//		customer.setEmail("mgmg@gmail.com");
//		customer.setPassword("mgmg123");
//		customer.setPhone("123456");
//		customer.setAddress("Yangon");
//		customer.setImage("mgmg.jpg");
//		boolean createFlag = customerDAO.create(customer);
//		if(createFlag) System.out.println("created success");
		
		// testing get all customers
		List<Customer> customers = customerDAO.get();
		for(Customer user : customers) {
			System.out.println("Name " + user.getName());
			System.out.println("Email " + user.getEmail());
			System.out.println("Address " + user.getAddress());
			System.out.println("Image " + user.getImage());
			System.out.println("--------------------------");
		}
	}	
}
