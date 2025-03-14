<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 03/03/2025
  Time: 06:07 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
%>
<script>
    alert("<%= message.replace("\"", "\\\"").replace("'", "\\'") %>");
</script>
<%
    }
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Profile</title>
</head>
<body>
<%@include file="driver_dashboard_navigator.jsp"%>

<div class="content" style="margin-top: 80px">
    <div class="profile-container">
        <h2>Profile</h2>
        <div class="profile-details">
            <form action="profile" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" id="hiddenEmail" name="email" >
                <label id="firstname" ></label>

                <label id="lastname" ></label>

                <label id="email" ></label>

                <label id="contact" ></label>
                <label id="license" ></label>
                <label id="nic" ></label>
                <label id="address" ></label>

                <h3>Change Password</h3>

                <label for="newpassword">New Password:</label>
                <input type="password" id="newpassword" name="new_password" required>

                <label for="confirmpassword">Confirm Password:</label>
                <input type="password" id="confirmpassword" name="confirm_password" required>

                <p><%= request.getAttribute("error")!=null? request.getAttribute("error"):"" %></p>
                <button type="submit" class="save-btn" >Save Changes</button>
            </form>
        </div>
    </div>
</div>

<script>
    function fetchUser() {

        fetch("profile?action=history")
            .then(response => response.json())
            .then(data => {

                console.log(data);
                document.getElementById("hiddenEmail").textContent = data.email;
                document.getElementById("firstname").textContent = "First Name: " + data.first_name;
                document.getElementById("lastname").textContent = "Last Name: " + data.last_name;
                document.getElementById("email").textContent = "Email: " + data.email;
                document.getElementById("contact").textContent = "Contact Number: " + data.contact_number;
                document.getElementById("license").textContent = "License No: " + data.driver_license;
                document.getElementById("nic").textContent = "NIC: " + data.nic;
                document.getElementById("address").textContent = "Address: " + data.address;


            })
            .catch(error => console.error("Error fetching user:", error));
    }

    window.onload = fetchUser;

</script>

</body>
</html>
