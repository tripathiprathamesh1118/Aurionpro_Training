<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Bank Account</title>
</head>
<body>

	<h2>Add Bank Account</h2>

	<form action="AddBankAccountServlet" method="POST">
		Account Number: <input type="text" name="account_number" required><br>
		<br> Select Account Type: <select name="account_type" required>
			<option value="Debit">Debit</option>
			<option value="Credit">Credit</option>
		</select><br>
		<br> <input type="submit" value="Add Account">
	</form>

	<br>
	<a href="adminDashboard.jsp">Back to Dashboard</a>

</body>
</html>