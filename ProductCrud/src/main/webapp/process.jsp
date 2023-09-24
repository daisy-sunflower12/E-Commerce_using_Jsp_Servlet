<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@page import="com.dao.UserDao" %>
<jsp:useBean id="u" class="com.model.User"></jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
int i = UserDao.saveUser(u);
if(i>0){
	out.print("Registered successfully");
	response.sendRedirect("home.jsp");
}else{
	out.print("Unable to register");
	request.getRequestDispatcher("register.jsp");
}
%>
</body>
</html>