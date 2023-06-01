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
import com.cglia.model.*;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 */
@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(RemoveFromCartServlet.class);

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
			String bookId = request.getParameter("id");
			if (bookId != null) {
				ArrayList<Cart> cartListItems = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if (cartListItems != null) {
					for (Cart c : cartListItems) {
						if (c.getId() == Integer.parseInt(bookId)) {
							cartListItems.remove(cartListItems.indexOf(c));
							break;
						}
					}
				}
				response.sendRedirect("cart.jsp");

			} else {
				response.sendRedirect("cart.jsp");
			}

		} catch (Exception e) {
			logger.error("An error occurred: {}", e.getMessage(), e);
		}
	}
}
