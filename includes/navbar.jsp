
<nav class="navbar navbar-expand-lg sticky-top bg-body-tertiary" data-bs-theme="dark" style="background-color: #e3f2fd;">
	<div class="container">
		<a class="navbar-brand" href="index.jsp">
		<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions">
		<%if (auth!=null) { %>Good Morning
		<%= auth.getName().toUpperCase()%>
		<%}else{ %>CGLIA<%} %><i class="fa-solid fa-cart-shopping" style="color: #f10e52;"></i></button></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
            <form action ="index.jsp" method ="post" class="d-flex" role="search">
               <input class="form-control me-2 rounded-pill" type="search" placeholder="Search for products" aria-label="Search" name="search">
               <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
          </div>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart <span class="badge badge-danger">${cart_list.size()}</span> </a></li>
				<%
				if(auth!=null) {
				%>
				<li class="nav-item"><a class="nav-link" href="orders.jsp">Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="log-out">Logout</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<li class="nav-item"><a class="nav-link" href="registration.jsp">SignUp</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>