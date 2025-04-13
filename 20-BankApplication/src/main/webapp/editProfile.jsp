<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="bootstrap_assets/css/bootstrap.min.css">

<!-- Bootstrap JS -->
<script src="bootstrap_assets/js/jquery.min.js"></script>
<script src="bootstrap_assets/js/popper.min.js"></script>
<script src="bootstrap_assets/js/bootstrap.min.js"></script>

<style>
body {
	background-color: #f4f6f9;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	font-family: 'Arial', sans-serif;
}

.form-container {
	background-color: white;
	padding: 50px;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 500px;
	border: 1px solid #e0e0e0;
}

.form-container h2 {
	font-weight: bold;
	color: #343a40;
	margin-bottom: 25px;
	text-align: center;
}

.form-label {
	font-weight: 500;
	color: #495057;
}

.form-control {
	border-radius: 8px;
	padding: 15px;
	font-size: 1rem;
	box-shadow: none;
}

.d-grid {
	margin-top: 25px;
}

.btn-success {
	background-color: #28a745;
	border-color: #28a745;
	font-weight: 600;
	padding: 12px;
	border-radius: 8px;
	text-transform: uppercase;
}

.btn-success:hover {
	background-color: #218838;
	border-color: #1e7e34;
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

.form-group {
	margin-bottom: 20px;
}

/* Center the Back to Dashboard Button */
.text-center {
	text-align: center;
}
</style>

</head>
<body>

	<%
	HttpSession userSession = request.getSession(false);
	String accountNumber = (String) userSession.getAttribute("accountNumber");
	String firstName = (String) request.getAttribute("firstName");
	String lastName = (String) request.getAttribute("lastName");
	String password = (String) request.getAttribute("password");

	if (accountNumber == null) {
	%>

	<div class="form-container">
		<h2>Error</h2>
		<p class="text-danger">You are not logged in properly. Please log
			in again.</p>
		<a href="index.jsp" class="btn btn-primary btn-block">Go to Login</a>
	</div>

	<%
	} else {
	%>

	<div class="form-container">
		<h2>Edit Your Profile</h2>

		<form action="EditProfileServlet" method="POST">
			<input type="hidden" name="account_number" value="<%=accountNumber%>">

			<div class="form-group">
				<label class="form-label">First Name:</label> <input type="text"
					class="form-control" name="first_name"
					value="<%=firstName != null ? firstName : ""%>" required>
			</div>

			<div class="form-group">
				<label class="form-label">Last Name:</label> <input type="text"
					class="form-control" name="last_name"
					value="<%=lastName != null ? lastName : ""%>" required>
			</div>

			<div class="form-group">
				<label class="form-label">New Password:</label> <input
					type="password" class="form-control" name="password"
					value="<%=password != null ? password : ""%>" required>
			</div>

			<div class="d-grid">
				<button type="submit" class="btn btn-success btn-block">Update
					Profile</button>
			</div>
		</form>

		<!-- Centered 'Back to Dashboard' Button -->
		<div class="text-center">
			<a href="customerDashboard.jsp" class="btn btn-secondary btn-block">Back
				to Dashboard</a>
		</div>
	</div>

	<%
	}
	%>

</body>
</html>
