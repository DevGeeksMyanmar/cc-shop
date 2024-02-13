package DAO;

import java.sql.*;
import java.util.*;
import Models.*;

public class CustomerDAO {
	
	Connection con = null;
	Statement statement = null;
	PreparedStatement stmt = null;
	ResultSet resultset = null;
	
	// default constructor
	public CustomerDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	
	// get all customer
	public List<Customer> get() throws SQLException{
		List<Customer> customers = new ArrayList<Customer>();
		String query = "SELECT * FROM customers";
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
		return customers;
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
		}
		return customer;
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
				Customer customer = new Customer();
				String query = "SELECT * FROM customers WHERE email=?";
				stmt = con.prepareStatement(query);
				stmt.setString(1, email);
				resultset = stmt.executeQuery();
				while(resultset.next()) {
					customer.setId(resultset.getInt("id"));
					customer.setName(resultset.getString("name"));
					customer.setEmail(resultset.getString("email"));
					customer.setPhone(resultset.getString("phone"));
					customer.setImage(resultset.getString("image"));
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
		String query = "UPDATE customers SET name=?, email=?, phone=?, address=?, image=? WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, customer.getName());
		stmt.setString(2, customer.getEmail());
		stmt.setString(3, customer.getPhone());
		stmt.setString(4, customer.getAddress());
		stmt.setString(5, customer.getImage());
		stmt.setInt(6, customer.getId());
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
