package com.aurionpro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MakeTransactionServlet")
public class MakeTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("accountNumber") == null) {
			response.sendRedirect("index.jsp"); // Redirect if session expired
			return;
		}

		String senderAccount = (String) session.getAttribute("accountNumber"); // Get sender's account number
		String receiverAccount = request.getParameter("receiverAccount");
		double amount = Double.parseDouble(request.getParameter("amount"));
		double charge = 0.0;
		String accountType = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi", "111803");

			// ✅ *Corrected Query: Fetch sender's account type from `account_types` table*
			String fetchAccountTypeQuery = "SELECT account_type FROM account_types WHERE account_number = ?";
			ps = con.prepareStatement(fetchAccountTypeQuery);
			ps.setString(1, senderAccount);
			rs = ps.executeQuery();

			if (rs.next()) {
				accountType = rs.getString("account_type");
			} else {
				request.setAttribute("message", "❌ Account Type Not Found!");
				RequestDispatcher rd = request.getRequestDispatcher("makeTransaction.jsp");
				rd.forward(request, response);
				return;
			}

			// ✅ *Apply charge if sender has a Credit account*
			if ("credit".equalsIgnoreCase(accountType)) {
				charge = amount * 0.02; // 2% charge for credit transactions
			}

			// ✅ *Check sender's balance*
			String balanceQuery = "SELECT balance FROM customers WHERE account_number = ?";
			ps = con.prepareStatement(balanceQuery);
			ps.setString(1, senderAccount);
			rs = ps.executeQuery();

			double senderBalance = 0.0;
			if (rs.next()) {
				senderBalance = rs.getDouble("balance");
			}

			double totalDeduction = amount + charge;
			if (senderBalance < totalDeduction) {
				request.setAttribute("message", "❌ Insufficient Balance!");
				RequestDispatcher rd = request.getRequestDispatcher("makeTransaction.jsp");
				rd.forward(request, response);
				return;
			}

			// ✅ *Deduct amount from sender's balance*
			String updateSenderBalance = "UPDATE customers SET balance = balance - ? WHERE account_number = ?";
			ps = con.prepareStatement(updateSenderBalance);
			ps.setDouble(1, totalDeduction);
			ps.setString(2, senderAccount);
			ps.executeUpdate();

			// ✅ *Add amount to receiver's balance*
			String updateReceiverBalance = "UPDATE customers SET balance = balance + ? WHERE account_number = ?";
			ps = con.prepareStatement(updateReceiverBalance);
			ps.setDouble(1, amount); // Only transfer actual amount (no extra charges)
			ps.setString(2, receiverAccount);
			ps.executeUpdate();

			// ✅ *Record Transaction in Database*
			String insertTransaction = "INSERT INTO transactions (sender_account, receiver_account, amount, account_type, transaction_date) VALUES (?, ?, ?, ?, ?)";
			ps = con.prepareStatement(insertTransaction);
			ps.setString(1, senderAccount);
			ps.setString(2, receiverAccount);
			ps.setDouble(3, amount);
			ps.setString(4, accountType);
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();

			// ✅ Display Success Message
			request.setAttribute("message", "✅ Transaction Successful!");
			RequestDispatcher rd = request.getRequestDispatcher("makeTransaction.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "❌ Transaction Failed! Error: " + e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("makeTransaction.jsp");
			rd.forward(request, response);
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