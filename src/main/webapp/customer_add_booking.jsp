<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 04/03/2025
  Time: 02:59 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Booking</title>
    <link rel="stylesheet" href="CSS/add_booking.css">
</head>
<body>
<%@include file="Top_Navigation_Bar.jsp"%>
<%@include file="customer_dashboard_navigator.jsp"%>

<div class="content">
    <div class="booking-container">
        <h2>Add New Booking</h2>

        <form action="customer_bookings?action=add" method="post">

            <label style="color: red"><%= request.getAttribute("error")!=null? request.getAttribute("error"):""%> </label>
            <div class="form-group">
                <label for="pickup_location">Pickup Location:</label>
                <input type="text" id="pickup_location" name="pickup_location" required>

                <label for="destination">Destination:</label>
                <input type="text" id="destination" name="destination" required>
            </div>

            <div class="form-group">
                <label for="distance">Distance:</label>
                <input type="number" id="distance" name="distance" step="0.01" onchange="onValueChange()" required>
            </div>

            <div class="selection-container">
                <h3>Select Vehicle</h3>
                <input type="text" id="searchVehicle" placeholder="Search vehicle..." onkeyup="filterTable('vehicleTable', 'searchVehicle')">

                <div>
                    <table id="vehicleTable">
                        <thead>
                        <tr>
                            <th>Select</th>
                            <th>Model</th>
                            <th>Plate Number</th>
                            <th>Seat Count</th>
                        </tr>
                        </thead>
                        <tbody id="table-container">
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="form-group" style="margin-top: 20px">
                <label>Estimated Price:</label>
                <p id="priceDisplay">Rs. 0.00</p>
            </div>

            <div class="form-group2" style="margin-top: 20px; display: none" id="payment_selection">
                <label>Payment Method:</label>
                <div><input type="radio" name="payment" value="Cash"> <p>Cash</p></div>
                <div><input type="radio" name="payment" value="Card"> <p>Card</p></div>
            </div>

            <div class="form-actions" style="display: none" id="submit_btn">
                <button type="submit" class="submit-btn">Add Booking</button>
            </div>
        </form>
        <div class="form-group" style="margin-top: 20px" id="calculate_btn">
            <button class="submit-btn" onclick="calculatePrice()">Calculate Price</button>
        </div>

    </div>
</div>

<script>

    function onValueChange(){
        document.getElementById("calculate_btn").setAttribute("style","display: block;margin-top: 20px");
        document.getElementById("submit_btn").setAttribute("style","display: none")
        document.getElementById("payment_selection").setAttribute("style","margin-top: 20px; display: none")
    }
    function calculatePrice(){
        let pickup = document.getElementById("pickup_location").value;
        let destination = document.getElementById("destination").value;
        let distance = document.getElementById("distance").value;
        let selectedVehicleId = document.querySelector('input[name="vehicle"]:checked').value;
        let priceDisplay = document.getElementById("priceDisplay");

        if(distance!=='' && selectedVehicleId!=null && pickup!=='' && destination!==''){
            console.log("distance: "+ distance+ "/ vehicle Id: " + selectedVehicleId);
            console.log("bookings?action=calculate&distance="+distance+"&vehicle_id="+selectedVehicleId);

            fetch("bookings?action=calculate&distance="+distance+"&vehicle_id="+selectedVehicleId)
                .then(response => response.json())
                .then(data => {
                    priceDisplay.textContent = "Rs. " + data.total;
                    document.getElementById("submit_btn").setAttribute("style","display: block")
                    document.getElementById("payment_selection").setAttribute("style","display: block")
                    document.getElementById("calculate_btn").setAttribute("style","display: none")
                })
                .catch(error => console.error("Error fetching total:", error));
        }
        else
            alert("Every fields must be filled or selected")
    }

    function fetchVehicles() {

        fetch("vehicles?action=filter&status=Active")
            .then(response => response.json())
            .then(data => {
                let container = document.getElementById("table-container")
                container.innerHTML = "";

                data.forEach(u =>{
                    let newTray = document.createElement("tr");

                    let selector = document.createElement("td");
                    let input = document.createElement("input");
                    input.setAttribute("type","radio");
                    input.setAttribute("name", "vehicle");
                    input.setAttribute("value",u.vehicle_id);
                    selector.appendChild(input);

                    let model = document.createElement("td");
                    model.textContent = u.model.model_name;

                    let plateNo = document.createElement("td");
                    plateNo.textContent = u.plate_no;

                    let seatCount = document.createElement("td");
                    seatCount.textContent = u.seat_count;

                    newTray.appendChild(selector);
                    newTray.appendChild(model);
                    newTray.appendChild(plateNo);
                    newTray.appendChild(seatCount);
                    container.appendChild(newTray);
                })

                const radioButtons = document.querySelectorAll('input[name="vehicle"]');

                radioButtons.forEach(radio => {
                    radio.addEventListener('change', function() {
                        onValueChange();
                    });
                });

            })
            .catch(error => console.error("Error fetching vehicles:", error));
    }

    window.onload = fetchVehicles;

</script>

</body>
</html>
