<%@ page import="com.dollop.task.service.impl.AccountServiceImpl" %>
<%@ page import="com.dollop.task.service.AccountService" %>
<%@ page import="com.dollop.task.entities.Account" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%
    String idParam = request.getParameter("id");
    Integer id = (idParam != null) ? Integer.parseInt(idParam) : null;
    AccountService service = new AccountServiceImpl();

    Account account = service.getUser(id);
    if (account == null) {
        out.println("<p class='text-danger text-center'>Error: User not found.</p>");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User</title>

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(135deg, #74b9ff, #81ecec);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Arial', sans-serif;
        }

        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
            animation: fadeIn 0.8s ease-in-out;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        h2 {
            margin-bottom: 1.5rem;
            text-align: center;
            font-weight: bold;
            color: #2d3436;
        }

        label {
            font-weight: 600;
            color: #34495e;
        }

        .form-control:focus {
            box-shadow: 0 0 5px rgba(0, 184, 212, 0.8);
            border-color: #00bcd4;
        }

        .btn-primary {
            background-color: #0984e3;
            border: none;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        .btn-primary:hover {
            background-color: #74b9ff;
        }
    </style>
</head>

<body>

    <div class="container">
        <h2>Update User</h2>

        <form action="./AccountController" method="POST">
            <input type="hidden" name="id" value="<%= account.getAccountId() %>">

            <div class="mb-3">
                <label for="firstName" class="form-label">First Name:</label>
                <input type="text" id="firstName" name="firstName" class="form-control"
                    value="<%= account.getFirstName() %>" required>
            </div>

            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name:</label>
                <input type="text" id="lastName" name="lastName" class="form-control"
                    value="<%= account.getLastName() %>" required>
            </div>

            <div class="mb-3">
                <label for="choiceStatus" class="form-label">Status:</label>
                <input list="choices" name="choiceStatus" id="choiceStatus" class="form-control"
                    value="<%= service.getStatusById(account.getStatusId()) %>" placeholder="Select Status" required>
                <datalist id="choices">
                    <option value="TRUE"></option>
                    <option value="FALSE"></option>
                </datalist>
            </div>

            <button type="submit" name="action" value="update" class="btn btn-primary mt-3">Update User</button>
        </form>
    </div>

    <!-- Bootstrap 5 JS (optional for interactivity) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
