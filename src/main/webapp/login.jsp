<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
 
     <!-- Login Notification -->
    <% 
        Boolean loginfailed = (Boolean) request.getAttribute("Block");
        if (loginfailed != null && loginfailed) { 
    %>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var toast = document.createElement('div');
            toast.textContent = "Your Account is Blocked ! ";
            toast.style.position = 'fixed';
            toast.style.top = '20px';
            toast.style.left = '50%';
            toast.style.transform = 'translateX(-50%)';
            toast.style.backgroundColor = '#ff5757';
            toast.style.color = 'white';
            toast.style.padding = '15px 30px';
            toast.style.borderRadius = '12px';
            toast.style.boxShadow = '0 4px 15px rgba(0, 0, 0, 0.2)';
            toast.style.zIndex = 1000;
            toast.style.fontSize = '16px';
            document.body.appendChild(toast);

            setTimeout(() => toast.style.display = 'none', 3000);
        });
    </script>
    <% } %>


    <!-- Login Notification -->
    <% 
        Boolean loginSuccess = (Boolean) request.getAttribute("InvalidInformation");
        if (loginSuccess != null && loginSuccess) { 
    %>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var toast = document.createElement('div');
            toast.textContent = "Invalid Credentials!";
            toast.style.position = 'fixed';
            toast.style.top = '20px';
            toast.style.left = '50%';
            toast.style.transform = 'translateX(-50%)';
            toast.style.backgroundColor = '#ff5757';
            toast.style.color = 'white';
            toast.style.padding = '15px 30px';
            toast.style.borderRadius = '12px';
            toast.style.boxShadow = '0 4px 15px rgba(0, 0, 0, 0.2)';
            toast.style.zIndex = 1000;
            toast.style.fontSize = '16px';
            document.body.appendChild(toast);

            setTimeout(() => toast.style.display = 'none', 3000);
        });
    </script>
    <% } %>

    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #e0eafc, #cfdef3);
            font-family: 'Poppins', sans-serif;
        }

        .container {
            width: 100%;
            max-width: 400px;
            padding: 40px;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 16px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.3);
            animation: fadeIn 0.8s ease-in-out;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
            font-weight: 600;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: 500;
            margin-bottom: 8px;
            color: #444;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid rgba(255, 255, 255, 0.4);
            border-radius: 8px;
            background: rgba(255, 255, 255, 0.25);
            font-size: 16px;
            transition: all 0.3s;
            color: #333;
        }

        input[type="text"]::placeholder,
        input[type="password"]::placeholder {
            color: #999;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            background: rgba(255, 255, 255, 0.5);
            border-color: #6a11cb;
            outline: none;
            box-shadow: 0 0 10px rgba(106, 17, 203, 0.4);
        }

        button {
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            border: none;
            border-radius: 8px;
            color: white;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background: linear-gradient(135deg, #4e0dab, #1b5fcd);
        }

        .forgot-password {
            text-align: right;
            margin-top: 10px;
        }

        .forgot-password a {
            color: #6a11cb;
            text-decoration: none;
            font-size: 14px;
        }

        .forgot-password a:hover {
            text-decoration: underline;
        }

        .logo {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }

        .logo img {
            width: 80px;
            height: auto;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="logo">
        <img src="https://www.pngall.com/wp-content/uploads/12/Avatar-Profile-PNG.png"  alt="Logo"  style="width: 110px; height: 100px;">
    </div>
    <h2>Login</h2>
    <form action="./AuthenticationController" method="POST">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="userName" name="userName" placeholder="Enter your username" required>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
        </div>

        <button type="submit" name="action" value="login">Login</button>

        <div class="forgot-password">
            <a href="#">Forgot Password?</a>
        </div>
    </form>
</div>

</body>
</html>
