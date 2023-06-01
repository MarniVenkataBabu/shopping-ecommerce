package com.cglia.servlet;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author venkata.marni
 * @since 23-05-2023
 * @version 1.0 final
 * 
 *
 */
@WebServlet("/generate-otp")
public class GenerateOTPServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Random random = new Random();

	/**
	 * doGet method with request and response as parameters
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");

		// Generate OTP dynamically
		String otp = generateOTP(); // Implement your logic to generate OTP
		request.setAttribute("otpMessage", otp);
		request.getSession().setAttribute("otp", otp);
		request.getRequestDispatcher("forget-password.jsp").forward(request, response);
	}

	/**
	 * generateOTP() method to generate OTP using Random class and nextInt() method
	 * converting otpValue from integer to String with String.valueOf() method in
	 * String class
	 * 
	 * @return generated OTP in the form of String
	 */
	private String generateOTP() {
		int otpValue = 1000 + random.nextInt(9000); // Generate a random 4-digit OTP
		return String.valueOf(otpValue);
	}
}
