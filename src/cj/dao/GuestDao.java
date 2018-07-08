package cj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cj.db.DBUtil;
import cj.model.Guest;

public class GuestDao {
	public static void addGuest(String id,String name,int sex,String phone ) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="INSERT INTO guesses VALUES (?,?,?,?)";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.setString(2, name);
		stmt.setInt(3, sex);
		stmt.setString(4, phone);
		stmt.execute();
		conn.close();
	}
	
	public static void deleteGuest(String id) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="DELETE FROM guesses WHERE id=? ";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.execute();
		conn.close();
	}
	
	public static Guest searchGuest(String id) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="SELECT * FROM guesses WHERE id=?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs=stmt.executeQuery();
		Guest g = new Guest();
		while(rs.next()){
			g.setId(id);
			g.setName(rs.getString(2));
			g.setPhone(rs.getString(4));
			g.setSex(rs.getInt(3));
		}
		conn.close();
		return g;
	}
}
