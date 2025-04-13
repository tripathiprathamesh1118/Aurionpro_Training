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

@WebServlet("/ViewTransactionServlet")
public class ViewTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accountNumber = request.getParameter("accountNumber");
		List<Transaction> transactions = new ArrayList<>();
		double balance = 0.0;

		if (accountNumber == null || accountNumber.trim().isEmpty()) {
			request.setAttribute("errorMessage", "Please enter a valid account number.");
			RequestDispatcher rd = request.getRequestDispatcher("viewTransaction.jsp");
			rd.forward(request, response);
			return;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi",
					"111803");

			String query = "SELECT sender_account, receiver_account, amount, account_type, transaction_type, transaction_date "
					+ "FROM transactions WHERE sender_account = ? OR receiver_account = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, accountNumber);
			ps.setString(2, accountNumber);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getString("sender_account"),
						rs.getString("receiver_account"), rs.getDouble("amount"), rs.getString("account_type"),
						rs.getString("transaction_type"), rs.getTimestamp("transaction_date"));
				transactions.add(transaction);
			}

			String balanceQuery = "SELECT balance FROM customers WHERE account_number = ?";
			PreparedStatement psBalance = con.prepareStatement(balanceQuery);
			psBalance.setString(1, accountNumber);
			ResultSet rsBalance = psBalance.executeQuery();
			if (rsBalance.next()) {
				balance = rsBalance.getDouble("balance");
			}

			request.setAttribute("transactions", transactions);
			request.setAttribute("balance", balance);
			request.setAttribute("accountNumber", accountNumber);

			RequestDispatcher rd = request.getRequestDispatcher("viewTransaction.jsp");
			rd.forward(request, response);

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error retrieving transactions: " + e.getMessage());
			request.getRequestDispatcher("viewTransaction.jsp").forward(request, response);
		}
	}
}