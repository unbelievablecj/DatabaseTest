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
<form action="searchRoomId.d" method="post">
房间号:<br>
<input type="text" name="roomid" value="">
<br>
<br>
<input type="submit" name="searchRoomId" value="查询房间">
<input type="hidden" name="action" value="searchRoomId">
</form> 
<br>
<form action="searchRoomType.d" method="post">
房间类型:<br>
<input type="text" name="roomtype" value="">
<br>
<br>
<input type="submit" name="searchRoomType" value="此类型空房查询">
<input type="hidden" name="action" value="searchRoomType">
</form>
</body>
</html>