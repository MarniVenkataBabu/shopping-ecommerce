<%@page import="com.cglia.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	response.sendRedirect("index.jsp");
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/includes/head.jsp"%>
<title>Venkat-Shopping-Cart</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="container">
				<div class="card w-50 mx-auto my-5">
					<div class="card-header text-center">
						<b>User Login</b>
					</div>
					<div class="card-body">
						<form action="user-login" method="post">
							<div class="form-group">
								<label>Email address</label> <input type="email" maxlength="30"
									name="login-email" class="form-control"
									placeholder="Enter email" required>
							</div>
							<div class="form-group">
								<label>Password</label> <input type="password" maxlength="8"
									name="login-password" class="form-control"
									placeholder="Password" required>
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Login</button>
								<button class="btn btn-warning">
									<a href="registration.jsp">New User Click Here!</a>
								</button>
								<br> <br> <a href="forget-password.jsp"
									class="btn btn-link">Forgot Password?</a>
								<%
								String errorMessage = (String) request.getAttribute("errorMessage");
								if (errorMessage != null) {
								%>
								<p class="text-danger"><%=errorMessage%></p>
								<%
								}
								%>
								<%
								String successMessage = (String) request.getAttribute("successMessage");
								if (successMessage != null) {
								%>
								<p class="text-success"><%=successMessage%></p>
								<%
								}
								%>
								<%
								String message = (String) request.getAttribute("message");
								if (message != null) {
								%>
								<p class="text-success"><%=message%></p>
								<%
								}
								%>
								<%-- Check if there is a checkout message in the session --%>
								<%
								String checkoutMessage = (String) request.getAttribute("checkoutMessage");
								if (checkoutMessage != null) {
								%>
								<p style="color: red;"><%=checkoutMessage%></p>
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
	<%@include file="/includes/footer.jsp"%>
</body>
</html>