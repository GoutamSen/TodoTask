<%@page import="com.dollop.task.service.impl.AccountServiceImpl"%>
<%@page import="com.dollop.task.service.AccountService"%>
<%@page import="java.util.List"%>
<%@page import="com.dollop.task.entities.Account"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
   
         <%
    HttpSession sessions = request.getSession(false); // Do not create a new session
    if (sessions == null || sessions.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }
%>
   
    <!-- Login Success Toast Notification -->
    <% 
        Boolean loginSuccess = (Boolean) request.getAttribute("loginSuccess");
        if (loginSuccess != null && loginSuccess) { 
    %>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var toast = document.createElement('div');
            toast.textContent = "Login Successful!";
            toast.style.position = 'fixed';
            toast.style.top = '30px';
            toast.style.left = '45%'; 
            toast.style.backgroundColor = '#4caf50';
            toast.style.color = 'white';
            toast.style.padding = '15px';
            toast.style.borderRadius = '5px';
            toast.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.2)';
            toast.style.zIndex = 1000;
            document.body.appendChild(toast);

            setTimeout(function() {
                toast.style.display = 'none';
            }, 3000); // Toast disappears after 3 seconds
        });
    </script>
    <% } %>

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f7f9;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            background-color: #2c3e50;
            color: white;
            padding: 15px;
            margin-bottom: 30px;
            border-radius: 8px;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .button-container button {
            background-color: #3498db;
            border: none;
            color: white;
            padding: 12px 20px;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 16px;
        }

        .button-container button:hover {
            background-color: #2980b9;
        }

        .button-container a {
            color: white;
            text-decoration: none;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #34495e;
            color: white;
        }

        td {
            color: #2c3e50;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        button.action-btn {
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 14px;
        }

        button.action-btn:hover {
            background-color: #c0392b;
        }

        .action-links {
            display: flex;
            gap: 10px;
        }
    </style>
</head>
<body>
    <%
        Integer id = (Integer) request.getAttribute("id");
        List<Account> users = (List<Account>) request.getAttribute("accounts");
    %>

    <h1>All Users</h1>

    <div class="button-container">
        <button><a href="./register.jsp?id=<%=id%>">ADD New User</a></button>

     <form action="./AuthenticationController" method="GET">
     <button type="submit">Logout</button>
     </form>
    </div>

    <table>
        <thead>
            <tr>
                <th>S No.</th>
                <th>ID</th>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Password</th>
                <th>Created At</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (users != null) {
                    int Sno = 1;
                    AccountService service = new AccountServiceImpl();
                    for (Account user : users) {
            %>
            <tr>
                <td><%= Sno++ %></td>
                <td><%= user.getAccountId() %></td>
                <td><%= user.getUserName() %></td>
                <td><%= user.getFirstName() %></td>
                <td><%= user.getLastName() %></td>
                <td><%= user.getPassword() %></td>
                <td><%= user.getCreatedAt() %></td>
                <td><%= service.getStatusById(user.getStatusId()) %></td>
                <td>
                    <div class="action-links">
                        <a href="./updateUser.jsp?id=<%= user.getAccountId() %>">
                            <button class="action-btn">Update</button>
                        </a>
                    </div>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
