<%@page import="com.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Products</title>
<jsp:include page="base.jsp"></jsp:include>
</head>
<body>
	<%@page import="com.model.Product, com.dao.ProductDao"%>
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">View Product</h2>

						<table class="table" border="1" width="100%">
							<thead>
								<tr>
									<th scope="col">Id</th>
									<th scope="col">Product Name</th>
									<th scope="col">Product Description</th>
									<th scope="col">Product Sell Price</th>
									<th scope="col">Product Cost Price</th>
									<th scope="col">Product Stock Price</th>
									<th scope="col">Edit</th>
									<th scope="col">Delete</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<%-- <td>${list}</td> --%>
								</tr>
								<c:forEach items="${list}" var="p">
									<tr>
										<th scope="row">${p.id }</th>
										<td>${p.prodName}</td>
										<td>${p.prodDesc}</td>
										<td>${p.prodSellPrice}</td>
										<td>${p.prodCostPrice}</td>
										<td>${p.stockUnit}</td>
										<td><a href="EditProductForm?id=${p.id}">Edit</a></td>
										<td><a href="DeleteProduct?id=${p.id}">Delete</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="text-center mt-3">
							<a href="addProduct.jsp">Add Product</a>
						</div>
						<div class="text-center mt-3">
							<a href="seller.jsp">Home</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>