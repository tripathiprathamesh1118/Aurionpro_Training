<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.servlet.http.*, javax.servlet.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Make Transaction</title>
</head>
<body>

	<h2>Make a Transaction</h2>

	<%
	HttpSession sessionObj = request.getSession(false);
	String senderAccount = (String) sessionObj.getAttribute("accountNumber");

	if (senderAccount == null) {
		response.sendRedirect("customerDashboard.jsp"); // Redirect if not logged in
		return;
	}

	String accountType = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi", "111803");

		// ✅ *Corrected Query: Fetch account type from `account_types` table*
		String fetchAccountTypeQuery = "SELECT account_type FROM account_types WHERE account_number = ?";
		ps = con.prepareStatement(fetchAccountTypeQuery);
		ps.setString(1, senderAccount);
		rs = ps.executeQuery();

		if (rs.next()) {
			accountType = rs.getString("account_type");
		} else {
			accountType = "Unknown";
		}

	} catch (Exception e) {
		e.printStackTrace();
		accountType = "Error Fetching Type";
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
	%>

	<!-- ✅ Display Messages -->
	<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	<p style="color: green; font-weight: bold;"><%=message%></p>
	<%
	}
	%>

	<!-- ✅ Transaction Form -->
	<form action="MakeTransactionServlet" method="POST">
		<label>Sender Account Number:</label> <input type="text"
			name="senderAccount" value="<%=senderAccount%>" readonly><br>
		<br> <label>Account Type:</label> <input type="text"
			name="accountType" value="<%=accountType%>" readonly><br>
		<br> <label>Receiver's Account Number:</label> <input type="text"
			name="receiverAccount" required><br>
		<br> <label>Amount:</label> <input type="number" name="amount"
			required><br>
		<br> <input type="submit" value="Transfer Money">
	</form>

	<br>
	<a href="customerDashboard.jsp">Back to Dashboard</a>

</body>
</html>