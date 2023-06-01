package com.cglia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cglia.model.Cart;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 *
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddToCartServlet.class.getName());

	/**
	 * doGet method with request and response as arguments
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			List<Cart> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));
			Cart cart = new Cart();
			cart.setId(id);
			cart.setQuantity(1);
			HttpSession session = request.getSession();
			List<Cart> cartListItems = (ArrayList<Cart>) session.getAttribute("cart-list");
			if (cartListItems == null) {
				cartList.add(cart);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
			} else {
				cartList = cartListItems;
				boolean exist = false;
				for (Cart c : cartListItems) {
					if (c.getId() == id) {
						exist = true;
						String message = "Item is already in the cart. Please increase the quantity here.";
						request.setAttribute("allReadyCartmessage", message);
						request.getRequestDispatcher("cart.jsp").forward(request, response);
						logger.log(Level.INFO, "Item already exists in the cart");
						break;
					}
				}
				if (!exist) {
					cartList.add(cart);
					response.sendRedirect("index.jsp");
				}
			}
		} catch (IOException | ServletException e) {
			logger.log(Level.SEVERE, "Error in AddToCartServlet", e);
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
