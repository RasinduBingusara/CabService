<%@ page import="org.megacity.cabservice.dto.driver_dto.DriverInsertDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  DriverInsertDTO newDriver = (DriverInsertDTO) request.getAttribute("user");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%@include file="dashboard_navigator.jsp"%>
  <div class="content">

    <h2>Add New Driver</h2>

    <form action="drivers" method="post">
      <label for="firstname">First Name:</label>
      <input type="text" id="firstname" name="first_name" placeholder="Enter first name"
             value='<%= newDriver!=null? newDriver.getFirstName():""%>' required>

      <label for="lastname">Last Name:</label>
      <input type="text" id="lastname" name="last_name" placeholder="Enter last name"
             value='<%= newDriver!=null? newDriver.getLastName():""%>' required>

      <label for="email">Email:</label>
      <input type="email" id="email" name="email" placeholder="Enter email"
             value='<%= newDriver!=null? newDriver.getEmail():""%>' required>

      <label for="password">Password:</label>
      <input type="password" id="password" name="password" placeholder="Enter password" required>

      <label for="confirmpassword">Confirm Password:</label>
      <input type="password" id="confirmpassword" name="confirm_password" placeholder="Re-enter password" required>

      <label for="contactnumber">Contact Number:</label>
      <input type="tel" id="contactnumber" name="contact_number" placeholder="Enter contact number"
             value='<%= newDriver!=null? newDriver.getContactNumber():""%>' required>

      <label for="nic">NIC Number:</label>
      <input type="text" id="nic" name="nic" placeholder="Enter NIC number"
             value='<%= newDriver!=null? newDriver.getNic():""%>' required>

      <label for="driverlicense">Driver License:</label>
      <input type="text" id="driverlicense" name="driver_license" placeholder="Enter driver license number"
             value='<%= newDriver!=null? newDriver.getDriverLicense():""%>' required>

      <label for="address">Address:</label>
      <textarea id="address" name="address" placeholder="Enter full address" rows="3" required><%= newDriver!=null? newDriver.getAddress():""%></textarea>

        <%
            String error = (String) request.getAttribute("error");
            if(error != null){
        %>
        <p><%= error%></p>
        <%
            }
        %>

      <button type="submit" class="submit-btn">Add Driver</button>
    </form>

  </div>
</body>
<style>
  h2 {
    color: #333;
    margin-bottom: 15px;
  }

  label {
    display: block;
    margin-top: 10px;
    font-weight: bold;
    text-align: left;
  }

  input, select, textarea {
    width: 100%;
    padding: 10px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
  }

  .submit-btn {
    width: 100%;
    padding: 12px;
    background: #ffcc00;
    color: #333;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    margin-top: 15px;
    font-weight: bold;
  }

  .submit-btn:hover {
    background: #e6b800;
  }
</style>
</html>
