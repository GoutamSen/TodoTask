<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ADD TASK</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background-color: white;
            color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            width: 500px;
            max-width: 100%;
            background-color: #2c2c2b;
            padding: 40px 30px;
            border-radius: 16px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.5);
            text-align: center;
        }

        h2 {
            font-size: 26px;
            margin-bottom: 20px;
            font-weight: 600;
            color: #f39c12;
        }

        .form-group {
            margin-bottom: 25px;
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: #aaa;
        }

        input[type="text"],
        input[type="date"],
        input[list] {
            width: 100%;
            padding: 15px;
            border: 1px solid #444;
            border-radius: 8px;
            background-color: #333;
            font-size: 16px;
            color: #f0f0f0;
            margin-top: 5px;
            transition: all 0.3s ease;
        }

        input:focus {
            border-color: #f39c12;
            background-color: #444;
            box-shadow: 0 0 10px rgba(243, 156, 18, 0.7);
            outline: none;
        }

        input[type="submit"] {
            width: 100%;
            padding: 15px;
            margin-top: 10px;
            background-color: #f39c12;
            color: #1e1e1e;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.1s ease;
        }

        input[type="submit"]:hover {
            background-color: #e67e22;
        }

        input[type="submit"]:active {
            transform: scale(0.97);
        }

        /* Style the datalist dropdown options */
        datalist option {
            background-color: #333;
            color: white;
        }
    </style>
</head>
<body>

<div class="container">
    <% 
        String idParam = request.getParameter("id"); 
        out.println("Received ID: " + idParam); 
        Integer id = Integer.parseInt(idParam); 
    %>

    <h2>Add Task</h2>
    <form action="./TaskController?action=add" method="POST">
        <input type="hidden" name="id" value="<%=id%>">

        <div class="form-group">
            <label for="details">Details:</label>
            <input type="text" id="details" name="details" placeholder="Enter Task Details" required>
        </div>

        <div class="form-group">
            <label for="choiceInput">Status:</label>
            <input list="choices" name="choiceInput" id="choiceInput" placeholder="Select Status" required>
            <datalist id="choices">
                <option value="STARTED"></option>
                <option value="COMPLETED"></option>
                <option value="PENDING"></option>
                <option value="CANCELLED"></option>
                <option value="PROCESSING"></option>
            </datalist>
        </div>

        <div class="form-group">
            <label for="dateInput">Deadline:</label>
            <input type="date" id="dateInput" name="dateInput" required>
        </div>

        <div class="form-group">
            <label for="choicePriority">Priority:</label>
            <input list="choice" name="choicePriority" id="choicePriority" placeholder="Select Priority" required>
            <datalist id="choice">
                <option value="High"></option>
                <option value="Medium"></option>
                <option value="Low"></option>
            </datalist>
        </div>

        <input type="submit" name="action" value="Register">
    </form>
</div>

</body>
</html>
