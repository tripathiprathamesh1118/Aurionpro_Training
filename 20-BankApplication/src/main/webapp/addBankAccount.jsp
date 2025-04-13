<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Bank Account</title>

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

.form-container {
	background-color: white;
	padding: 40px;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 500px;
}

.form-container h3 {
	font-weight: bold;
	color: #343a40;
	margin-bottom: 30px;
}

.form-group {
	margin-bottom: 20px;
}

.form-control {
	border-radius: 25px;
	padding: 10px 15px;
	font-size: 1rem;
	box-shadow: none;
	border: 1px solid #ddd;
}

.btn-block {
	width: 100%;
	font-weight: 600;
	padding: 12px;
	border-radius: 8px;
	text-transform: uppercase;
}

.btn-primary {
	border-color: #28a745;
	color: white;
}

.btn-primary:hover {
	background-color: #28a745;
	color: white;
}

.btn-danger {
	background-color: red;
	color: white;
	border-color: red;
}

.gap-btn {
	margin-top: 20px;
}

/* Modal styles */
.modal-body {
	font-size: 1.1rem;
}
</style>

<script>
	// JavaScript for form validation and popup display
	function validateForm() {
		// Get the account number
		var accountNumber = document
				.querySelector('input[name="account_number"]').value;
		var accountType = document.querySelector('select[name="account_type"]').value;

		// Check if the account number is empty or invalid (basic validation for now)
		if (accountNumber === "" || accountType === "") {
			// Show the modal with the error message
			document.getElementById("errorModal").style.display = "block";
			return false; // prevent form submission
		}

		return true; // allow form submission
	}

	// Close the modal
	function closeModal() {
		document.getElementById("errorModal").style.display = "none";
	}
</script>

</head>

<body>

	<div class="form-container">
		<h3 class="mb-4 text-center">Add Bank Account</h3>

		<!-- Form with validation -->
		<form action="AddBankAccountServlet" method="POST"
			onsubmit="return validateForm()">
			<div class="form-group">
				<label>Account Number</label> <input type="text"
					name="account_number" class="form-control" required>
			</div>

			<div class="form-group">
				<label>Select Account Type</label> <select name="account_type"
					class="form-control" required>
					<option value="">Select...</option>
					<option value="Debit">Debit</option>
					<option value="Credit">Credit</option>
				</select>
			</div>

			<div class="d-grid">
				<button type="submit" class="btn btn-primary btn-block">Add
					Account</button>
			</div>
		</form>

		<!-- Updated Back to Dashboard button with red color and white text -->
		<div class="text-center gap-btn">
			<a href="adminDashboard.jsp" class="btn btn-danger btn-block"
				style="color: white; text-align: center;">Back to Dashboard</a>
		</div>
	</div>

	<!-- Error Modal Popup -->
	<div id="errorModal" class="modal" tabindex="-1" role="dialog"
		style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Error</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" onclick="closeModal()">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Please fill in all fields correctly before submitting.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" onclick="closeModal()">Close</button>
				</div>
			</div>
		</div>
	</div>

</body>

</html>
