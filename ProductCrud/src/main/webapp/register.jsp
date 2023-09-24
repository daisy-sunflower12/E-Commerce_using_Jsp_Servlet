<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Register here</title>

</head>
<body>

<c:forEach var= "error" items="${error_msg}" >
Error is : ${error }
</c:forEach>

	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Register here</h2>
						<form action="RegisterServlet" name="form" method="post">
							<div class="form-group">
								<label for="exampleInputEmail1">First Name:</label> <input
									type="text" name="firstName" class="form-control"/>
							</div>
							<div class="form-group mt-2">
								<label>Last Name:</label> <input type="text" name="lastName"
									class="form-control mt-1" required/>
							</div>
							<div class="form-group mt-2">
								<label>Email:</label> <input type="email" name="email"
									class="form-control mt-1" required/>
							</div>
							<div class="form-group mt-2">
								<label>Password:</label> <input type="password" name="password"
									class="form-control mt-1" required/>
							</div>
							<div class="form-group mt-2">
								<label>DoB:</label> <input type="text" name="dob"
									class="form-control mt-1" required/>
							</div>
							<div class="form-group mt-2">
								<label>Mobile:</label> <input type="text" name="mobile"
									class="form-control mt-1" required/>
							</div>
							<div class="form-group mt-2">
								<label>Role Type:</label> <select name="role_id"
									class="form-select mt-1" style="width: 100%" required>
									<option value="1">Buyer</option>
									<option value="2">Seller</option>
								</select>
							</div>
							<div class="form-group mt-2">
								<label>Balance:</label> <input type="number" name="balance"
									class="form-control mt-1" required/>
							</div>
							<div class="text-center mt-3">
								<input type="submit" value="Register" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>