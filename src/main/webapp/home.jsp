<%@page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Crud Home</title>
</head>
<body>
<jsp:useBean id="u" class="com.model.User"></jsp:useBean>

<h2>Welcome to Product Application</h2>
<%
	User user = (User)session.getAttribute("user");
%>
<h2>User is <%= user.getUserRoleName() %></h2>

<c:if test="${user.getUserRoleName() == 'Buyer'}">
<%-- <% response.sendRedirect("buyer.jsp"); %> --%>
<a href="buyer.jsp">Go to Buyer</a>
</c:if>

<c:if test="${user.getUserRoleName() == 'Seller'}">
<a href="seller.jsp">Go to Seller</a>
</c:if>

</body>
</html>