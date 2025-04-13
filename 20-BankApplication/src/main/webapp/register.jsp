<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>

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

.register-container {
	background-color: white;
	padding: 50px;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 500px;
	border: 1px solid #e0e0e0;
}

.register-container h3 {
	font-weight: bold;
	color: #343a40;
	margin-bottom: 25px;
}

.form-label {
	font-weight: 500;
	color: #495057;
}

/* Styling for input fields */
.form-control, .form-select {
	border-radius: 8px;
	padding: 15px;
	font-size: 1rem;
	box-shadow: none;
}

.form-control {
	height: 25px;
}

.d-grid {
	margin-top: 25px;
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
	font-weight: 600;
	padding: 12px;
	border-radius: 8px;
	text-transform: uppercase;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #004085;
}

.alert {
	border-radius: 8px;
}

.register-link {
	font-size: 0.95rem;
	color: #007bff;
}

.register-link a {
	text-decoration: none;
}

.register-link a:hover {
	text-decoration: underline;
}

.form-group {
	margin-bottom: 20px;
}
</style>

<script>
	function validateForm() {
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirm_password").value;
		if (password !== confirmPassword) {
			alert("Passwords do not match!");
			return false;
		}
		return true;
	}
</script>

</head>

<body>

	<div class="register-container">

		<h3 class="text-center mb-4">Customer Registration</h3>

		<form action="RegisterServlet" method="POST"
			onsubmit="return validateForm()">

			<div class="form-group">
				<label class="form-label">First Name</label> <input type="text"
					name="first_name" class="form-control"
					placeholder="Enter your first name" required>
			</div>

			<div class="form-group">
				<label class="form-label">Last Name</label> <input type="text"
					name="last_name" class="form-control"
					placeholder="Enter your last name" required>
			</div>

			<div class="form-group">
				<label class="form-label">Email</label> <input type="email"
					name="email" class="form-control" placeholder="Enter your email"
					required>
			</div>

			<div class="form-group">
				<label class="form-label">Password</label> <input type="password"
					id="password" name="password" class="form-control"
					placeholder="Enter your password" required>
			</div>

			<div class="form-group">
				<label class="form-label">Confirm Password</label> <input
					type="password" id="confirm_password" name="confirm_password"
					class="form-control" placeholder="Confirm your password" required>
			</div>

			<div class="d-grid">
				<button type="submit" class="btn btn-primary btn-block">Register</button>
			</div>

		</form>

		<div class="text-center mt-4 register-link">
			<a href="index.jsp">Back to Login</a>
		</div>

	</div>

</body>

</html>
