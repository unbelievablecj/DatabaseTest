package cj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cj.db.DBUtil;

public class UserDao {
	public static boolean login(String username,String password) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="SELECT * FROM operators WHERE username=? AND password=?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet rs=stmt.executeQuery();
		String n="";
		while(rs.next()){
			n=rs.getString(1);
		}
		conn.close();
		if(n.equals(username)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void register(String username,String password,String name,String id,String phone,
			int sex) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="INSERT INTO operators VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
		stmt.setString(3, name);
		stmt.setString(4, id);
		stmt.setString(5, phone);
		stmt.setInt(6, sex);
		stmt.execute();
		conn.close();
	}
	
	public static void changePassword(String user,String oldpwd,String newpwd) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="SELECT password FROM operators WHERE username=?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, user);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
			oldpwd.equals(rs.getString(1));
			String sql2="UPDATE operators SET password=? WHERE username=?";
			PreparedStatement st=conn.prepareStatement(sql2);
			st.setString(1, newpwd);
			st.setString(2, user);
			st.executeUpdate();
		}
		conn.close();
	}
}
