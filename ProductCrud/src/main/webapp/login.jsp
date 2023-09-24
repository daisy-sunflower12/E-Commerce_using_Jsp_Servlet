<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="base.jsp"></jsp:include>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<div class="container mt-5">
	
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card">
					<div class="card-body">
						<h2 class="text-center">Login here</h2>
						<form action="login" method="post">
							<div class="form-group">
								<label>Email:</label> <input type="email" name="email"
									class="form-control" />
							</div>
							<div class="form-group">
								<label>Password:</label> <input type="password" name="password"
									class="form-control" />
							</div>
							<div class="text-center mt-3">
								<input type="submit" value="Login"/>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>