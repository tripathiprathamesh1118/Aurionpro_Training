<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
</head>
<body>
    <h2>Welcome to Your Dashboard</h2>

    <ul>
        <li><a href="PassbookServlet">View Passbook</a></li>
        <li><a href="EditProfileServlet">Edit Profile</a></li>
        <li><a href="makeTransaction.jsp">Make Transaction</a></li>
    </ul>

    <br><br>
    <a href="index.jsp">Logout</a>
</body>
</html>