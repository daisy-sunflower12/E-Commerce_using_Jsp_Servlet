<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@page import="com.dao.*, com.model.*"%>

	<%
	DecimalFormat dcf = new DecimalFormat("#.##");
	request.setAttribute("dcf", dcf);
	User auth = (User) request.getSession().getAttribute("user");
	List<UserPurchase> orders = null;
	if (auth != null) {
		request.setAttribute("person", auth);
		orders = PurchaseDao.getAllPurchases(auth.getId());

	} else {
		response.sendRedirect("login.jsp");
	}
	ArrayList<UserPurchase> cart_list = (ArrayList<UserPurchase>) session.getAttribute("cart-list");
	if (cart_list != null) {
		request.setAttribute("cart_list", cart_list);
	}
	%>

	<div class="container mt-5">
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card ml-4">
					<div class="card-body">
						<h2 class="text-center">All Purchases</h2>

						<table class="table" border="1" width="90%"
							style="margin-left: 4%;">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Date</th>
									<th scope="col">Name</th>
									<th scope="col">Quantity</th>
									<th scope="col">Cost Price</th>
									<th scope="col">Total Price</th>
									<th scope="col">Cancel</th>

								</tr>
							</thead>
							<%
							if (orders != null) {
								for (UserPurchase o : orders) {
							%>
							<tr>
								<td><%=o.getPurchaseId()%></td>
								<td><%=o.getPurchaseDt()%></td>
								<td><%=o.getProdName()%></td>
								<td><%=o.getTotalUnit()%></td>
								<td><%=o.getProdCostPrice()%></td>
								<td><%=o.getTotalCostPrice()%></td>
								<td><a class="btn btn-sm btn-danger"
									href="cancelOrder?id=<%=o.getPurchaseId()%>">Cancel Order</a></td>
							</tr>
							<%
							}
							}
							%>
						</table>
						<div class="text-center mt-3">
							<a href="buyer.jsp">Checkout Products!!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>