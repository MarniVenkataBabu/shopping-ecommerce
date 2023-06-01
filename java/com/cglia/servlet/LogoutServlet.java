package com.cglia.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author venkata.marni
 *@since 22-05-2023
 *@version 1.0
 */
@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(LogoutServlet.class);
    
    /**
	 * doGet method with request and response objects
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * 
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()) {
            if (request.getSession().getAttribute("auth") != null) {
                request.getSession().removeAttribute("auth");
                request.setAttribute("message", "You have successfully logged out.");
                logger.info("User logged out successfully.");
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }catch (Exception e) {
            logger.error("An error occurred during logout: {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
