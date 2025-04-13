<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.aurionpro.PassbookTransaction"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Passbook</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f4f6f9;
	font-family: 'Arial', sans-serif;
}

.container {
	margin-top: 40px;
}

h2 {
	font-weight: bold;
	color: #343a40;
	margin-bottom: 30px;
}

.table {
	background-color: white;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.btn-secondary {
	background-color: #dc3545; /* Red color */
	color: white;
	text-transform: uppercase;
	font-weight: bold;
	border-radius: 8px;
	padding: 12px;
}

.btn-secondary:hover {
	background-color: darkred;
	color: white;
}

/* Modal styles */
.modal-body {
	font-size: 1.1rem;
}
</style>
</head>
<body class="bg-light">

	<div class="container mt-5">
		<h2 class="text-center mb-4">Your Passbook</h2>

		<!-- ✅ Display Message -->
		<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
			String alertClass = message.contains("❌") ? "alert-danger" : "alert-success";
		%>
		<div class="alert <%=alertClass%> alert-dismissible fade show"
			role="alert">
			<%=message%>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<%
		}
		%>

		<!-- ✅ Display Transactions Table -->
		<div class="table-responsive">
			<table
				class="table table-bordered table-striped text-center align-middle">
				<thead class="table-dark">
					<tr>
						<th>Sender Account</th>
						<th>Receiver Account</th>
						<th>Transaction Type</th>
						<th>Amount</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<PassbookTransaction> transactions = (List<PassbookTransaction>) request.getAttribute("transactions");

					if (transactions == null || transactions.isEmpty()) {
					%>
					<tr>
						<td colspan="5" class="text-danger fw-bold">No transactions
							found!</td>
					</tr>
					<%
					} else {
					for (PassbookTransaction txn : transactions) {
					%>
					<tr>
						<td><%=txn.getSenderAccount()%></td>
						<td><%=txn.getReceiverAccount()%></td>
						<td><%=txn.getTransactionType()%></td>
						<td>₹<%=txn.getAmount()%></td>
						<td><%=txn.getTransactionDate()%></td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>
		</div>

		<!-- ✅ Back Button -->
		<div class="mt-3 text-center">
			<a href="customerDashboard.jsp" class="btn btn-secondary">Back to
				Dashboard</a>
		</div>
	</div>

	<!-- Error Modal Popup (in case no transactions are found) -->
	<div id="errorModal" class="modal" tabindex="-1" role="dialog"
		style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">No Transactions Found</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="closeModal()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>There are no transactions in your passbook. Please try again
						later.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" onclick="closeModal()">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS Bundle -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		// Function to close the modal
		function closeModal() {
			document.getElementById("errorModal").style.display = "none";
		}

		// Function to show the error modal if no transactions are found
		window.onload = function() {
			var transactions =
	<%=(transactions != null ? transactions.size() : 0)%>
		;
			if (transactions === 0) {
				document.getElementById("errorModal").style.display = "block";
			}
		}
	</script>

</body>
</html>