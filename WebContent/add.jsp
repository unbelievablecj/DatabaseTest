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
<form action="addRoom.d" method="post" accept-charset="UTF-8">
房间号:<br>
<input type="text" name="roomid" value="">
<br>
房间类型:<br>
<input type="text" name="roomtype" value="">
<br>
房间单价<br>
<input type="text" name="roomprice" value="">
<br>
<br>
<input type="submit" name="addRoom" value="添加房间">
<input type="hidden" name="action" value="addRoom">

</form> 
</body>
</html>