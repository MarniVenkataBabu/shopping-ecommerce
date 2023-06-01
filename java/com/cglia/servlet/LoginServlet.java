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
import com.cglia.model.User;

/**
 * 
 * @author venkata.marni
 *@since 22-05-2023
 *@version 1.0
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    
    /**
	 * doPost method with request and response objects
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");
            UserDao udao = new UserDao(DbCon.getConnection());
            User user = udao.userLogin(email, password);
            if (user != null) {
                request.getSession().setAttribute("auth", user);
                response.sendRedirect("index.jsp");
                logger.info("User logged in: {}", email);
            } else {
                String errorMessage = "Incorrect email or password. Please try again.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                logger.error("Login failed for email: {}",  email);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("An error occurred during login: {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
