<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.Format"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="cj.model.order"%>
<%@page import="java.util.List"%>
<%@page import="cj.model.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<a href="search.jsp">返回</a>
<br>
<%
	Room r = (Room)request.getAttribute("room");
%>
房间号为<%=r.getRoomid()%>
<br>
房间类型为<%=r.getRoomtype()%>
<br>
房间单价为<%=r.getPrice() %>
<br>
<%
List<order> orders=r.getOrders();
PrintWriter pw=response.getWriter();
Format format = new SimpleDateFormat("yyyy-MM-dd");
for(int i=0;i<orders.size();i++){
	order o=orders.get(i);
	
	pw.write(o.getGuest()+" 开始时间：");
	pw.write(format.format(o.getStart())+" 结束时间：");
	pw.write(format.format(o.getEnd())+" ");
	pw.write("<a href=\"checkOut.d?orderid="+o.getOrderid()+"\">退房</a><br>");
}
%>

</body>
</html>