<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 03/02/2025
  Time: 10:06 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String usertype = request.getAttribute("type")!=null? (String) request.getAttribute("type") :"Customer";
    User user = (User) request.getAttribute("user");
%>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<%@include file="Top_Navigation_Bar.jsp"%>
    <div class="signup-container">
        <%
            if(usertype.equals("driver")){
                out.print("<h2>Join As a Driver</h2>");
            }
            else{
                out.print("<h2>Sign Up</h2>");
            }
        %>
        <form action="signup" method="post" class="signup-form">
            <input type="hidden" name="usertype" value='<%= usertype%>'>
           <div class="name-group">
               <div class="input-group">
                   <label for="firstname">First Name</label>
                   <input type="text" id="firstname" name="firstname" placeholder="First Name..."
                          value='<%= user != null ? user.getFirstName():""%>' required>
               </div>
               <div class="input-group">
                   <label for="lastname">Last Name</label>
                   <input type="text" id="lastname" name="lastname" placeholder="Last Name..."
                          value='<%= user != null ? user.getLastName():""%>' required>
               </div>
           </div>
            <div class="input-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" placeholder="Email..."
                       value='<%= user != null ? user.getEmail():""%>' required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Password..." required>
            </div>
            <div class="input-group">
                <label for="conPassword">Confirm Password</label>
                <input type="password" id="conPassword" name="confirmpassword" placeholder="Confirm Password..." required>
            </div>
            <div class="input-group">
                <label for="contactnumber">Contact Number</label>
                <input type="text" pattern="\d*" inputmode="numeric" id="contactnumber"
                       name="contactnumber" placeholder="Contact Number..."
                       value='<%= user != null ? user.getContactNumber():""%>' required>
            </div>
            <%
                if(usertype.equals("driver")){
            %>
            <div class="input-group">
                <label for="nic">NIC</label>
                <input type="text" id="nic" name="nic" placeholder="Nic..."
                       value='<%= user != null ? user.getNic():""%>' required>
            </div>
            <div class="input-group">
                <label for="driverlicense">Driver License</label>
                <input type="text" id="driverlicense" name="driverlicense" placeholder="Driver License..."
                       value='<%= user != null ? user.getDriverLicense():""%>' required>
            </div>
            <div class="input-group">
                <label for="address">Address</label>
                <textarea name="address" id="address"  required><%= user != null ? user.getAddress():""%></textarea>
            </div>
            <%
                }
            %>


            <label class="error-message">
                <%=
                request.getAttribute("error") != null? request.getAttribute("error"):""
                %>
            </label>
            <p class="login-link">already have an account?<a href="login.jsp">login</a></p>
            <input type="submit" value="Sign Up" class="signup-btn">
        </form>
    </div>
</body>

<style>
    .signup-container {
        width: 100%;
        max-width: 450px;
        margin: 50px auto;
        margin-top: 150px;
        padding: 20px;
        background: #f9f9f9;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
        text-align: center;
    }

    .signup-form {
        display: flex;
        flex-direction: column;
    }

    .name-group {
        display: flex;
        gap: 10px;
    }

    .input-group {
        margin-bottom: 15px;
        text-align: left;
        width: 100%;
    }

    .input-group label {
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
        color: #333;
    }

    .input-group input,.input-group textarea {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }

    .input-group input:focus, .input-group textarea:focus {
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

    .login-link {
        margin-top: 10px;
        font-size: 14px;
    }

    .login-link a {
        color: #ffcc00;
        text-decoration: none;
        font-weight: bold;
    }

    .login-link a:hover {
        text-decoration: underline;
    }

    .signup-btn {
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

    .signup-btn:hover {
        background: #e6b800;
    }
</style>
</html>
