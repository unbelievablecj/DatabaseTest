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
<form action="searchGuest.d" method="post">
客户身份证:<br>
<input type="text" name="id" value="">
<br>
<br>
<input type="submit" name="searchGuest" value="查询客户">
<input type="hidden" name="action" value="searchGuest">
</form> 
</body>
</html>