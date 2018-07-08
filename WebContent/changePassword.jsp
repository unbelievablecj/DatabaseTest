<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="work.jsp">返回</a>
<form action="changePassword.d" method="post">
旧密码:<br>
<input type="text" name="old_pwd" value="">
<br>
新密码:<br>
<input type="text" name="new_pwd" value="">
<br><br>
<input type="submit" name="changePassword" value="更改密码">
<input type="hidden" name="action" value="changePassword">
</form> 
</body>
</html>