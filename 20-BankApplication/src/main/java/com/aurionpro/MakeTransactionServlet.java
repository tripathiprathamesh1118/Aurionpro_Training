package com.aurionpro;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/MakeTransactionServlet")
public class MakeTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("accountNumber") == null) {
			response.sendRedirect("index.jsp");
			return;
		}

		String senderAccount = (String) session.getAttribute("accountNumber");
		String transactionType = request.getParameter("transactionType");
		String receiverAccount = request.getParameter("receiverAccount");
		double amount;

		try {
			amount = Double.parseDouble(request.getParameter("amount"));
			if (amount <= 0) {
				request.setAttribute("message", "❌ Amount must be positive.");
				request.getRequestDispatcher("makeTransaction.jsp").forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			request.setAttribute("message", "❌ Invalid amount.");
			request.getRequestDispatcher("makeTransaction.jsp").forward(request, response);
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi", "111803");

			// Get account type
			String accountType = "";
			ps = con.prepareStatement("SELECT account_type FROM account_types WHERE account_number = ?");
			ps.setString(1, senderAccount);
			rs = ps.executeQuery();
			if (rs.next()) {
				accountType = rs.getString("account_type");
			}
			rs.close();
			ps.close();

			// Get sender's balance
			double senderBalance = 0;
			ps = con.prepareStatement("SELECT balance FROM customers WHERE account_number = ?");
			ps.setString(1, senderAccount);
			rs = ps.executeQuery();
			if (rs.next()) {
				senderBalance = rs.getDouble("balance");
			}
			rs.close();
			ps.close();

			double charge = "credit".equalsIgnoreCase(accountType) ? amount * 0.02 : 0;
			double totalDeduction = amount + charge;

			switch (transactionType.toLowerCase()) {
			case "credit":
				ps = con.prepareStatement("UPDATE customers SET balance = balance + ? WHERE account_number = ?");
				ps.setDouble(1, amount);
				ps.setString(2, senderAccount);
				ps.executeUpdate();
				ps.close();

				// insert transaction
				ps = con.prepareStatement(
						"INSERT INTO transactions (sender_account, receiver_account, amount, account_type, transaction_type, transaction_date) VALUES (?, ?, ?, ?, ?, ?)");
				ps.setString(1, senderAccount);
				ps.setString(2, senderAccount);
				ps.setDouble(3, amount);
				ps.setString(4, accountType);
				ps.setString(5, "credit");
				ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				ps.executeUpdate();
				break;

			case "debit":
				if (senderBalance < totalDeduction) {
					request.setAttribute("message", "❌ Insufficient balance.");
					request.getRequestDispatcher("makeTransaction.jsp").forward(request, response);
					return;
				}

				ps = con.prepareStatement("UPDATE customers SET balance = balance - ? WHERE account_number = ?");
				ps.setDouble(1, totalDeduction);
				ps.setString(2, senderAccount);
				ps.executeUpdate();
				ps.close();

				// insert transaction
				ps = con.prepareStatement(
						"INSERT INTO transactions (sender_account, receiver_account, amount, account_type, transaction_type, transaction_date) VALUES (?, ?, ?, ?, ?, ?)");
				ps.setString(1, senderAccount);
				ps.setString(2, senderAccount);
				ps.setDouble(3, amount);
				ps.setString(4, accountType);
				ps.setString(5, "debit");
				ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				ps.executeUpdate();
				break;

			case "transfer":
				if (senderBalance < totalDeduction) {
					request.setAttribute("message", "❌ Insufficient balance.");
					request.getRequestDispatcher("makeTransaction.jsp").forward(request, response);
					return;
				}

				// deduct sender
				ps = con.prepareStatement("UPDATE customers SET balance = balance - ? WHERE account_number = ?");
				ps.setDouble(1, totalDeduction);
				ps.setString(2, senderAccount);
				ps.executeUpdate();
				ps.close();

				// credit receiver
				ps = con.prepareStatement("UPDATE customers SET balance = balance + ? WHERE account_number = ?");
				ps.setDouble(1, amount);
				ps.setString(2, receiverAccount);
				ps.executeUpdate();
				ps.close();

				// insert transaction
				ps = con.prepareStatement(
						"INSERT INTO transactions (sender_account, receiver_account, amount, account_type, transaction_type, transaction_date) VALUES (?, ?, ?, ?, ?, ?)");
				ps.setString(1, senderAccount);
				ps.setString(2, receiverAccount);
				ps.setDouble(3, amount);
				ps.setString(4, accountType);
				ps.setString(5, "transfer");
				ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				ps.executeUpdate();
				break;

			default:
				request.setAttribute("message", "❌ Invalid transaction type.");
				request.getRequestDispatcher("makeTransaction.jsp").forward(request, response);
				return;
			}

			request.setAttribute("message", "✅ " + transactionType + " successful.");
			request.getRequestDispatcher("makeTransaction.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "❌ Transaction failed. " + e.getMessage());
			request.getRequestDispatcher("makeTransaction.jsp").forward(request, response);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}