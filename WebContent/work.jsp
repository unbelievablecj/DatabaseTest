<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>请选择要进行的操作</p>
<a href ="guest.jsp">客户注册</a>
<br>
<br>
<a href="book.jsp">预定房间</a>
<br>
<br>
<a href="add.jsp">添加房间</a>
<br>
<br>
<a href="search.jsp">查询房间</a>
<br>
<br>
<a href="searchGuest.jsp">查询客户</a>
<br>
<br>
<a href="deleteGuest.jsp">删除客户</a>
<br>
<br>
<a href="deleteRoom.jsp">删除房间</a>
<br>
<br>
<a href="changePassword.jsp">修改密码</a>
<br>
<br>
<form action="logout.d">
<input type="submit" name="logout" value="登出"> 
</form>
<br>
</body>
</html>