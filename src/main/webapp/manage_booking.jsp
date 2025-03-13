<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 02/03/2025
  Time: 03:44 pm
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
        <h1>Manage Bookings</h1>

        <input type="text" id="searchBookings" placeholder="Search by plate no or transaction id ..." onkeyup="searchTable()" class="searchBar">
        <select id="statusFilter" name="statusFilter" onchange="onChangeStatus()">
            <option value="">All</option>
            <option value="Pending">Pending</option>
            <option value="Confirmed">Confirmed</option>
            <option value="Canceled">Canceled</option>
        </select>

        <table>
            <thead>
            <tr>
                <th>Customer</th>
                <th>Booking User</th>
                <th>Vehicle</th>
                <th>Amount</th>
                <th>Pick Up Location</th>
                <th>Destination</th>
                <th>Distance</th>
                <th>Booked At</th>
            </tr>
            </thead>
            <tbody id="bookingContainer">
            </tbody>
        </table>
        <button id="loadMore" onclick="loadMoreTable()">Load More...</button>
    </div>

<script>
    let count = 0;
    let container = document.getElementById("bookingContainer");
    let status;
    let offset = 10;

    function onChangeStatus(){
        let keyword = document.getElementById("searchBookings").value;
        if(keyword===""){
            fetchBookings();
        }
        else{
            searchTable();
        }
    }

    function fetchBookings() {
        offset = 10;
        count = 0;
        status = document.getElementById("statusFilter").value;
        fetch("bookings?action=portion&limit=10&offset=0&status="+status)
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
            .catch(error => console.error("Error fetching booking: ", error));
    }

    window.onload = fetchBookings;

    function loadMoreTable(){
        status = document.getElementById("statusFilter").value;
        fetch("bookings?action=portion&limit=10&offset="+offset+"&status=" + status)
            .then(response => response.json())
            .then(data => {
                count = data.length
                loadTable(data);

                if(count===10){
                    document.getElementById("loadMore").style.display = "block";
                }else
                    document.getElementById("loadMore").style.display = "none";
                offset += 10

            }).catch(error => console.error("Error fetching booking: ", error));
    }

    function searchTable(){
        status = document.getElementById("statusFilter").value;
        let keyword = document.getElementById("searchBookings").value;
        if(keyword === ""){
            fetchBookings();
        }
        else{
            fetch("bookings?action=search&keyword="+keyword+"&status="+status)
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

            let customer = document.createElement("td");
            customer.textContent = u.customer.first_name + " " + u.customer.last_name;

            let bookingUser = document.createElement("td");
            bookingUser.textContent = u.booking_user.first_name + " " + u.booking_user.last_name;

            let vehicle = document.createElement("td");
            vehicle.textContent = u.vehicle.plate_no;

            let amount = document.createElement("td");
            amount.textContent = u.transaction.amount;

            let pickup = document.createElement("td");
            pickup.textContent = u.location.pickup;

            let destination = document.createElement("td");
            destination.textContent = u.location.destination;

            let distance = document.createElement("td");
            distance.textContent = u.distance + " KM";

            let bookedAt = document.createElement("td");
            bookedAt.textContent = u.booked_at;

            newTray.appendChild(customer);
            newTray.appendChild(bookingUser);
            newTray.appendChild(vehicle);
            newTray.appendChild(amount);
            newTray.appendChild(pickup);
            newTray.appendChild(destination);
            newTray.appendChild(distance);
            newTray.appendChild(bookedAt);

            container.appendChild(newTray);
        })

    }
</script>
</body>
</html>
