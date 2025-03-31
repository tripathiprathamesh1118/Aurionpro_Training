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

@WebServlet("/AddBankAccountServlet")
public class AddBankAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String accountNumber = request.getParameter("account_number");
		String accountType = request.getParameter("account_type");

		try {
			// Database connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi",
					"111803");

			// Check if account number exists
			PreparedStatement checkAccount = con.prepareStatement("SELECT * FROM customers WHERE account_number = ?");
			checkAccount.setString(1, accountNumber);
			ResultSet rs = checkAccount.executeQuery();

			if (!rs.next()) {
				pw.println("<h3>Error: Account number does not exist!</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("addBankAccount.jsp");
				rd.include(request, response);
				return;
			}

			// Insert account type into the database
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO account_types (account_number, account_type) VALUES (?, ?)");
			ps.setString(1, accountNumber);
			ps.setString(2, accountType);

			int count = ps.executeUpdate();

			if (count > 0) {
				pw.println("<h3>Bank Account Added Successfully!</h3>");
			} else {
				pw.println("<h3>Error: Unable to add bank account.</h3>");
			}

			RequestDispatcher rd = request.getRequestDispatcher("adminDashboard.jsp");
			rd.include(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h3>Error: " + e.getMessage() + "</h3>");
			RequestDispatcher rd = request.getRequestDispatcher("addBankAccount.jsp");
			rd.include(request, response);
		}
	}
}