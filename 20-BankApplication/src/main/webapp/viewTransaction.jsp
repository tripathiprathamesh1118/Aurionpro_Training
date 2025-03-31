<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.TransactionDetails"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Transactions (Admin)</title>
</head>
<body>

	<h2>View Transactions</h2>

	<!-- Admin Inputs Account Number -->
	<form action="ViewTransactionServlet" method="post">
		<label for="accountNumber">Enter Account Number:</label> <input
			type="text" id="accountNumber" name="accountNumber" required>
		<input type="submit" value="Search">
	</form>

	<hr>

	<%
	List<TransactionDetails> transactions = (List<TransactionDetails>) request.getAttribute("transactions");
	Double balance = (Double) request.getAttribute("balance");
	String accountNumber = (String) request.getAttribute("accountNumber");
	String errorMessage = (String) request.getAttribute("errorMessage");
	%>

	<%
	if (errorMessage != null) {
	%>
	<p style="color: red;"><%=errorMessage%></p>
	<%
	}
	%>

	<%
	if (transactions != null && !transactions.isEmpty()) {
	%>
	<h3>
		Transactions for Account:
		<%=accountNumber%></h3>
	<table border="1">
		<tr>
			<th>Sender</th>
			<th>Receiver</th>
			<th>Amount</th>
			<th>Account Type</th>
			<th>Date</th>
		</tr>
		<%
		for (TransactionDetails transaction : transactions) {
		%>
		<tr>
			<td><%=transaction.getSenderAccount()%></td>
			<td><%=transaction.getReceiverAccount()%></td>
			<td>₹<%=transaction.getAmount()%></td>
			<td><%=transaction.getAccountType()%></td>
			<td><%=transaction.getTransactionDate()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<h3>
		Current Balance: ₹<%=balance%></h3>
	<%
	} else if (accountNumber != null) {
	%>
	<h3>
		No transactions found for Account:
		<%=accountNumber%></h3>
	<%
	}
	%>

	<br>
	<a href="adminDashboard.jsp">Back to Dashboard</a>

</body>
</html>