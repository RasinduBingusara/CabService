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
    if (message != null) {
%>
<script>
    alert("<%= message.replace("\"", "\\\"").replace("'", "\\'") %>");
</script>
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
    function fetchModels() {

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
                    form.setAttribute("action", "model");

                    let hiddenInput = document.createElement("input");
                    hiddenInput.setAttribute("type", "hidden");
                    hiddenInput.setAttribute("name", "model_id");
                    hiddenInput.setAttribute("value", u.model_id);
                    let hiddenInput2 = document.createElement("input");
                    hiddenInput2.setAttribute("type", "hidden");
                    hiddenInput2.setAttribute("name", "status");
                    let hiddenInput3 = document.createElement("input");
                    hiddenInput3.setAttribute("type", "hidden");
                    hiddenInput3.setAttribute("name", "action");
                    hiddenInput3.setAttribute("value", "update");

                    let button = document.createElement("button");
                    button.classList.add("edit-btn");
                    button.setAttribute("type","submit");
                    if(u.status === "Active"){
                        hiddenInput2.setAttribute("value", "Inactive");
                        button.setAttribute("style","background: #a72828;")
                        button.textContent = "InActive"
                    }
                    else if(u.status === "Inactive"){
                        hiddenInput2.setAttribute("value", "Active");
                        button.textContent = "Activate";
                    }


                    form.appendChild(hiddenInput);
                    form.appendChild(hiddenInput2);
                    form.appendChild(hiddenInput3);
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
                    newTray.appendChild(actionTd);
                    container.appendChild(newTray);
                })

            })
            .catch(error => console.error("Error fetching models:", error));
    }

    window.onload = fetchModels;
</script>
</body>
</html>
