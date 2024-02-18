package Config;

import java.sql.*;

public class config {
	
	public static Connection getConnections() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/shop_dot_com", "root", "20040224");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
}
