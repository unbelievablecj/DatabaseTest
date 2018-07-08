<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href=register.jsp>注册</a><br>
<form action="login.d" method="post">
用户名:<br>
<input type="text" name="user_name" value="">
<br>
密码:<br>
<input type="text" name="user_password" value="">
<br><br>
<input type="submit" name="login" value="登陆">
<input type="hidden" name="action" value="login">
</form> 
</body>
</html>