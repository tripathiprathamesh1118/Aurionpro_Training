<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.Transaction"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Transactions (Admin)</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	background-color: #f2f4f8;
}

.container {
	margin-top: 40px;
}

.table {
	background-color: white;
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.btn-danger {
	border-radius: 10px;
	font-weight: bold;
}
</style>
</head>
<body>

	<div class="container">
		<h2 class="text-center mb-4">View Transactions (Admin)</h2>

		<form action="ViewTransactionServlet" method="post"
			class="row g-3 justify-content-center">
			<div class="col-md-6">
				<label for="accountNumber" class="form-label">Enter Account
					Number:</label> <input type="text" class="form-control" id="accountNumber"
					name="accountNumber" required>
			</div>
			<div class="col-auto mt-4">
				<button type="submit" class="btn btn-primary">Search</button>
			</div>
		</form>

		<hr>

		<%
		String errorMessage = (String) request.getAttribute("errorMessage");
		if (errorMessage != null) {
		%>
		<div class="alert alert-danger text-center mt-3"><%=errorMessage%></div>
		<%
		}
		%>

		<%
		List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
		Double balance = (Double) request.getAttribute("balance");
		String accountNumber = (String) request.getAttribute("accountNumber");

		if (transactions != null && !transactions.isEmpty()) {
		%>
		<h4 class="text-center mt-4">
			Transactions for Account: <span class="text-primary"><%=accountNumber%></span>
		</h4>

		<div class="table-responsive mt-3">
			<table class="table table-bordered text-center align-middle">
				<thead class="table-dark">
					<tr>
						<th>Sender</th>
						<th>Receiver</th>
						<th>Amount</th>
						<th>Account Type</th>
						<th>Transaction Type</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Transaction txn : transactions) {
					%>
					<tr>
						<td><%=txn.getSenderAccount()%></td>
						<td><%=txn.getReceiverAccount()%></td>
						<td>₹<%=txn.getAmount()%></td>
						<td><%=txn.getAccountType()%></td>
						<td><%=txn.getTransactionType()%></td>
						<td><%=txn.getTransactionDate()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>

		<div class="text-center mt-3">
			<h5 class="text-success">
				Current Balance: ₹<%=balance%></h5>
		</div>

		<%
		} else if (accountNumber != null) {
		%>
		<div class="alert alert-warning text-center mt-4">
			No transactions found for Account: <strong><%=accountNumber%></strong>
		</div>
		<%
		}
		%>

		<div class="text-center mt-4">
			<a href="adminDashboard.jsp" class="btn btn-danger">Back to
				Dashboard</a>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>