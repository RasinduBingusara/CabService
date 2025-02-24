<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 03/02/2025
  Time: 10:05 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="Top_Navigation_Bar.jsp"%>
    <div class="login-container">

        <form action="login" method="post" class="login-form">
            <div class="input-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Email..." required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Password..." required>
            </div>
            <label class="error-message">
                <%=
                    request.getAttribute("error") != null? request.getAttribute("error"):""
                %>
            </label>
            <p class="sign-up-link">Don't have an account?<a href="signup.jsp">Create Account</a></p>
            <input type="submit" value="Sign In" class="login-btn">
        </form>
    </div>
</body>

<style>

    .login-container {
        width: 100%;
        max-width: 400px;
        margin: 50px auto;
        padding: 20px;
        margin-top: 150px;
        background: #f9f9f9;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        text-align: center;
    }

    .login-form {
        display: flex;
        flex-direction: column;
    }

    .input-group {
        margin-bottom: 15px;
        text-align: left;
    }

    .input-group label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
        color: #333;
    }

    .input-group input {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }

    .input-group input:focus {
        outline: none;
        border-color: #ffcc00;
        box-shadow: 0 0 5px rgba(255, 204, 0, 0.5);
    }

    .error-message {
        color: red;
        font-size: 14px;
        margin-bottom: 10px;
        display: block;
    }

    .sign-up-link {
        margin-top: 10px;
        font-size: 14px;
    }

    .sign-up-link a {
        color: #ffcc00;
        text-decoration: none;
        font-weight: bold;
    }

    .sign-up-link a:hover {
        text-decoration: underline;
    }

    .login-btn {
        width: 100%;
        padding: 10px;
        background: #ffcc00;
        color: #333;
        font-size: 18px;
        font-weight: bold;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: 0.3s;
    }

    .login-btn:hover {
        background: #e6b800;
    }

</style>
</html>
