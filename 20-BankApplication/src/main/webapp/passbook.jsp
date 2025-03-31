<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.aurionpro.PassbookTransaction"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Passbook</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: center;
}

th {
	background-color: lightgray;
}
</style>
</head>
<body>

	<h2>Your Passbook</h2>

	<!-- ✅ Display Error Messages -->
	<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	<p style="color: red; font-weight: bold;"><%=message%></p>
	<%
	}
	%>

	<!-- ✅ Display Transactions -->
	<table>
		<tr>
			<th>Sender Account</th>
			<th>Receiver Account</th>
			<th>Transaction Type</th>
			<th>Amount</th>
			<th>Date</th>
		</tr>

		<%
		List<PassbookTransaction> transactions = (List<PassbookTransaction>) request.getAttribute("transactions");

		if (transactions == null || transactions.isEmpty()) {
		%>
		<tr>
			<td colspan="5">No transactions found!</td>
		</tr>
		<%
		} else {
		for (PassbookTransaction txn : transactions) {
		%>
		<tr>
			<td><%=txn.getSenderAccount()%></td>
			<td><%=txn.getReceiverAccount()%></td>
			<td><%=txn.getTransactionType()%></td>
			<td><%=txn.getAmount()%></td>
			<td><%=txn.getTransactionDate()%></td>
		</tr>
		<%
		}
		}
		%>

	</table>

	<br>
	<a href="customerDashboard.jsp">Back to Dashboard</a>

</body>
</html>