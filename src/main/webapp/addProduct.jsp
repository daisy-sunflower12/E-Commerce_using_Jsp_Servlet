<%@page import="com.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<jsp:include page="base.jsp"></jsp:include>
</head>
<body>

<%
	User user = (User)session.getAttribute("user");
%>

${user.id}
	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Add Product here</h2>
						<form action="AddProduct" method="post"
							enctype="multipart/form-data">
							<div class="form-group">
								<label>Product Name:</label> <input type="text" name="prodName"
									class="form-control" />
							</div>
							<div class="form-group mt-2">
								<label>Product Description:</label> <input type="text"
									name="prodDesc" class="form-control mt-1" required />
							</div>
							<div class="form-group mt-2">
								<label>Product Image:</label> <input type="file" name="prodImg"
									class="form-control mt-1" />
							</div>
							<div class="form-group mt-2">
								<label>Product Sell Price:</label> <input type="number"
									name="prodSellPrice" class="form-control mt-1" />
							</div>
							<div class="form-group mt-2">
								<label>Product Cost Price:</label> <input type="number"
									name="prodCostPrice" class="form-control mt-1" />
							</div>
							<div class="form-group mt-2">
								<label>Stock Unit:</label> <input type="number" name="stockUnit"
									class="form-control mt-1" />
							</div>
							<div class="form-group mt-2">
								<label>User Created:</label> <input type="text"
									name="userCreated" class="form-control mt-1" value="${user.id}" readonly/>
							</div>
							<div class="text-center m-3">
								<input type="submit" value="Add Product">
							</div>
						</form>
						<div class="text-center mt-3">
							<a href="ViewProduct">View Product</a>
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