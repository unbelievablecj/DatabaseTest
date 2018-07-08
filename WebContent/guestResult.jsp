<%@page import="cj.model.Guest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="work.jsp">主菜单</a><br>
<%
	Guest g=(Guest)request.getAttribute("guest");
%>
身份证：<%=g.getId() %><br>
姓名：<%=g.getName() %><br>
手机号：<%=g.getPhone() %><br>
<%String sex="";
if(g.getSex()==1){
	sex="男";
}
else{
	sex="女";
}
%>
性别：<%=sex %>


</body>
</html>