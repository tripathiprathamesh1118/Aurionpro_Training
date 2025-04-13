<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.servlet.http.*, javax.servlet.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Make Transaction</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="bootstrap_assets/css/bootstrap.min.css">
<!-- Bootstrap JS -->
<script src="bootstrap_assets/js/jquery.min.js"></script>
<script src="bootstrap_assets/js/popper.min.js"></script>
<script src="bootstrap_assets/js/bootstrap.min.js"></script>

<style>
/* General body and container styles */
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
    padding: 40px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 600px;
    border: 1px solid #e0e0e0;
}

h2 {
    font-weight: bold;
    color: #343a40;
    margin-bottom: 20px;
    text-align: center;
}

/* Styling for input fields */
.form-control {
    border-radius: 8px;
    padding: 15px;
    font-size: 1rem;
    box-shadow: none;
}

.form-group {
    margin-bottom: 20px;
}

/* Button styling */
.btn-block {
    padding: 12px;
    border-radius: 8px;
    text-transform: uppercase;
    font-weight: 600;
}

.btn-success {
    background-color: #28a745;
    border-color: #28a745;
}

.btn-success:hover {
    background-color: #218838;
    border-color: #1e7e34;
}

.btn-danger {
    background-color: #dc3545;
    border-color: #dc3545;
}

.btn-danger:hover {
    background-color: #c82333;
    border-color: #bd2130;
}

.text-center {
    text-align: center;
}

.alert {
    border-radius: 8px;
}

/* Remove underline for links */
a {
    text-decoration: none;
}

a:hover {
    text-decoration: none;
}

</style>

</head>

<body>
<%
    HttpSession sessionObj = request.getSession(false);
    String senderAccount = (String) sessionObj.getAttribute("accountNumber");

    if (senderAccount == null) {
        response.sendRedirect("customerDashboard.jsp");
        return;
    }

    String accountType = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "tripathi", "111803");

        String fetchAccountTypeQuery = "SELECT account_type FROM account_types WHERE account_number = ?";
        ps = con.prepareStatement(fetchAccountTypeQuery);
        ps.setString(1, senderAccount);
        rs = ps.executeQuery();

        if (rs.next()) {
            accountType = rs.getString("account_type");
        } else {
            accountType = "Unknown";
        }
    } catch (Exception e) {
        e.printStackTrace();
        accountType = "Error Fetching Type";
    } finally {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (con != null) con.close();
    }
%>

<div class="form-container">
    <h2>Make a Transaction</h2>

    <%
    String message = (String) request.getAttribute("message");
    if (message != null) {
    %>
        <div class="alert alert-success"><%=message %></div>
    <% } %>

    <form action="MakeTransactionServlet" method="POST">
        <div class="form-group">
            <label>Sender Account Number:</label>
            <input type="text" class="form-control" name="senderAccount" value="<%=senderAccount%>" readonly>
        </div>

        <div class="form-group">
            <label>Account Type:</label>
            <input type="text" class="form-control" name="accountType" value="<%=accountType%>" readonly>
        </div>

        <div class="form-group">
            <label>Transaction Type:</label>
            <select class="form-control" name="transactionType" id="transactionType" onchange="toggleReceiverField()">
                <option value="transfer">Transfer</option>
                <option value="credit">Credit</option>
                <option value="debit">Debit</option>
            </select>
        </div>

        <div class="form-group" id="receiverField">
            <label>Receiver's Account Number:</label>
            <input type="text" class="form-control" name="receiverAccount" id="receiverAccount">
        </div>

        <div class="form-group">
            <label>Amount:</label>
            <input type="number" class="form-control" name="amount" required>
        </div>

        <button type="submit" class="btn btn-success btn-block">Submit</button>
    </form>

    <div class="text-center mt-3">
        <!-- Changed to red btn-danger and removed underline for link -->
        <a href="customerDashboard.jsp" class="btn btn-danger btn-block">Back to Dashboard</a>
    </div>
</div>

<script>
function toggleReceiverField() {
    const transactionType = document.getElementById("transactionType").value;
    const receiverField = document.getElementById("receiverField");
    if (transactionType === "transfer") {
        receiverField.style.display = "block";
        document.getElementById("receiverAccount").required = true;
    } else {
        receiverField.style.display = "none";
        document.getElementById("receiverAccount").required = false;
    }
}
window.onload = toggleReceiverField;
</script>

</body>
</html>
