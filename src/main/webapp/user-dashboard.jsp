<%@page import="com.dollop.task.service.TaskService"%>
<%@page import="com.dollop.task.service.impl.TaskServiceImpl"%>
<%@page import="com.dollop.task.entities.Task"%>
<%@page import="java.util.List"%>
<%@page import="com.dollop.task.entities.Account"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task List</title>
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
  %>
  
    <h1>All Task</h1>
    <div class="button-container">
        <button><a href="./addtask.jsp?id=<%=id%>">ADD New Task</a></button>
     
     <form action="./AuthenticationController" method="GET">
     <button type="submit">Logout</button>
     </form>
     
  
    </div>

    <table>
        <thead>
            <tr>
                <th>S no.</th>
                <th>Id</th>
                <th>Details</th>
                <th>CreatedAt</th> 
                <th>Deadline</th> 
                <th>UpdatedAt</th> 
                <th>Status</th>
                <th>Priority</th>                            
                <th>Action</th>             
            </tr>
        </thead>
        <tbody>
      
            <tr>
                <% 
            TaskServiceImpl service = new TaskServiceImpl();
            List<Task> tasks = (List<Task>) request.getAttribute("tasks");
            if (tasks != null) {
            	int Sno=1;
                for (Task task : tasks) { 
        %>
                <td><%= Sno++ %></td>
                <td><%= task.getId() %></td>
                <td><%= task.getDetails()%></td>               
                <td><%= task.getCreatedAt()%></td>
                <td><%= task.getDeadline()%></td>
                <td><%= task.getLastUpdate()%></td>
                <td><%= service.getStatusByTask(task.getStatusId())%>
                <td><%= service.getPriorityByTask(task.getPriorityId()) %> </td>
                <td>
                    <div class="action-links">
                        <a href="./updateTask.jsp?id=<%=task.getId()%>">
                            <button class="action-btn">Update</button>
                        </a>
                                                                                           
                    </div>
                </td>
            </tr>
        <% 
                }
            } else { 
        %>
        <% } %>
        </tbody>
    </table>

</body>
</html>
