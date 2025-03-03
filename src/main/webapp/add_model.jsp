<%@ page import="org.megacity.cabservice.dto.model_dto.ModelInsertDto" %>
<%@ page import="org.megacity.cabservice.model.Enums.VehicleType" %><%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 14/02/2025
  Time: 03:04 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ModelInsertDto returnModel = (ModelInsertDto) request.getAttribute("model");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="dashboard_navigator.jsp"%>

    <div class="content">
        <h1>Add Vehicle Model</h1>

        <form action="model" method="post" class="model-form">
            <label for="vehicleType">Vehicle Type:</label>
            <select id="vehicleType" name="vehicleType" required>
                <option value="" disabled <%= returnModel!=null? "selected":""%>>Select Type</option>
                <%
                    for (VehicleType type: VehicleType.values()){
                %>
                    <option value='<%=type%>' <%= returnModel!=null && returnModel.getVehicleType().equals(type.toString())? "selected":""%>><%=type%></option>
                <%
                    }
                %>
            </select>

            <label for="modelName">Model Name:</label>
            <input type="text" id="modelName" name="modelName" placeholder="Enter model name..."
                   value='<%= returnModel!=null? returnModel.getModelName():""%>' required>

            <label for="manufacturer">Manufacturer:</label>
            <input type="text" id="manufacturer" name="manufacturer" placeholder="Enter manufacturer name..."
                   value='<%= returnModel!=null? returnModel.getManufacturer():""%>' required>

            <label for="year">Year:</label>
            <input type="number" id="year" name="year" placeholder="Enter year..." required min="1900" max="2099"
                   value='<%= returnModel!=null? returnModel.getYear():""%>'>

            <label for="fuelType">Fuel Type:</label>
            <select id="fuelType" name="fuelType" required>
                <option value="" disabled selected>Select Fuel Type</option>
                <option value="Petrol" <%= returnModel!=null && returnModel.getFuelType().equals("Petrol")? "selected" :""%>>Petrol</option>
                <option value="Diesel" <%= returnModel!=null && returnModel.getFuelType().equals("Diesel")? "selected" :""%>>Diesel</option>
                <option value="Electric" <%= returnModel!=null && returnModel.getFuelType().equals("Electric")? "selected" :""%>>Electric</option>
                <option value="Hybrid" <%= returnModel!=null && returnModel.getFuelType().equals("Hybrid")? "selected" :""%>>Hybrid</option>
            </select>

            <label for="transmission">Transmission:</label>
            <select id="transmission" name="transmission" required>
                <option value="" disabled selected>Select Transmission</option>
                <option value="Manual" <%= returnModel!=null && returnModel.getTransmission().equals("Manual")? "selected" :""%>>Manual</option>
                <option value="Automatic" <%= returnModel!=null && returnModel.getTransmission().equals("Automatic")? "selected" :""%>>Automatic</option>
            </select>
            <p><%= request.getAttribute("error")!=null? request.getAttribute("error"):""%></p>
            <button type="submit" class="submit-btn">Add Model</button>
        </form>
    </div>

</body>

<style>
    h1 {
        font-size: 26px;
        margin-bottom: 20px;
        color: #333;
        text-align: center;
    }

    /* Form Styling */
    .model-form {
        display: flex;
        flex-direction: column;
        gap: 12px;
    }

    .model-form label {
        font-weight: bold;
        margin-bottom: 5px;
        color: #333;
    }

    .model-form input,
    .model-form select {
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    /* Submit Button */
    .submit-btn {
        background-color: #ffcc00;
        color: #333;
        padding: 12px;
        font-size: 18px;
        font-weight: bold;
        border: none;
        cursor: pointer;
        border-radius: 5px;
        margin-top: 15px;
        transition: 0.3s;
    }

    .submit-btn:hover {
        background-color: #333;
        color: #ffcc00;
    }

    /* Responsive Design */
    @media (max-width: 768px) {
        .content {
            margin-left: 0;
            width: 90%;
            margin: auto;
        }
    }
</style>
</html>
