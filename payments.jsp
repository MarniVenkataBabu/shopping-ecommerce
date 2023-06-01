<%@page import="com.cglia.model.*"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/includes/head.jsp"%>
<title>Venkat-Shopping-Cart - Payments</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<%@include file="/includes/sidebar.jsp"%>
	<div class="card-body" style="margin-left: 10%">
		<div class="container-sm">
			<div class="row">
				<div class="container">
					<div class="card w-50 mx-auto my-5">
						<div class="card-header text-center">
							<h3>Select Payment Method:</h3>
						</div>
						<div class="card-body">
							<form action="cart-check-out" method="post">
								<ul>
									<li><label> <input type="radio" name="payment"
											value="cod" checked> Cash on Delivery (COD)
									</label></li>
									<li><label> <input type="radio" name="payment"
											value="credit_card"> Credit Card
									</label></li>
									<li><label> <input type="radio" name="payment"
											value="prepaid_debit_card"> Prepaid Debit Card
									</label></li>
									<li><label> <input type="radio" name="payment"
											value="upi"> UPI
									</label></li>
								</ul>
								<div class="text-center">
									<button type="submit" class="btn btn-success">Proceed
										to Checkout</button>
									<%
									String errorMessage = (String) request.getAttribute("message");
									if (errorMessage != null) {
									%>
									<p class="text-danger"><%=errorMessage%></p>
									<%
									}
									%>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/includes/footer.jsp"%>
</body>
</html>
