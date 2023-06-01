
<%@page import="com.cglia.model.*"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("person", auth);
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="/includes/head.jsp"%>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="container">
				<div class="card w-50 mx-auto my-5">
					<div class="card-header text-center">
						<b>Forget Password</b>
					</div>
					<div class="card-body">
						<form action="forget-password" method="post">
							<div class="form-group">
								<label>Email address</label> <input type="email" maxlength="30"
									name="email" class="form-control" placeholder="Enter email"
									required>
							</div>
							<div class="form-group">
								<label>New Password</label> <input type="password" maxlength="8"
									name="new-password" class="form-control"
									placeholder="New Password" required>
							</div>
							<div class="form-group">
								<label>Confirm Password</label> <input type="password"
									maxlength="8" name="confirm-password" class="form-control"
									placeholder="Confirm Password" required>
							</div>
							<div class="form-group">
								<label>OTP</label> <input type="text" pattern="[0-9]{4}"
									maxlength="4" title="4 digit number: e.g. 1234" name="otp"
									class="form-control" placeholder="Enter OTP" size="4" required>
							</div>
							<div class="text-right">
								<button type="button" class="btn btn-warning">
									<a href="generate-otp">Generate OTP</a>
								</button>
								<br> <br>
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Reset
									Password</button>
								<br> <br>
								<%
								String errorMessage = (String) request.getAttribute("errorMessage");
								if (errorMessage != null) {
								%>
								<p class="text-danger"><%=errorMessage%></p>
								<%
								}
								%>
								<%
								String otpMessage = (String) request.getAttribute("otpMessage");
								%>
								<%
								if (otpMessage != null) {
								%>
								<p class="text-success">
									your OTP to change password is :
									<%=otpMessage%></p>
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
</body>
</html>