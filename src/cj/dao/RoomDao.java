package cj.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cj.db.*;
import cj.model.Room;
import cj.model.order;
public class RoomDao {
	public static void addRoom(String id,String type,double price) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="insert into rooms values (?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,type);
		ptmt.setDouble(2, price);
		ptmt.setString(3, id);
		ptmt.execute();
		conn.close();
	}
	
	public static double bookRoom(String opname,String roomid,Date start,Date end,String id) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		DBUtil db2=new DBUtil();
		Connection c1=db2.getConnection();
		String select="SELECT starttime,endtime FROM orders WHERE room=? AND isdone=0";
		PreparedStatement sq=c1.prepareStatement(select);
		sq.setString(1, roomid);
		ResultSet rsq=sq.executeQuery();
		boolean have=true;
		while(rsq.next()){
			Date d1=new Date(rsq.getDate(1).getTime());
			Date d2=new Date(rsq.getDate(2).getTime());
			if((d1.getTime()-start.getTime()<0 && d2.getTime()-start.getTime()<=0)||
					(d1.getTime()-end.getTime()>=0&&d2.getTime()-end.getTime()>0)){
			}
			else{
				have=false;
				break;
			}
		}
		c1.close();
		if(!have){
			conn.close();
			return -1;
		}
		
		String sql="INSERT INTO orders (opname,starttime,endtime,guest,room,price,"
				+ "isdone) values (?,?,?,?,?,?,?)";
		java.sql.Date s=new java.sql.Date(start.getTime());
		java.sql.Date e=new java.sql.Date(end.getTime());
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,opname);
		ptmt.setDate(2, s);
		ptmt.setDate(3, e);
		ptmt.setString(4, id);
		ptmt.setString(5, roomid);
		double price=0;
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT price FROM rooms WHERE roomid="+roomid);
		while(rs.next()){
			price=rs.getDouble(1);
		}
		long days=(end.getTime()-start.getTime())/(1000*3600*24);
		price=price*days;
		ptmt.setDouble(6, price);
		ptmt.setInt(7, 0);
		ptmt.execute();
		conn.close();
		return price;
	}
	
	public static Room searchRoomId(String id) throws SQLException{
		Room r=new Room();
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="SELECT * FROM rooms WHERE roomid=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1,id);
		ResultSet rs=ptmt.executeQuery();
		if(rs.next()){
			r.setPrice(rs.getDouble(2));
			r.setRoomid(id);
			r.setRoomtype(rs.getString(1));
		}
		rs.close();
		conn.close();
		DBUtil db2=new DBUtil();
		Connection c2=db2.getConnection();
		String sql2="SELECT * FROM orders WHERE room=? AND isdone=0";
		PreparedStatement pt=c2.prepareStatement(sql2);
		pt.setString(1, id);
		ResultSet rs2=pt.executeQuery();
		List<order> orders=new LinkedList<order>();
		
		while(rs2.next()){
			order o=new order();
			
			o.setOpname(rs2.getString(1));
			o.setStart(new Date(rs2.getDate(2).getTime()));
			o.setEnd(new Date(rs2.getDate(3).getTime()));
			o.setGuest(rs2.getString(4));
			o.setRoom(rs2.getString(5));
			o.setPrice(rs2.getDouble(6));
			o.setIsdone(0);
			o.setOrderid(rs2.getInt(8));
			orders.add(o);
		}
		r.setOrders(orders);
		rs2.close();
		c2.close();
		return r;
	}
	
	public static void checkOut(int orderid) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql ="UPDATE orders SET isdone=1 WHERE orderid=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setInt(1, orderid);
		ptmt.execute();
		conn.close();
		
	}
	
	public static List<String> searchRoomType(String type) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql ="SELECT roomid FROM rooms WHERE roomtype=?";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, type);
		List<String> rooms=new LinkedList<String>();
		ResultSet rs=ptmt.executeQuery();
		while(rs.next()){
			rooms.add(rs.getString(1));
		}
		conn.close();
		return rooms;
	}
	
	public static void deleteRoom(String id) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="DELETE FROM rooms WHERE roomid=?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.execute();
		conn.close();
	}
}
