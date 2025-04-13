<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer Dashboard</title>

<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Bootstrap 5 JS (including Popper.js and Bootstrap JS) -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<style>
body {
	background-color: #f0f2f5;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	margin: 0;
}

.dashboard-container {
	background-color: white;
	padding: 40px;
	border-radius: 12px;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 500px;
	text-align: center;
}

h3 {
	font-size: 1.8rem;
	font-weight: 600;
	color: #333;
}

.btn-block {
	width: 100%;
	margin-bottom: 15px;
}

/* Blue color for the buttons */
.btn-primary {
	background-color: #007bff; /* Blue background */
	border-color: #007bff; /* Blue border */
	color: white; /* White text */
}

/* Hover effect for blue buttons turning green */
.btn-primary:hover {
	background-color: #28a745; /* Green background */
	border-color: #28a745; /* Green border */
	color: white; /* White text */
}

/* Red color for Logout button */
.btn-danger {
	background-color: #dc3545;
	color: white;
}

.btn-danger:hover {
	background-color: #c82333;
	color: white;
}

hr.my-4 {
	border-color: #e0e0e0;
}
</style>
</head>
<body>

	<div class="dashboard-container">
		<h3 class="mb-4">Welcome to Your Dashboard</h3>

		<!-- View Passbook button with blue background -->
		<a href="PassbookServlet" class="btn btn-primary btn-block">View
			Passbook</a>

		<!-- Edit Profile button with blue background -->
		<a href="EditProfileServlet" class="btn btn-primary btn-block">Edit
			Profile</a>

		<!-- Make Transaction button with blue background -->
		<a href="makeTransaction.jsp" class="btn btn-primary btn-block">Make
			Transaction</a>

		<hr class="my-4">

		<!-- Logout button in red -->
		<a href="index.jsp" class="btn btn-danger btn-block">Logout</a>
	</div>

</body>
</html>
