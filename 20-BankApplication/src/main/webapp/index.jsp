<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<h2>Login</h2>
<% 
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <p style="color: red;"><%= errorMessage %></p>
<% } %>

<form action="LoginServlet" method="POST">
    Email: <input type="text" name="email" required> 
    <br><br>
    Password: <input type="password" name="password" required> 
    <br><br>
    Role: 
    <select name="user_type">
        <option value="admin">Admin</option>
        <option value="customer">Customer</option>
    </select>
    <br><br>
    <input type="submit" value="Login">
</form>

<br><br>
<a href="register.jsp">Register as a new Customer</a>

</body>
</html>