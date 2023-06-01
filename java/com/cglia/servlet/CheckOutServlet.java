package com.cglia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cglia.connection.DbCon;
import com.cglia.dao.OrderDao;
import com.cglia.model.Cart;
import com.cglia.model.Order;
import com.cglia.model.User;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 */
@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(CheckOutServlet.class);

	/**
	 * doGet method with request and response
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			ArrayList<Cart> cartListItems = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			if (cartListItems != null && auth != null) {
				for (Cart c : cartListItems) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUserId(auth.getId());
					order.setOrderQuantity(c.getQuantity());
					order.setOrderDate(formatter.format(date));

					OrderDao oDao = new OrderDao(DbCon.getConnection());
					boolean result = oDao.insertOrder(order);
					if (!result)
						break;
				}
				cartListItems.clear();
				// redirect to orders.jsp
				response.sendRedirect("orders.jsp");
				logger.info("Order placed successfully for user: {}", auth.getName());
			} else {
				if (auth == null) {
					// Redirect to login page with message
					request.setAttribute("checkoutMessage", "Please log in to check out.");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					// Redirect to cart page with message
					request.setAttribute("cartEmptymessage",
							"Your cart is empty. Please add items to proceed to checkout.");
					request.getRequestDispatcher("cart.jsp").forward(request, response);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			logger.error("An exception occurred during checkout", e);
		}
	}

	/**
	 * doPost method with request and response
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
