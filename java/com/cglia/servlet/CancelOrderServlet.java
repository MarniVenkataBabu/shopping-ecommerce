package com.cglia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cglia.connection.DbCon;
import com.cglia.dao.OrderDao;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 */
@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(CancelOrderServlet.class);

	/**
	 * doGet method with request and response arguments
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if (id != null) {
				OrderDao orderDao = new OrderDao(DbCon.getConnection());
				orderDao.cancelOrder(Integer.parseInt(id));
				// Set success message
				request.setAttribute("successMessage", "Your order has been cancelled successfully.");
				logger.info("Order cancelled with ID: {}", id);
			}
			// Redirect back to orders page
			request.getRequestDispatcher("orders.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			logger.error("An exception occurred while cancelling the order", e);
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
