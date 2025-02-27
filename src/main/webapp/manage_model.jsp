<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 27/02/2025
  Time: 01:38 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String message = (String) request.getAttribute("message");
    if(message!=null){
%>
<script>alert('<%= message%>')</script>
<%
    }
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="CSS/manage_dashboard.css">
</head>
<body>
<%@include file="dashboard_navigator.jsp"%>
    <div class="content">
        <h2>Vehicle Model Management</h2>

        <a href="model?action=add">
            <button class="add-driver-btn">+ Add Model</button>
        </a>

        <table id="modelTable">
            <thead>
            <tr>
                <th>Model Name</th>
                <th>Vehicle Type</th>
                <th>Manufacturer</th>
                <th>Year</th>
                <th>Fuel Type</th>
                <th>Transmission</th>
                <th>Status</th>
                <th>Added At</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody id="modelContainer">
            </tbody>
        </table>

    </div>

<script>
    function fetchUsers() {

        fetch("model?action=load")
            .then(response => response.json())
            .then(data => {
                let container = document.getElementById("modelContainer")
                container.innerHTML = "";

                data.forEach(u =>{
                    let newTray = document.createElement("tr");

                    let name = document.createElement("td");
                    name.textContent = u.model_name;
                    let type = document.createElement("td");
                    type.textContent = u.vehicle_type;
                    let manufacturer = document.createElement("td");
                    manufacturer.textContent = u.manufacturer;
                    let year = document.createElement("td");
                    year.textContent = u.year;
                    let fuelType = document.createElement("td");
                    fuelType.textContent = u.fuel_type;
                    let transmission = document.createElement("td");
                    transmission.textContent = u.transmission;
                    let status = document.createElement("td");
                    status.textContent = u.status;
                    let addedAt = document.createElement("td");
                    addedAt.textContent = u.added_at;

                    let actionTd = document.createElement("td");
                    let form = document.createElement("form");
                    form.setAttribute("action", "model?action=edit");

                    let hiddenInput = document.createElement("input");
                    hiddenInput.setAttribute("type", "hidden");
                    hiddenInput.setAttribute("name", "modelId");
                    hiddenInput.setAttribute("value", u.model_id);

                    let button = document.createElement("button");
                    button.classList.add("edit-btn");
                    button.setAttribute("type","submit");
                    button.textContent = "Edit";

                    form.appendChild(hiddenInput);
                    form.appendChild(button);
                    actionTd.appendChild(form);

                    newTray.appendChild(name);
                    newTray.appendChild(type);
                    newTray.appendChild(manufacturer);
                    newTray.appendChild(year);
                    newTray.appendChild(fuelType);
                    newTray.appendChild(transmission);
                    newTray.appendChild(status);
                    newTray.appendChild(addedAt);
                    newTray.appendChild(button);
                    container.appendChild(newTray);
                })

            })
            .catch(error => console.error("Error fetching users:", error));
    }

    window.onload = fetchUsers;
</script>
</body>
</html>
