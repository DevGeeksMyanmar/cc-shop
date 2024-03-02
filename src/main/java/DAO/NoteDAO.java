package DAO;

import java.sql.*;
import java.util.*;
import Models.Note;

public class NoteDAO {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public NoteDAO() throws ClassNotFoundException, SQLException {
		con = Config.config.getConnections();
	}
	
	// create the note
	public boolean create(Note note) {
		boolean flag = false;
		String query = "INSERT INTO notes (order_code, customer_id, note) VALUES (?,?,?)";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, note.getOrder_code());
			pst.setInt(2, note.getCustomer_id());
			pst.setString(3, note.getText());
			int inserted = pst.executeUpdate();
			if(inserted > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// get by order_code
	public Note getByOrderCode(String order_code) {
		Note note = null;
		String query = "SELECT * FROM notes WHERE order_code = ?";
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, order_code);
			rs = pst.executeQuery();
			if(rs.next()) {
				note = new Note();
				note.setId(rs.getInt("id"));
				note.setCustomer_id(rs.getInt("customer_id"));
				note.setText(rs.getString("note"));
				note.setUpdated_at(rs.getString("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return note;
	}
	
	
}
