<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="base.jsp"></jsp:include>
<title>Edit Form</title>
</head>
<body>
<%@page import="com.model.Product, com.dao.ProductDao"%> 

	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Product Details</h2>
						<form action="EditProduct" method="post">
						<input type="hidden" name="id" value="${p.id }">
							<div class="form-group">
								<label>Product Name:</label> <input type="text" name="prodName"
									class="form-control" value="${p.prodName }" readonly="readonly"/>
							</div>
							<div class="form-group mt-2">
								<label>Product Description:</label> <input type="text"
									name="prodDesc" class="form-control mt-1" value="${p.prodDesc}" required />
							</div>
							<div class="form-group mt-2">
								<label>Product Image:</label> <input type="file" name="prodImg"
									class="form-control mt-1" />
									<img src="webapp/files/${p.prodImg}">
									
							</div>
							<div class="form-group mt-2">
								<label>Product Sell Price:</label> <input type="number"
									name="prodSellPrice" class="form-control mt-1" value="${p.prodSellPrice}"/>
							</div>
							<div class="form-group mt-2">
								<label>Product Cost Price:</label> <input type="number"
									name="prodCostPrice" class="form-control mt-1" value="${p.prodCostPrice}"/>
							</div>
							<div class="form-group mt-2">
								<label>Stock Unit:</label> <input type="number" name="stockUnit"
									class="form-control mt-1" value="${p.stockUnit}"/>
							</div>
							<div class="form-group mt-2">
								<label>User Created:</label> <input type="number"
									name="userCreated" class="form-control mt-1" value="${p.userCreated}"/>
							</div>
							
							<div class="text-center mt-3">
							<input type="submit" value="Update Product">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>