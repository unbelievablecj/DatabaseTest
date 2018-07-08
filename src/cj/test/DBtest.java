package cj.test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import cj.db.DBUtil;
public class DBtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			DBUtil db=new DBUtil();
			Connection conn=db.getConnection();
			if(conn==null){
				System.out.println("null");
			}
			Statement stmt=conn.createStatement();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from rooms");
			while(rs.next()){
				String type =rs.getString(1);
				System.out.println(type);
				
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}

}
