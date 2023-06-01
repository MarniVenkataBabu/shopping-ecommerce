<%@page import="com.cglia.connection.DbCon"%>
<%@page import="com.cglia.dao.ProductDao"%>
<%@page import="com.cglia.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("person", auth);
}
ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
// Retrieve the search query parameter
String searchQuery = request.getParameter("search");

// Retrieve products based on sidebar item click
String sidebarCategory = request.getParameter("category");
// Check if a search query is present
if (searchQuery != null && !searchQuery.isEmpty()) {
	// Retrieve the searched products
	products = pd.getSearchProducts(searchQuery);
} else {
	if (sidebarCategory != null && !sidebarCategory.isEmpty()) {
		products = pd.getSearchProducts(sidebarCategory);
	}
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/includes/head.jsp"%>
<title>Cglia-Shopping-cart</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<%@include file="/includes/sidebar.jsp"%>
	<div style="margin-left: 10%">
		<div class="w3-container">
			<div class="bg-body-tertiary">
				<div class="container-sm">
					<div class="card-header my-3">
						<%
						if (searchQuery != null) {
						%>
						<%=searchQuery%>
						Products
						<%
						} else if (sidebarCategory != null) {
						%>
						<%=sidebarCategory%>
						Products
						<%
						} else {
						%>
						<p>All Products</p>
						<%
						}
						%>
					</div>
				</div>
				<div class="card-body" style="margin-left: 25%">
					<%
					if (sidebarCategory != null && sidebarCategory.equals("help")) {
					%>
					<h1>
						Contact <b> 7093702308</b> for further assistance.<br>or<br>Email
						us :<br>
						<b>venkatthekiller@gmail.com</b>
					</h1>
					<h1>we can get back to you. Thank you !</h1>
					<%
					}
					%>
					<%
					String message = (String) request.getAttribute("message");
					if (message != null) {
					%>
					<%=message%>
					<%
					}
					%>
				</div>
				<div class="row">
					<%
					if (!products.isEmpty()) {
						for (Product p : products) {
					%>
					<div class="col-md-3 my-3">
						<div class="card w-100">
							<img class="card-img-top" height=350
								src="product-image/<%=p.getImage()%>" alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title"><%=p.getName()%></h5>
								<h6 class="price">
									Price: &#8377
									<%=p.getPrice()%></h6>
								<h6 class="category">
									Category:
									<%=p.getCategory()%></h6>
								<div class="mt-3 d-flex justify-content-between">
									<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add
										to Cart</a> <a class="btn btn-primary"
										href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
								</div>
							</div>
						</div>
					</div>
					<%
					}
					} else {
					if (products.isEmpty() && (sidebarCategory == null || !sidebarCategory.equals("help"))) {
					out.println("There are no products");
					}
					}
					%>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/includes/footer.jsp"%>
</body>
</html>