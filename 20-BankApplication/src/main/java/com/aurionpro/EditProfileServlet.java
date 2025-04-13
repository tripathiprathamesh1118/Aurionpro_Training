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

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Handle fetching existing user details
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("accountNumber") == null) {
			request.setAttribute("errorMessage", "You are not logged in. Please log in again.");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}

		String accountNumber = (String) session.getAttribute("accountNumber");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi",
					"111803");

			PreparedStatement ps = con.prepareStatement(
					"SELECT first_name, last_name, password FROM customers WHERE id = (SELECT id FROM account_types WHERE account_number = ?)");
			ps.setString(1, accountNumber);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				request.setAttribute("firstName", rs.getString("first_name"));
				request.setAttribute("lastName", rs.getString("last_name"));
				request.setAttribute("password", rs.getString("password"));
			} else {
				request.setAttribute("errorMessage", "Account not found!");
			}

			RequestDispatcher rd = request.getRequestDispatcher("editProfile.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error occurred: " + e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("editProfile.jsp");
			rd.forward(request, response);
		}
	}

	// Handle profile update
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("accountNumber") == null) {
			request.setAttribute("errorMessage", "Session expired. Please log in again.");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}

		String accountNumber = (String) session.getAttribute("accountNumber");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi",
					"111803");

			PreparedStatement ps = con.prepareStatement(
					"UPDATE customers SET first_name=?, last_name=?, password=? WHERE id = (SELECT id FROM account_types WHERE account_number = ?)");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, password);
			ps.setString(4, accountNumber);

			int count = ps.executeUpdate();

			if (count > 0) {
				pw.println("<h3>Profile Updated Successfully!</h3>");
			} else {
				pw.println("<h3>Update Failed!</h3>");
			}

			RequestDispatcher rd = request.getRequestDispatcher("customerDashboard.jsp");
			rd.include(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h3>Error Occurred: " + e.getMessage() + "</h3>");
			RequestDispatcher rd = request.getRequestDispatcher("customerDashboard.jsp");
			rd.include(request, response);
		}
	}
}