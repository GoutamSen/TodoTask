<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Register</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #3498db, #9b59b6);
            overflow: hidden;
        }

        .container {
            background-color: #fff;
            padding: 45px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            width: 520px;
            max-width: 100%;
            text-align: center;
            animation: fadeIn 1s ease-in-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(30px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h2 {
            font-size: 28px;
            font-weight: 600;
            color: #333;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: #555;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[list] {
            width: 100%;
            padding: 14px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #f7f7f7;
            font-size: 15px;
            color: #333;
            outline: none;
            transition: all 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="password"]:focus,
        input[list]:focus {
            border-color: #8e44ad;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(142, 68, 173, 0.3);
        }

        input[type="submit"] {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #3498db, #8e44ad);
            color: white;
            font-weight: 600;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 17px;
            transition: background 0.3s ease, transform 0.1s ease;
        }

        input[type="submit"]:hover {
            background: linear-gradient(135deg, #2980b9, #7d3c98);
        }

        input[type="submit"]:active {
            transform: scale(0.97);
        }

        @media (max-width: 600px) {
            .container {
                padding: 30px;
                width: 90%;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <%
        String idParam = request.getParameter("id");
        Integer id = Integer.parseInt(idParam);
    %>

    <h2>Register User</h2>
    <form action="./AccountController?action=add" method="POST">
        <input type="hidden" name="id" value="<%= id %>">

        <div class="form-group">
            <label for="userName">Username:</label>
            <input type="text" id="userName" placeholder="Enter Username" name="userName" required>
        </div>

        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" placeholder="Enter First Name" name="firstName" required>
        </div>

        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" placeholder="Enter Last Name" name="lastName" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" placeholder="Enter Password" name="password" required>
        </div>

        <div class="form-group">
            <label for="status">Status:</label>
            <input list="choice" name="choiceStatus" id="choiceStatus" placeholder="Select Status" required>
             
            <datalist id="choice">
                <option value="TRUE"></option>
                <option value="FALSE"></option>
            </datalist>
        </div>

        <input type="submit" name="action" value="Register">
    </form>
</div>

</body>
</html>
