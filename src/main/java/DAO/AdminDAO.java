package DAO;

import java.util.*;
import java.sql.*;
import Models.Admin;
import Models.Business;

public class AdminDAO {
	
	Connection con = null; // declare connection
	ResultSet resultset = null; // declare result set
	Statement statement = null; // declare statement
	PreparedStatement stmt = null; // declare prepare statement
	private int noOfRecords;
	
	// admin data access object model ( constructor work first)
	public AdminDAO() throws ClassNotFoundException, SQLException{
		con = Config.config.getConnections();
	}
	
	// get all admins
	public List<Admin> get(){
		List<Admin> admins = null;  // create empty admin list to store admins
		Admin admin = null; // create admin object which is from model
		// surround with try and catch block 
		try {
			admins = new ArrayList<Admin>(); // create ArrayList admin object
			String query = "SELECT * FROM admin"; // prepare query
			statement = con.createStatement(); // create statement
			resultset = statement.executeQuery(query); // execute that query and store that into resultset variable
			while(resultset.next()) {  // until end
				admin = new Admin(); // create admin object
				admin.setId(resultset.getInt("id"));  // set admin id from admin table's data
				admin.setName(resultset.getString("name"));
				admin.setEmail(resultset.getString("email"));
				admin.setPhone(resultset.getString("phone"));
				admin.setImage(resultset.getString("image"));
				admins.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admins; // return that list
	}
	
	// get all admin
			public List<Admin> getAll(int offset, int noOfRecords) throws SQLException{
				List<Admin> admins = new ArrayList<Admin>();  // create empty admin list to store admins
				Admin admin = null; // create admin object which is from model
				String query = "select SQL_CALC_FOUND_ROWS * from admin ORDER BY updated_at DESC limit " + offset + ", " + noOfRecords;
				statement = con.createStatement(); // create statement
				resultset = statement.executeQuery(query); // execute that query and store that into resultset variable
				while(resultset.next()) {  // until end
					admin = new Admin(); // create admin object
					admin.setId(resultset.getInt("id"));  // set admin id from admin table's data
					admin.setName(resultset.getString("name"));
					admin.setEmail(resultset.getString("email"));
					admin.setPhone(resultset.getString("phone"));
					admin.setImage(resultset.getString("image"));
					admins.add(admin);
				}
				resultset.close();
				resultset = statement.executeQuery("SELECT FOUND_ROWS()");
		        if(resultset.next()) {
		        	 this.noOfRecords = resultset.getInt(1);
		        }
				return admins; // return that list
			}
			
			// get number of records
			public int getNoOfRecords() {
		        return noOfRecords;
		    }
	
	// get by id
	public Admin getById(int id) {
		Admin admin = new Admin();
		try {
			String query = "SELECT * FROM admin WHERE id = " + id;
			statement = con.createStatement();
			resultset = statement.executeQuery(query);
			// resultset to admin object
			if(resultset.next()) {
				admin.setId(resultset.getInt("id"));
				admin.setName(resultset.getString("name"));
				admin.setEmail(resultset.getString("email"));
				admin.setPhone(resultset.getString("phone"));
				admin.setImage(resultset.getString("image"));
				admin.setPassword(resultset.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin; // return object admin
	}
	
	// get by name
	public Admin getByName(String name) throws SQLException {
		Admin admin = new Admin();
		String query = "SELECT * FROM admin WHERE name = ?" ;
		stmt = con.prepareStatement(query);
		stmt.setString(1, name);
		resultset = stmt.executeQuery();
		if(resultset.next()) {
			admin.setId(resultset.getInt("id"));
			admin.setName(resultset.getString("name"));
			admin.setEmail(resultset.getString("email"));
			admin.setPhone(resultset.getString("phone"));
			admin.setImage(resultset.getString("image"));
		}
		return admin;
	}
	
	// create admin
	public boolean create(Admin admin) throws SQLException {
		boolean flag = false;
		String query = "INSERT INTO admin (name,email,password,phone,image)"
				+ "VALUES (?,?,?,?,?)";
		stmt = con.prepareStatement(query);
		stmt.setString(1, admin.getName());
		stmt.setString(2, admin.getEmail());
		stmt.setString(3, admin.getPassword());
		stmt.setString(4, admin.getPhone());
		stmt.setString(5, admin.getImage());
		int rowInserted = stmt.executeUpdate();
		if(rowInserted > 0) flag =true;
		return flag;
	}
	
	// get by email
	public Admin getAdminByEmail(String email) throws SQLException {
		Admin admin = null;
		String query = "SELECT * FROM admin WHERE email=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, email);
		resultset = stmt.executeQuery();
		while(resultset.next()) {
			admin = new Admin();
			admin.setEmail(resultset.getString("email"));
			admin.setId(resultset.getInt("id"));
			admin.setName(resultset.getString("name"));
			admin.setEmail(resultset.getString("email"));
			admin.setPhone(resultset.getString("phone"));
			admin.setImage(resultset.getString("image"));
			admin.setPassword(resultset.getString("password"));
		}
		return admin;
	}
	
	// delete admin
	public boolean delete(int id) throws SQLException {
		boolean flag = false;
		String query = "DELETE FROM admin WHERE id = " + id;
		statement = con.createStatement();
		int deletedRow = statement.executeUpdate(query);
		if(deletedRow > 0) flag = true;
		return flag;
	}
	
	// update admin
	public boolean update(Admin admin) throws SQLException {
		boolean flag = false;
		String query = "UPDATE admin SET name=? , email=?, phone=?, updated_at = current_timestamp WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, admin.getName());
		stmt.setString(2, admin.getEmail());
		stmt.setString(3, admin.getPhone());
		stmt.setInt(4, admin.getId());
		int updatedRow = stmt.executeUpdate();
		if(updatedRow > 0) flag = true;
		return flag;
	}
	
	// update password
	public boolean updatePassword(String password, int id) throws SQLException {
		boolean flag = false;
		String query = "UPDATE admin SET password=?, updated_at = current_timestamp WHERE id=?";
		stmt = con.prepareStatement(query);
		stmt.setString(1, password);
		stmt.setInt(2, id);
		int updatedRow = stmt.executeUpdate();
		if(updatedRow > 0) flag = true;
		return flag;
	}
	
	// update image
		public boolean updateImage(Admin admin) throws SQLException {
			boolean flag = false;
			String query = "UPDATE admin SET image=?, updated_at = current_timestamp WHERE id=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, admin.getImage());
			stmt.setInt(2, admin.getId());
			int updatedRow = stmt.executeUpdate();
			if(updatedRow > 0) flag = true;
			return flag;
		}
	
	// testing method
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
			AdminDAO dao = new AdminDAO();
	
			// testing getting by name
			Admin adminbyname = dao.getByName("dd");
			System.out.println(adminbyname.getEmail());
			
			// testing inserting
//			Admin newAdmin = new Admin();
//			newAdmin.setName("Mg Mg");
//			newAdmin.setEmail("mgmg@gmail.com");
//			newAdmin.setPassword("mgmg");
//			newAdmin.setPhone("12345678");
//			newAdmin.setImage("mgmg.img");
//			boolean newAdminFlag = dao.create(newAdmin);
//			if(newAdminFlag) {
//				System.out.println("Created new admin successfully");
//			}
			
			// testing delete admin
//			boolean deleteAdminFlag = dao.delete(1);
//			if(deleteAdminFlag) System.out.println("deleted success");
			
			// testing update admin
			Admin updatedAdmin = new Admin();
			updatedAdmin.setId(2);
			updatedAdmin.setName("mgba");
			updatedAdmin.setEmail("mgba@gmail.com");
			updatedAdmin.setPhone("12222222");
			updatedAdmin.setImage("mgba.img");
			boolean updateAdminFlag = dao.update(updatedAdmin);
			if(updateAdminFlag) System.out.println("update success");
			
			// testing get all admins
			List<Admin> all = dao.get();
			for(Admin admin : all) {
				System.out.println(admin.getName());
				System.out.println(admin.getEmail());
				System.out.println(admin.getPhone());
			}
			
	}
	

}
