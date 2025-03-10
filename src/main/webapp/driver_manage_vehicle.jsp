<%@ page import="org.megacity.cabservice.dto.vehicle_dto.VehicleDetailsDto" %><%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 10/03/2025
  Time: 04:56 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    VehicleDetailsDto vehcile = (VehicleDetailsDto) request.getAttribute("vehicle");
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Driver Vehicle Management</title>
    <link rel="stylesheet" href="CSS/driver_vehicle.css">
</head>
<body>

<%@include file="driver_dashboard_navigator.jsp"%>

<div class="container">
    <h2>Manage Your Vehicle</h2>

    <div class="vehicle-details">
        <h3>Vehicle Information</h3>
        <p><strong>Model:</strong> <%= vehcile.getModel().getModelName()%></p>
        <p><strong>Plate Number:</strong> <%= vehcile.getPlate_no()%></p>
        <p><strong>Seat Count:</strong> <%= vehcile.getSeat_count()%></p>
        <p><strong>Status:</strong> <span id="vehicle-status"><%= vehcile.getStatus()%></span></p>
    </div>

    <div class="status-update">
        <h3>Update Vehicle Status</h3>
        <form action="driver_vehicle" id="statusForm" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value='<%= vehcile.getId()%>'>
            <label for="status">Select Status:</label>
            <select id="status" name="status">
                <option value="Active">Available</option>
                <option value="OnTrip">On Trip</option>
                <option value="Inactive">Maintenance</option>
            </select>
            <button type="submit" onclick="updateStatus()">Update Status</button>
        </form>
    </div>

    <form action="driver_vehicle" method="get">
        <input type="hidden" name="action" value="renew">
        <button type="submit">Change the Vehicle</button>
    </form>
</div>

</body>

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f4f4f4;
        margin: 0;
        padding: 20px;
    }

    .container {
        background: white;
        padding: 20px;
        width: 50%;
        margin: auto;
        border-radius: 10px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }

    h2, h3 {
        text-align: center;
        color: #333;
    }

    .vehicle-details, .status-update {
        margin-top: 20px;
        padding: 10px;
        background: #f9f9f9;
        border-radius: 5px;
    }

    p {
        font-size: 16px;
        margin: 5px 0;
    }

    form {
        text-align: center;
    }

    select {
        padding: 8px;
        font-size: 16px;
        margin-right: 10px;
    }

    button {
        background: #007bff;
        color: white;
        padding: 8px 12px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    button:hover {
        background: #0056b3;
    }

</style>
</html>
