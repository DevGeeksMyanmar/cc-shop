package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Message;


public class MessageDAO {

	Connection con = null;
	Statement stmt = null;
	PreparedStatement pst = null;
	ResultSet resultSet = null;
	
	public MessageDAO() throws ClassNotFoundException, SQLException{
		con = Config.config.getConnections();
	}
	
	public List<Message> get(){
		List<Message> messages = null; 
		Message mes = null; 
		try {
			messages = new ArrayList<Message>();
			String query = "SELECT * FROM message"; 
			stmt = con.createStatement(); // create statement
			resultSet = stmt.executeQuery(query); // execute that query and store that into resultset variable
			while(resultSet.next()) {  // until end
				mes = new Message();
				mes.setId(resultSet.getInt("id")); 
				mes.setName(resultSet.getString("name"));
				mes.setEmail(resultSet.getString("email"));
				mes.setPhone(resultSet.getString("phone"));
				mes.setMessage(resultSet.getString("message"));
				mes.setCreatedAt(resultSet.getString("created_at"));
				mes.setUpdatedAt(resultSet.getString("updated_at"));
				messages.add(mes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages; // return that list
	}
	
	public boolean create(Message mes) {

		boolean flag=false;
		
		String query ="insert into message(name,phone,email,message) values(?,?,?,?)";
		
		try {
			pst=con.prepareStatement(query);
			
			pst.setString(1,mes.getName());
			pst.setString(2,mes.getPhone());
			pst.setString(3,mes.getEmail());
			pst.setString(4,mes.getMessage());
			
			//insert ဆို
			int inserted=pst.executeUpdate();
			if(inserted>0)flag=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return flag;
		
	}

}
