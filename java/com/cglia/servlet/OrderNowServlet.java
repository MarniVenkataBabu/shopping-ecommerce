package com.cglia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cglia.connection.*;
import com.cglia.dao.*;
import com.cglia.model.*;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 */
@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(OrderNowServlet.class);

	/**
	 * doPost method with request and response objects
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			User auth = (User) request.getSession().getAttribute("auth");
			if (auth != null) {
				processOrder(request, response, formatter, date, auth);
			} else {
				// Redirect to login page with message
				request.setAttribute("checkoutMessage", "Please log in to Buy products.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			logger.error("An error occurred: {}", e.getMessage(), e);
		}
	}

	/**
	 * processOrder Method which has business logic of process Order with assigning
	 * orderId, productId
	 * 
	 * @param request
	 * @param response
	 * @param formatter
	 * @param date
	 * @param auth
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void processOrder(HttpServletRequest request, HttpServletResponse response, SimpleDateFormat formatter,
			Date date, User auth) throws IOException, ClassNotFoundException, SQLException {
		String productId = request.getParameter("id");
		int productQuantity = Integer.parseInt(request.getParameter("quantity"));
		if (productQuantity <= 0) {
			productQuantity = 1;
		}
		Order orderModel = new Order();
		orderModel.setId(Integer.parseInt(productId));
		orderModel.setUserId(auth.getId());
		orderModel.setOrderQuantity(productQuantity);
		orderModel.setOrderDate(formatter.format(date));

		OrderDao orderDao = new OrderDao(DbCon.getConnection());
		boolean result = orderDao.insertOrder(orderModel);
		if (result) {
			ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if (cartList != null) {
				removeProductFromCartList(cartList, productId);
			}

			response.sendRedirect("orders.jsp");
		} else {
			PrintWriter out = response.getWriter();
			out.println("order failed");
		}
	}

	/**
	 * 
	 * @param cartList
	 * @param productId
	 */
	private void removeProductFromCartList(ArrayList<Cart> cartList, String productId) {
		Iterator<Cart> iterator = cartList.iterator();
		while (iterator.hasNext()) {
			Cart cart = iterator.next();
			if (cart.getId() == Integer.parseInt(productId)) {
				iterator.remove();
				break;
			}
		}
	}

	/**
	 * doGet method with request and response
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
