<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.aurionpro.Customer"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Customers</title>
</head>
<body>

	<h2>Customer Details</h2>

	<table border="1">
		<tr>
			<th>Account Number</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Account Type</th>
			<th>Balance</th>
		</tr>

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
			<td><%=customer.getBalance()%></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="6">No customers found.</td>
		</tr>
		<%
		}
		%>
	</table>

	<br>
	<a href="adminDashboard.jsp">Back to Dashboard</a>

</body>
</html>