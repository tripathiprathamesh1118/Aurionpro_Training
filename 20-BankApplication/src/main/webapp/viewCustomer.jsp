<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.Customer"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>View Customers</title>

<!-- Bootstrap CSS -->
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
	background-color: red;
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
<body>

	<div class="container">
		<h2 class="text-center">Customer Details</h2>

		<div class="table-responsive">
			<table
				class="table table-bordered table-striped text-center align-middle">
				<thead class="table-dark">
					<tr>
						<th>Account Number</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Account Type</th>
						<th>Balance</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Customer> customerList = (List<Customer>) request.getAttribute("customerList");
					if (customerList != null && !customerList.isEmpty()) {
						for (Customer customer : customerList) {
					%>
					<tr>
						<td><%=customer.getAccountNumber()%></td>
						<td><%=customer.getFirstName()%></td>
						<td><%=customer.getLastName()%></td>
						<td><%=customer.getEmail()%></td>
						<td><%=customer.getAccountType()%></td>
						<td>₹<%=customer.getBalance()%></td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="6" class="text-danger fw-bold">No customers
							found.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>

		<div class="mt-3 text-center">
			<a href="adminDashboard.jsp" class="btn btn-secondary">Back to
				Dashboard</a>
		</div>
	</div>

	<!-- Error Modal Popup (in case no customers are found) -->
	<div id="errorModal" class="modal" tabindex="-1" role="dialog"
		style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">No Customers Found</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="closeModal()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>There are no customers in the database. Please add customers
						first.</p>
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

		// Function to show the error modal if no customers are found
		window.onload = function() {
			var customerList =
	<%=(customerList != null ? customerList.size() : 0)%>
		;
			if (customerList === 0) {
				document.getElementById("errorModal").style.display = "block";
			}
		}
	</script>

</body>
</html>
