package com.aurionpro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmedPassword = request.getParameter("confirm_password");

		// Check if passwords match
		if (!password.equals(confirmedPassword)) {
			pw.println("<h3 style='color:red;'>Passwords do not match!</h3>");
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.include(request, response);
			return;
		}

		// Generate an 8-digit account number
		String accountNumber = generateAccountNumber();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi",
					"111803");
			con.setAutoCommit(true); // Ensure auto-commit is enabled

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO customers (account_number, first_name, last_name, email, password, balance) VALUES (?, ?, ?, ?, ?, ?)");

			ps.setString(1, accountNumber);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, email);
			ps.setString(5, password);
			ps.setDouble(6, 0.00);

			int count = ps.executeUpdate();

			if (count > 0) {
				pw.println("<h3 style='color:green;'>Registration successful!</h3>");
				pw.println("<h4>Your account number is: <b>" + accountNumber + "</b></h4>");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			} else {
				pw.println("<h3 style='color:red;'>Registration failed!</h3>");
				pw.println("<h4>Account may already exist or invalid details entered.</h4>");
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.include(request, response);
		}
	}

	// Generate 8-digit Account Number
	private String generateAccountNumber() {
		Random random = new Random();
		int accountNumber = 10000000 + random.nextInt(90000000);
		return String.valueOf(accountNumber);
	}
}