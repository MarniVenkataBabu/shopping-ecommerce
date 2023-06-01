<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.cglia.model.*"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<%@include file="/includes/head.jsp"%>
<title>User Registration</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">
				<b>User Registration</b>
			</div>
			<div class="card-body">
				<form action="register-servlet" method="POST">
					<label for="name">Name :</label> <input type="text" maxlength="10"
						id="name" class="form-control" name="name" required><br>
					<br> <label for="email">Email:</label> <input type="email"
						id="email" maxlength="30" class="form-control" name="email"
						required><br> <br> <label for="password">Password:</label>
					<input type="password" id="password" maxlength="8"
						class="form-control" name="password" required><br> <br>
					<div class="text-center">
						<input class="btn btn-primary" type="submit" value="Register">
						<button class="btn btn-warning">
							<a href="index.jsp">Back to Home</a>
						</button>
						<br>
						<%
						String errorMessage = (String) request.getAttribute("errorMessage");
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
	<%@include file="/includes/footer.jsp"%>
</body>
</html>