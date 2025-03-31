package com.aurionpro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewCustomerServlet")
public class ViewCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Customer> customerList = new ArrayList<>();

		try {
			// Database connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi", "111803");

			// Updated SQL Query: Fetch customer details along with their account type and balance
			String query = "SELECT c.account_number, c.first_name, c.last_name, c.email, at.account_type, c.balance " +
						   "FROM customers c LEFT JOIN account_types at " +
						   "ON c.account_number = at.account_number";

			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			// Fetch data from ResultSet
			while (rs.next()) {
				Customer customer = new Customer(
					rs.getString("account_number"), 
					rs.getString("first_name"), 
					rs.getString("last_name"), 
					rs.getString("email"), 
					rs.getString("account_type"),
					rs.getDouble("balance")
				);
				customerList.add(customer);
			}

			// Set customer list attribute
			request.setAttribute("customerList", customerList);
			RequestDispatcher rd = request.getRequestDispatcher("viewCustomer.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error fetching customer details.");
			request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
		}
	}
}