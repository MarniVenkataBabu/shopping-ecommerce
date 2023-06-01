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
import com.cglia.dao.UserDao;

/**
 * 
 * @author venkata.marni
 * @since 22-05-2023
 * @version 1.0
 */
@WebServlet("/register-servlet")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(RegisterServlet.class);

	/**
	 * doPost method of HttpServlet class with request and response objects
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// Validate the name input
			if (!name.matches("[a-zA-Z]+")) {
				String errorMessage = "Please provide a valid name with alphabetic characters only.";
				request.setAttribute("errorMessage", errorMessage);
				request.getRequestDispatcher("registration.jsp").forward(request, response);
				return;
			}
			UserDao userDao = new UserDao(DbCon.getConnection());
			boolean emailExists = userDao.checkEmailExists(email);
			if (emailExists) {
				String message = "You already have an account. Please log in.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				boolean result = userDao.createUser(name, email, password);
				if (result) {
					// Redirect to a index page
					response.sendRedirect("index.jsp");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.error("An error occurred: {}", e.getMessage(), e);
		}
	}
}
