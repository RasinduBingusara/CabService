<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 04/03/2025
  Time: 04:03 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Booking Management</title>
    <link rel="stylesheet" href="CSS/manage_booking.css">
</head>
<body>
<%@include file="Top_Navigation_Bar.jsp"%>
<%@include file="driver_dashboard_navigator.jsp"%>

<div class="content">

    <div class="booking-container">
        <h2>Driver Booking Management</h2>

        <div class="search-filter">
            <input type="text" id="searchBooking" placeholder="Search bookings..." onkeyup="filterTable()">
        </div>

        <div class="table-container">
            <table id="bookingTable">
                <thead>
                <tr>
                    <th>Vehicle (Plate No.)</th>
                    <th>Pickup</th>
                    <th>Destination</th>
                    <th>Distance</th>
                    <th>Amount</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody id="table-container">
                </tbody>
            </table>
        </div>
    </div>
</div>

<script>
    function fetchBookings() {

        fetch("driver_bookings?action=requests")
            .then(response => response.json())
            .then(data => {
                let container = document.getElementById("table-container")
                container.innerHTML = "";

                data.forEach(u =>{
                    let newTray = document.createElement("tr");

                    let vehicle = document.createElement("td");
                    let pickup = document.createElement("td");
                    let destination = document.createElement("td");
                    let distance = document.createElement("td");
                    let amount = document.createElement("td");
                    let status = document.createElement("td");
                    let action = document.createElement("td");

                    //for cancel button
                    let form = document.createElement("form");
                    form.setAttribute("action","edit");
                    form.setAttribute("method","post");

                    let hiddenInput = document.createElement("input");
                    hiddenInput.setAttribute("type","hidden");
                    hiddenInput.setAttribute("name",u.booking_id);
                    let button = document.createElement("input")
                    button.setAttribute("type","submit");
                    button.setAttribute("class","cancel-btn");
                    button.setAttribute("value","Cancel");

                    //for pay button
                    let form2 = document.createElement("form");
                    form2.setAttribute("action","accept");
                    form2.setAttribute("method","post");

                    let form3 = document.createElement("form");
                    form3.setAttribute("action","paid");
                    form3.setAttribute("method","post");

                    let hiddenInput2 = document.createElement("input");
                    hiddenInput2.setAttribute("type","hidden");
                    hiddenInput2.setAttribute("name",u.booking_id);
                    let button2 = document.createElement("input")
                    button2.setAttribute("type","submit");
                    button2.setAttribute("class","edit-btn");
                    button2.setAttribute("value","Accept");

                    let hiddenInput3 = document.createElement("input");
                    hiddenInput3.setAttribute("type","hidden");
                    hiddenInput3.setAttribute("name",u.booking_id);
                    let button3 = document.createElement("input")
                    button3.setAttribute("type","submit");
                    button3.setAttribute("class","edit-btn");
                    button3.setAttribute("value","Paid in Cash");


                    switch (u.status){
                        case "Pending":
                            form.appendChild(hiddenInput);
                            form.appendChild(button);
                            action.appendChild(form);

                            form2.appendChild(hiddenInput2);
                            form2.appendChild(button2);
                            action.appendChild(form2);
                            break;
                        case "Confirmed":
                            form.appendChild(hiddenInput);
                            form.appendChild(button);
                            action.appendChild(form);

                            form3.appendChild(hiddenInput3);
                            form3.appendChild(button3);
                            action.appendChild(form3);

                            break;
                        case "Paid":
                            form.setAttribute("action","edit");
                            button.setAttribute("value","Cancel");
                    }


                    form.appendChild(hiddenInput);
                    form.appendChild(button);
                    action.appendChild(form);

                    vehicle.textContent = u.vehicle.plate_no;
                    pickup.textContent =  u.location.pickup;
                    destination.textContent = u.location.destination;
                    distance.textContent = u.distance;
                    amount.textContent = u.transaction.amount;
                    status.textContent = u.status;


                    newTray.appendChild(vehicle);
                    newTray.appendChild(pickup);
                    newTray.appendChild(destination);
                    newTray.appendChild(distance);
                    newTray.appendChild(amount);
                    newTray.appendChild(status);
                    newTray.appendChild(action);
                    container.appendChild(newTray);
                })

            })
            .catch(error => console.error("Error fetching vehicles:", error));
    }

    window.onload = fetchBookings;
</script>

</body>
</html>
