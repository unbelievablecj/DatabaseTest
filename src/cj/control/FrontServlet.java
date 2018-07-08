package cj.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cj.dao.*;
import cj.model.Guest;
import cj.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet(
		name="FrontServlet",
		urlPatterns={"*.d"},
		loadOnStartup=1
		)
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servletPath=request.getServletPath();
		String methodName=servletPath.substring(1);
		methodName=methodName.substring(0,methodName.length()-2);
		try{
			Method method=getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		}catch(Exception e){
			request.getRequestDispatcher("error.jsp?msg=don't have this function").forward(request, response);
		}
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		String username=request.getParameter("user_name");
		String password=request.getParameter("user_password");
		
		try {
			if(UserDao.login(username, password)){
				HttpSession session=request.getSession();
				String status="pass";
				session.setAttribute("status",status);
				session.setAttribute("username",username);
				request.getRequestDispatcher("work.jsp").forward(request, response);
			}
			else{
				request.getRequestDispatcher("error.jsp?msg=User don't exist or pwd wrong").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void bookRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String opname=(String) session.getAttribute("username");
		String roomid=request.getParameter("roomid");
		String start=request.getParameter("StartDate");
		String end=request.getParameter("EndDate");
		String id =request.getParameter("id");
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date sDate=format.parse(start);
			Date eDate=format.parse(end);
			double price=RoomDao.bookRoom(opname, roomid, sDate, eDate, id);
			if(price==-1){
				request.getRequestDispatcher("success.jsp?msg=订房失败,时间冲突").forward(request, response);
				return ;
			}
			request.getRequestDispatcher("success.jsp?msg=订房成功,价格为"+Double.toString(price)).forward(request, response);
			
		} catch (ParseException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
	
	protected void addRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String id=request.getParameter("roomid");
		String type=request.getParameter("roomtype");
		String price=request.getParameter("roomprice");
		Double d=new Double(price);
		double p=d.doubleValue();
		try {
			RoomDao.addRoom(id, type, p);
			request.getRequestDispatcher("success.jsp?msg=操作成功").forward(request, response);
		} catch (SQLException e) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}
	
	protected void searchRoomId(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String roomid = request.getParameter("roomid");
		try {
			Room r = RoomDao.searchRoomId(roomid);
			request.setAttribute("room", r);
			request.getRequestDispatcher("room.jsp").forward(request, response);
		} catch (SQLException e) {
			request.getRequestDispatcher("success.jsp?msg=sql错误").forward(request, response);
			e.printStackTrace();
		}
		
		
		
	}
	
	protected void checkOut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String orderid = request.getParameter("orderid");
		Integer id=new Integer(orderid);
		try {
			RoomDao.checkOut(id.intValue());
			request.getRequestDispatcher("success.jsp?msg=退房成功").forward(request, response);
		} catch (SQLException e) {
			request.getRequestDispatcher("success.jsp?msg=sql错误").forward(request, response);
			e.printStackTrace();
		}
	}
	
	protected void guestRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String id =request.getParameter("id");
		String name= request.getParameter("name");
		String s=request.getParameter("sex");
		String phone=request.getParameter("phone");
		int sex=0;
		if(s.equals("男")){
			sex=1;
		}
		else{
			sex=-1;
		}
		try {
			GuestDao.addGuest(id, name, sex, phone);
			request.getRequestDispatcher("success.jsp?msg=注册成功").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.getRequestDispatcher("work.jsp").forward(request, response);
		}
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String username=request.getParameter("user_name");
		String password=request.getParameter("user_password");
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		String phone=request.getParameter("phone");
		String s=request.getParameter("sex");
		int sex=1;
		if(s.equals("男")){
			sex=1;
		}
		else{
			sex=-1;
		}
		try {
			UserDao.register(username, password, name, id, phone, sex);
			response.sendRedirect("index.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		session.invalidate();
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void searchRoomType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String type = request.getParameter("roomtype");
		try {
			List<String> rooms = RoomDao.searchRoomType(type);
			PrintWriter pw=response.getWriter();
			pw.write("<a href=work.jsp>return</a><br>");
			for(int i=0;i<rooms.size();i++){
				pw.write("Roomid:"+rooms.get(i)+"<br>");
			}
		} catch (SQLException e) {
			request.getRequestDispatcher("success.jsp?msg=sql错误").forward(request, response);
			e.printStackTrace();
		}
		
	}
	protected void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String opwd = request.getParameter("old_pwd");
		String npwd = request.getParameter("new_pwd");
		String user = (String) session.getAttribute("username");
		try {
			UserDao.changePassword(user, opwd, npwd);
			request.getRequestDispatcher("success.jsp?msg=密码更改成功").forward(request, response);
		} catch (SQLException e) {
			request.getRequestDispatcher("success.jsp?msg=sql错误").forward(request, response);
			e.printStackTrace();
		}
	}
	protected void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String roomid = request.getParameter("roomid");
		try {
			RoomDao.deleteRoom(roomid);
			request.getRequestDispatcher("success.jsp?msg=删除房间成功").forward(request, response);
		} catch (SQLException e) {
			request.getRequestDispatcher("success.jsp?msg=sql错误").forward(request, response);
			e.printStackTrace();
		}
	}
	protected void deleteGuest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String id = request.getParameter("id");
		try {
			GuestDao.deleteGuest(id);
			request.getRequestDispatcher("success.jsp?msg=删除客户信息成功").forward(request, response);
		} catch (SQLException e) {
			request.getRequestDispatcher("success.jsp?msg=sql错误").forward(request, response);
			e.printStackTrace();
		}
	}
	
	protected void searchGuest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String pass=(String) session.getAttribute("status");
		if(pass==null||!pass.equals("pass")){
			response.sendRedirect("index.jsp");
			return;
		}
		String id = request.getParameter("id");
		try {
			Guest g = GuestDao.searchGuest(id);
			request.setAttribute("guest", g);
			request.getRequestDispatcher("guestResult.jsp").forward(request, response);
		} catch (SQLException e) {
			request.getRequestDispatcher("success.jsp?msg=sql错误").forward(request, response);
			e.printStackTrace();
		}
	}
}
