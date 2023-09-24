<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>E-Commerce Cart</title>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
</head>
<body>
	<%@page import="com.dao.*, com.model.*"%>
	<jsp:include page="base.jsp"></jsp:include>
	<%
	DecimalFormat dcf = new DecimalFormat("#.00");
	User auth = (User) request.getSession().getAttribute("user");
	if (auth != null) {
		request.setAttribute("user", auth);
	}

	ArrayList<UserPurchase> cart_list = (ArrayList<UserPurchase>) session.getAttribute("cart-list");
	List<UserPurchase> cartProduct = null;

	if (cart_list != null) {
		cartProduct = ProductDao.getAllCartProducts(cart_list);
		double total = ProductDao.getCartPrice(cart_list);
		request.setAttribute("total", total);
		request.setAttribute("cart_list", cart_list);
	}
	%>

	<div class="container mt-5">
	${total}
	<div class="d-flex py-3"><h3> </h3> <a class="mx-3 btn btn-primary" href="checkOutCart">Check Out</a></div>
		<div class="row">
			<div class="col-md-7 offset-md-2">
				<div class="card m-4">
					<div class="card-body">
						<h2 class="text-center">Product Cart</h2>

						<table class="table"  width="50%">
							<thead>
								<tr>
									<th scope="col">Id</th>
									<th scope="col">Product Cost Price</th>
									<th scope="col">Quantity</th>
									<th scope="col">Cancel</th>
								</tr>
							</thead>
							<tbody>
								<%
								if (cart_list != null)
									for (UserPurchase c : cart_list) {
								%>
								<tr>
									<td scope="row"><%=c.getId()%></td>
									<td><%= c.getProdCostPrice()%></td>
									<td style="margin-top: 2px;">
									
										<form action="orderNow" method="post" class="form-inline">
											<input type="hidden" name="id" value="<%=c.getId()%>">
											<input type="hidden" name="purchaseFromUser" value="<%=c.getPurchaseFromUser()%>">
												
											<div class="form-group d-flex justify-content-between">
												<a class="btn bnt-sm btn-incre"
													href="quantityManage?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i>
													</a> 
												<input type="text"	name="qty" class="form-control" value="<%=c.getTotalUnit()%>" readonly style="text-align: center;"> 
												<a class="btn btn-sm btn-decre" href="quantityManage?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
											</div>
											<div class="text-center mt-3">
												<input type="submit" value="Buy">
											</div>
										</form>
									</td>
									<td><a href="RemoveFromCart?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
								</tr>

								<%
								}
								%>


							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>