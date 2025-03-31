<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Edit Profile</title>

</head>

<body>



	<h2>Edit Profile</h2>



	<%
	// Fetch session data

	HttpSession userSession = request.getSession(false);

	String accountNumber = (String) userSession.getAttribute("accountNumber");

	String firstName = (String) request.getAttribute("firstName");

	String lastName = (String) request.getAttribute("lastName");

	String password = (String) request.getAttribute("password");

	if (accountNumber == null) {
	%>

	<h3>Error: You are not logged in properly. Please log in again.</h3>

	<a href="index.jsp">Go to Login</a>

	<%
	} else {
	%>

	<h3>Edit Your Details</h3>

	<form action="EditProfileServlet" method="POST">

		<!-- Hidden field to ensure correct account number is used -->

		<input type="hidden" name="account_number" value="<%=accountNumber%>">



		First Name: <input type="text" name="first_name"
			value="<%=firstName != null ? firstName : ""%>"><br>
		<br> Last Name: <input type="text" name="last_name"
			value="<%=lastName != null ? lastName : ""%>"><br>
		<br> New Password: <input type="password" name="password"
			value="<%=password != null ? password : ""%>"><br>
		<br> <input type="submit" value="Update Profile">

	</form>

	<%
	}
	%>



	<br>
	<br>

	<a href="customerDashboard.jsp">Go Back to Dashboard</a>



</body>

</html>