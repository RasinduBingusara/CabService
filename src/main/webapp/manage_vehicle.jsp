<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 14/02/2025
  Time: 01:46 pm
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
    <title>Manage Vehicle</title>
    <link rel="stylesheet" href="CSS/manage_dashboard.css">
</head>
<body>
<%@include file="dashboard_navigator.jsp"%>

<div class="content">
    <h1>Manage Vehicles</h1>

    <a href="vehicles?action=add">
        <button class="add-btn">+ Add Vehicle</button>
    </a>

    <input type="text" id="searchVehicle" placeholder="Search by Plate No..." onkeyup="searchTable()" class="searchBar">
    <select id="statusFilter" name="statusFilter" onchange="onChangeStatus()">
        <option value="">All</option>
        <option value="Active">Active</option>
        <option value="Inactive">Inactive</option>
    </select>

    <table>
        <thead>
        <tr>
            <th>Model Name</th>
            <th>Color</th>
            <th>Plate No</th>
            <th>Seat Count</th>
            <th>Availability</th>
            <th>Price Per KM</th>
            <th>Liters Per KM</th>
            <th>Driver</th>
            <th>Owner</th>
            <th>Status</th>
            <th>Updated At</th>
            <th>Added At</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody id="vehicleContainer">
        </tbody>
    </table>
    <button id="loadMore" onclick="loadMoreTable()">Load More...</button>
</div>

<script>

    let count = 0;
    let container = document.getElementById("vehicleContainer");
    let status;
    let offset = 10;

    function onChangeStatus(){
        let keyword = document.getElementById("searchVehicle").value;
        if(keyword===""){
            fetchVehicles();
        }
        else{
            searchTable();
        }
    }

    function fetchVehicles() {
        offset = 10;
        count = 0;
        status = document.getElementById("statusFilter").value;
        fetch("vehicles?action=portion&limit=10&offset=0&status="+status)
            .then(response => response.json())
            .then(data => {
                count = data.length;
                container.innerHTML = "";
                loadTable(data);

                if(count===10){
                    document.getElementById("loadMore").style.display = "block";
                }else
                    document.getElementById("loadMore").style.display = "none";

            })
            .catch(error => console.error("Error fetching vehicles:", error));
    }

    window.onload = fetchVehicles;

    function loadMoreTable(){
        status = document.getElementById("statusFilter").value;
        fetch("vehicles?action=portion&limit=10&offset="+offset+"&status=" + status)
            .then(response => response.json())
            .then(data => {
                count = data.length
                loadTable(data);

                if(count===10){
                    document.getElementById("loadMore").style.display = "block";
                }else
                    document.getElementById("loadMore").style.display = "none";
                offset += 10

            }).catch(error => console.error("Error fetching users:", error));
    }

    function searchTable(){
        status = document.getElementById("statusFilter").value;
        let keyword = document.getElementById("searchVehicle").value;
        if(keyword === ""){
            fetchVehicles();
        }
        else{
            fetch("vehicles?action=search&keyword="+keyword+"&status="+status)
                .then(response => response.json())
                .then(data => {
                    container.innerHTML = "";
                    loadTable(data);
                })
        }

    }

    function loadTable(data){
        console.log("Status: " + status);
        data.forEach(u => {
            let newTray = document.createElement("tr");

            let model = document.createElement("td");
            model.textContent = u.model.model_name;

            let color = document.createElement("td");
            color.textContent = u.color;

            let plateNo = document.createElement("td");
            plateNo.textContent = u.plate_no;

            let seatCount = document.createElement("td");
            seatCount.textContent = u.seat_count;

            let availability = document.createElement("td");
            availability.textContent = u.availability;

            let ppkm = document.createElement("td");
            ppkm.textContent = u.price_per_km;

            let lpkm = document.createElement("td");
            lpkm.textContent = u.liters_per_km;

            let driver = document.createElement("td");
            driver.textContent = u.driver.first_name + " " + u.driver.last_name;

            let owner = document.createElement("td");
            owner.textContent = u.owner.first_name + " " + u.owner.last_name;

            let status = document.createElement("td");
            status.textContent = u.status;

            let updated_at = document.createElement("td");
            updated_at.textContent = u.updated_at!=""? u.updated_at:"Not Updated Yet";

            let addedAt = document.createElement("td");
            addedAt.textContent = u.added_at;

            let actionTd = document.createElement("td");
            let form = document.createElement("form");
            form.setAttribute("action", "vehicles");

            let hiddenInput = document.createElement("input");
            hiddenInput.setAttribute("type", "hidden");
            hiddenInput.setAttribute("name", "vehicle_id");
            hiddenInput.setAttribute("value", u.vehicle_id);
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
            else if(u.status === "OnTrip"){
                button.setAttribute("style","display: none;")
            }


            form.appendChild(hiddenInput);
            form.appendChild(hiddenInput2);
            form.appendChild(hiddenInput3);
            form.appendChild(button);
            actionTd.appendChild(form);

            newTray.appendChild(model);
            newTray.appendChild(color);
            newTray.appendChild(plateNo);
            newTray.appendChild(seatCount);
            newTray.appendChild(availability);
            newTray.appendChild(ppkm);
            newTray.appendChild(lpkm);
            newTray.appendChild(driver);
            newTray.appendChild(owner);
            newTray.appendChild(status);
            newTray.appendChild(updated_at);
            newTray.appendChild(addedAt);
            newTray.appendChild(actionTd);

            container.appendChild(newTray);
        })

    }

</script>

</body>
</html>