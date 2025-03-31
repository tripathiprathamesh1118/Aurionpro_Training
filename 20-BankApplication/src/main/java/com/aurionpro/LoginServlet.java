package com.aurionpro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Retrieve form data
		String email = request.getParameter("email"); // Login using Email ID
		String password = request.getParameter("password");
		String userType = request.getParameter("user_type"); // Matches the select input in JSP

		HttpSession session = request.getSession(); // Create session for login

		// Validate input fields
		if (email == null || password == null || userType == null || email.isEmpty() || password.isEmpty()) {
			request.setAttribute("errorMessage", "All fields are required!");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}

		try {
			// Load MySQL driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi",
					"111803");

			// Select query based on user type
			String query = "";
			if ("admin".equals(userType)) {
				query = "SELECT * FROM admin WHERE email = ? AND password = ?";
			} else if ("customer".equals(userType)) {
				query = "SELECT * FROM customers WHERE email = ? AND password = ?";
			} else {
				request.setAttribute("errorMessage", "Invalid User Type!");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				return;
			}

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Store user details in session
				session.setAttribute("user_email", email);
				session.setAttribute("user_type", userType);

				// If customer, store account number in session
				if ("customer".equals(userType)) {
					String accountNumber = rs.getString("account_number");
					session.setAttribute("accountNumber", accountNumber);
					response.sendRedirect("customerDashboard.jsp");
				} else {
					response.sendRedirect("adminDashboard.jsp");
				}

			} else {
				// Login failed, show error on index.jsp
				request.setAttribute("errorMessage", "Invalid Email or Password or wrong role! Please try again.");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error: " + e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
}