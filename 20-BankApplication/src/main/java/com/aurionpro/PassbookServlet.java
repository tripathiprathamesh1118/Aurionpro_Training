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
import javax.servlet.http.HttpSession;

@WebServlet("/PassbookServlet")
public class PassbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("accountNumber") == null) {
			response.sendRedirect("index.jsp"); // Redirect if session expired
			return;
		}

		String senderAccount = (String) session.getAttribute("accountNumber"); // Logged-in user's account number
		List<PassbookTransaction> transactions = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi", "111803");

			// ✅ *Fetch transactions for the logged-in user*
			String fetchTransactions = "SELECT sender_account, receiver_account, amount, account_type, transaction_date "
					+ "FROM transactions WHERE sender_account = ? OR receiver_account = ? "
					+ "ORDER BY transaction_date DESC";
			ps = con.prepareStatement(fetchTransactions);
			ps.setString(1, senderAccount);
			ps.setString(2, senderAccount);
			rs = ps.executeQuery();

			while (rs.next()) {
				PassbookTransaction txn = new PassbookTransaction();
				txn.setSenderAccount(rs.getString("sender_account"));
				txn.setReceiverAccount(rs.getString("receiver_account"));
				txn.setAmount(rs.getDouble("amount"));
				txn.setTransactionType(senderAccount.equals(rs.getString("sender_account")) ? "Debit" : "Credit");
				txn.setTransactionDate(rs.getTimestamp("transaction_date").toString());

				transactions.add(txn);
			}

			request.setAttribute("transactions", transactions);
			RequestDispatcher rd = request.getRequestDispatcher("passbook.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "❌ Error Loading Passbook: " + e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("customerDashboard.jsp");
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