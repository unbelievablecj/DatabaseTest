<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href=index.jsp>登陆</a><br>
<form action="register.d" method="post">
用户名:<br>
<input type="text" name="user_name" value="">
<br>
密码:<br>
<input type="text" name="user_password" value="">
<br>
姓名：<br>
<input type="text" name="name" value="">
<br>
身份证号:<br>
<input type="text" name="id" value="">
<br>
手机号:<br>
<input type="text" name="phone" value="">
<br>
性别:<br>
<input type="text" name="sex" value="">
<br>
<br>
<input type="submit" name="register" value="注册">
<input type="hidden" name="action" value="register">

</form> 
</body>
</html>