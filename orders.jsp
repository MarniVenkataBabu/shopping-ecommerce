<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.cglia.dao.OrderDao" %>
<%@ page import="com.cglia.connection.DbCon" %>
<%@ page import="com.cglia.dao.ProductDao" %>
<%@ page import="com.cglia.model.*" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%
  DecimalFormat dcf = new DecimalFormat("#.##");
  request.setAttribute("dcf", dcf);
  User auth = (User) request.getSession().getAttribute("auth");
  List<Order> orders = null;
  ProductDao pd = new ProductDao(DbCon.getConnection());
  List<Product> products = pd.getAllProducts();
  if(auth != null) {
    request.setAttribute("person", auth);
    OrderDao orderDao = new OrderDao(DbCon.getConnection());
    orders = orderDao.userOrders(auth.getId());
  } else {
    response.sendRedirect("login.jsp");
  }
  ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
  if(cart_list != null) {
    request.setAttribute("cart_list", cart_list);
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/includes/head.jsp" %>
    <title>Venkat-Shopping-Cart</title>
     <style>
        .order-image {
            width: 5%;
        }
    </style>
</head>
<body>
    <%@include file="/includes/navbar.jsp" %>
    <%@include file="/includes/sidebar.jsp" %>
    <div class="card-body" style="margin-left: 10%">
        <div class="container-sm">
            <div class="card-header my-3">All Orders</div>
            <table class="table table-striped">
            <caption>This table displays your Recent Orders.</caption>
               <thead>
                    <tr>
                        <th scope="col">Image</th>
                        <th scope="col">Name</th>
                        <th scope="col">Category</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <% if(orders != null) {
                         for(Order order : orders) {
                            String productImage = "";
                            for(Product product : products) {
                                if(product.getName().equals(order.getName())) {
                                    productImage = product.getImage();
                                    break;
                                }
                            }
                     %>
                            <tr>
                                <td class="order-image"><img src="product-image/<%= productImage %>" alt="Product Image" class="img-thumbnail"></td>
                                <td><%= order.getName() %></td>
						<td><%=order.getCategory()%></td>
                                <td><%= order.getOrderQuantity() %></td>
                                <td><%= dcf.format(order.getPrice()) %></td>
                                <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%= order.getOrderId() %>">Cancel Order</a></td>
                            </tr>
                     <%   }
                      }
                    %>
                </tbody>
            </table>
            <%   
              String successMessage = (String) request.getAttribute("successMessage");
              if(successMessage != null && !successMessage.isEmpty()) {  
            %>
                <div class="alert alert-success" role="alert"><%= successMessage %></div>
            <% 
               } 
            %>
        </div>
    </div>
  <%@include file="/includes/footer.jsp" %>  
</body>
</html>
