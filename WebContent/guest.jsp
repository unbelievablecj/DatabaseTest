<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href=work.jsp>返回</a><br>
<form action="guestRegister.d" method="post">
身份证号:<br>
<input type="text" name="id" value="">
<br>
姓名：<br>
<input type="text" name="name" value="">
<br>
性别：<br>
<input type="text" name="sex" value="" >
<br>
电话：<br>
<input type="text" name="phone" value="" >
<br>
<br>
<input type="submit" name="book" value="客户注册">
<input type="hidden" name="action" value="book">

</form> 
</body>
</html>