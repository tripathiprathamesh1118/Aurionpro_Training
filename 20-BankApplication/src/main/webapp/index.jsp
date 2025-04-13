<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap_assets/css/bootstrap.min.css">

    <!-- Optional Bootstrap JS Bundle (if needed) -->
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

        .login-container {
            background-color: #ffffff;
            padding: 50px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            border: 1px solid #e0e0e0;
        }

        .login-container h3 {
            font-weight: bold;
            color: #343a40;
            margin-bottom: 25px;
        }

        .form-label {
            font-weight: 500;
            color: #495057;
        }

        /* Increase size of form controls */
        .form-control, .form-select {
            border-radius: 8px;
            box-shadow: none;
            padding: 5px; /* Increase padding for bigger input */
            font-size: 1rem; /* Make the text inside input a bit bigger */
        }

        .form-control {
            height: 35px; /* Increase height of the input fields */
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

        /* Custom gap between input fields */
        .form-group {
            margin-bottom: 20px;
        }
    </style>
</head>

<body>

    <div class="login-container">
        <h3 class="text-center">🔐 Login</h3>

        <!-- Error Message Display -->
        <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
        %>
        <div class="alert alert-danger text-center"><%=errorMessage%></div>
        <%
        }
        %>

        <form action="LoginServlet" method="POST">
            <div class="form-group">
                <label class="form-label">Email</label>
                <input type="email" name="email" class="form-control" placeholder="Enter your email" required>
            </div>

            <div class="form-group">
                <label class="form-label">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Enter your password" required>
            </div>

            <div class="form-group">
                <label class="form-label">Role</label>
                <select name="user_type" class="form-select" required>
                    <option value="admin">Admin</option>
                    <option value="customer">Customer</option>
                </select>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-success w-100">Login</button>
            </div>
        </form>

        <div class="text-center mt-3 register-link">
            <a href="register.jsp">Don't have an account? Register here</a>
        </div>
    </div>

</body>
</html>
