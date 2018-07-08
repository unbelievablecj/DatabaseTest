package cj.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String URL="jdbc:mysql://115.159.198.216:3306/hotel?user=root&password=chenjing5114"
            + "&useUnicode=true&characterEncoding=UTF8&useSSL=true";
	private  Connection conn=null;
	
	public Connection getConnection(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			conn=DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
