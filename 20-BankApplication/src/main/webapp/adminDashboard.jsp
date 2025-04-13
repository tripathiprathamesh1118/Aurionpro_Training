<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="bootstrap_assets/css/bootstrap.min.css">

<!-- Bootstrap JS -->
<script src="bootstrap_assets/js/jquery.min.js"></script>
<script src="bootstrap_assets/js/popper.min.js"></script>
<script src="bootstrap_assets/js/bootstrap.min.js"></script>

<style>
body {
	background-color: #f4f6f9;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	font-family: 'Arial', sans-serif;
}

.dashboard-container {
	background-color: white;
	padding: 40px;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 600px;
	text-align: center;
}

.dashboard-container h3 {
	font-weight: bold;
	color: #343a40;
	margin-bottom: 30px;
}

.btn-block {
	width: 100%;
	margin-bottom: 15px;
	font-weight: 600;
	padding: 12px;
	border-radius: 8px;
	text-transform: uppercase;
}

/* Default button styles */
.btn-outline {
	border-width: 2px;
	border-color: #28a745; /* Green border for all buttons */
	color: white; /* Green text for all buttons */
}

.btn-outline:hover {
	background-color: #28a745; /* Green background on hover */
	color: white; /* White text on hover */
}

/* Red color for Logout button */
.btn-danger {
	border-color: #dc3545; /* Red border */
	background-color: #dc3545; /* Red background */
	color: white; /* White text */
}

.btn-danger:hover {
	background-color: #c82333; /* Darker red on hover */
	border-color: #bd2130; /* Darker red border on hover */
}

hr.my-4 {
	border-color: #e0e0e0;
}
</style>
</head>

<body>

	<div class="dashboard-container">
		<h3>Welcome Admin</h3>

		<!-- Buttons with green hover effect -->
		<a href="register.jsp" class="btn btn-outline btn-block">Add New
			Customer</a> <a href="addBankAccount.jsp"
			class="btn btn-outline btn-block">Add Bank Account</a> <a
			href="ViewCustomerServlet" class="btn btn-outline btn-block">View
			Customers</a> <a href="viewTransaction.jsp"
			class="btn btn-outline btn-block">View Transactions</a>

		<hr class="my-4">

		<!-- Logout button in red -->
		<a href="index.jsp" class="btn btn-danger btn-block">Logout</a>
	</div>

</body>

</html>
