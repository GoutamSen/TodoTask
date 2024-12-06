<%@page import="com.dollop.task.entities.Task"%>
<%@page import="com.dollop.task.service.TaskService"%>
<%@page import="com.dollop.task.service.impl.TaskServiceImpl"%>
<%@page import="com.dollop.task.service.impl.AccountServiceImpl"%>
<%@page import="com.dollop.task.service.AccountService"%>
<%@page import="com.dollop.task.entities.Account"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<%
    String idParam = request.getParameter("id");
    Integer id = (idParam != null) ? Integer.parseInt(idParam) : null;
    TaskService service = new TaskServiceImpl();
    Task task = service.getTask(id);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Task</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300;600&display=swap" rel="stylesheet">

    <style>
        /* Global Styles */
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #74b9ff, #ffeaa7);
            font-family: 'Raleway', sans-serif;
        }

        /* Glassmorphism Container */
        .container {
            width: 100%;
            max-width: 500px;
            padding: 40px;
            background: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(15px);
            border-radius: 20px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.25);
            border: 1px solid rgba(255, 255, 255, 0.18);
            animation: fadeIn 0.6s ease-in-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-30px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #2d3436;
            font-weight: 600;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: 600;
            color: #636e72;
            display: block;
            margin-bottom: 8px;
        }

        input[type="text"], input[type="date"], input[list] {
            width: 100%;
            padding: 12px;
            border-radius: 10px;
            border: 1px solid #dfe6e9;
            background: rgba(255, 255, 255, 0.7);
            transition: all 0.3s ease;
            font-size: 16px;
            outline: none;
        }

        input:focus {
            border-color: #0984e3;
            box-shadow: 0 0 10px rgba(9, 132, 227, 0.5);
        }

        button {
            width: 100%;
            padding: 15px;
            background-color: #00cec9;
            border: none;
            border-radius: 10px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.1s;
        }

        button:hover {
            background-color: #81ecec;
        }

        button:active {
            transform: scale(0.98);
        }
    </style>
</head>

<body>

<div class="container">
    <h2>Update Task</h2>
    
    <form action="./TaskController" method="POST">
        <input type="hidden" name="taskid" value="<%=id%>">

        <div class="form-group">
            <label for="details">Details:</label>
            <input type="text" id="details" name="details" 
                   value="<%=task.getDetails()%>" placeholder="Enter Task Details" required>
        </div>

        <div class="form-group">
            <label for="choiceInput">Status:</label>
            
            <input list="choices" name="choiceInput" id="choiceInput" 
                   value="<%=service.getStatusByTask(task.getAccountId())%>" placeholder="Select Status">
            <datalist id="choices">
            
                <option value="ENABLE"></option>
                <option value="DISABLE"></option>
            </datalist>
        </div>

        <div class="form-group">
            <label for="dateInput">Deadline:</label>
            <input type="date" id="dateInput" name="dateInput" 
                   value="<%=task.getDeadline()%>">
        </div>

        <div class="form-group">
            <label for="choicePriority">Priority:</label>
            <input list="choice" name="choicePriority" id="choicePriority" 
                   value="<%=service.getPriorityByTask(task.getPriorityId())%>" placeholder="Select Priority">
            <datalist id="choice">
                <option value="High"></option>
                <option value="Middle"></option>
                <option value="Low"></option>
            </datalist>
        </div>

        <button type="submit" name="action" value="update">Update Task</button>
    </form>
</div>

</body>
</html>
