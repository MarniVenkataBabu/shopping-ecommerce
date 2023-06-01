package com.cglia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cglia.model.Cart;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 */
@WebServlet("/quantity-inc-dec")
public class QuantityIncDecServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(QuantityIncDecServlet.class);
	private static final long serialVersionUID = 1L;
	private static final String CART_JSP = "cart.jsp";

	/**
	 * doGet method of HttpServlet class with request and response objects
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Cart> cartListItems = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					incrementQuantityById(id, cartListItems);
					response.sendRedirect(CART_JSP);
				} else if (action.equals("dec")) {
					decrementQuantityById(id, cartListItems);
					response.sendRedirect(CART_JSP);
				}
			} else {
				response.sendRedirect(CART_JSP);
			}
		} catch (IOException e) {
			logger.error("An error occurred: {}", e.getMessage(), e);
		}
	}

	/**
	 * This Method takes productId and increase the quantity of product in the cart
	 * page
	 * 
	 * @param id
	 * @param cartListItems
	 */
	private void incrementQuantityById(int id, ArrayList<Cart> cartListItems) {
		for (Cart c : cartListItems) {
			if (c.getId() == id) {
				int quantity = c.getQuantity();
				quantity++;
				c.setQuantity(quantity);
				return;
			}
		}
	}

	/**
	 * This Method takes id of product and cartListItems to decrease the count of
	 * items in the cart
	 * 
	 * @param id
	 * @param cartListItems
	 */
	private void decrementQuantityById(int id, ArrayList<Cart> cartListItems) {
		for (Cart c : cartListItems) {
			if (c.getId() == id && c.getQuantity() > 1) {
				int quantity = c.getQuantity();
				quantity--;
				c.setQuantity(quantity);
				return;
			}
		}
	}
}
