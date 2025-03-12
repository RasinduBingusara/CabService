<%@ page import="org.megacity.cabservice.model.Booking" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
  <%@include file="dashboard_navigator.jsp"%>

  <div class="content">
      <div class="dashboard-container">
          <h2>Admin Dashboard Report</h2>

          <div class="summary-container">
              <div class="summary-card">
                  <h3>Total Bookings</h3>
                  <p>${totalBookings}</p>
              </div>
              <div class="summary-card">
                  <h3>Active Drivers</h3>
                  <p>${activeDrivers}</p>
              </div>
              <div class="summary-card">
                  <h3>Available Vehicles</h3>
                  <p>${availableVehicles}</p>
              </div>
              <div class="summary-card">
                  <h3>Total Revenue</h3>
                  <p>$${totalRevenue}</p>
              </div>
          </div>

          <div class="chart-container">
              <h3>Monthly Revenue</h3>
              <canvas id="revenueChart"></canvas>
          </div>

          <div class="recent-bookings">
              <h3>Recent Bookings</h3>
              <table>
                  <thead>
                  <tr>
                      <th>Booking ID</th>
                      <th>Customer</th>
                      <th>Pickup</th>
                      <th>Destination</th>
                      <th>Status</th>
                  </tr>
                  </thead>
                  <tbody>
                  <%
                      List<Booking> bookings = (List<Booking>) request.getAttribute("recentBookings");
                      if(bookings!=null){
                          for (Booking booking: bookings){
                  %>
                  <tr>
                      <td><%= booking.getBookingId()%></td>
                      <td><%= booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName()%></td>
                      <td><%= booking.getPickupLocation()%></td>
                      <td><%= booking.getDestination()%></td>
                      <td><%= booking.getStatus()%></td>
                  </tr>
                  <%
                      }}
                  %>
                  </tbody>
              </table>
          </div>
      </div>

      <script>
          window.onload = function () {
              const monthlyRevenueData = ${monthlyRevenueData};

              const ctx = document.getElementById('revenueChart').getContext('2d');
              new Chart(ctx, {
                  type: 'line',
                  data: {
                      labels: ["Mar", "Apr", "May", "Jun"],
                      datasets: [{
                          label: 'Revenue ($)',
                          data: monthlyRevenueData,
                          backgroundColor: 'rgba(54, 162, 235, 0.2)',
                          borderColor: 'rgba(54, 162, 235, 1)',
                          borderWidth: 2
                      }]
                  },
                  options: {
                      responsive: true,
                      maintainAspectRatio: false
                  }
              });
          };

      </script>
  </div>
</body>

<style>
    .dashboard-container {
        background: white;
        padding: 20px;
        width: 80%;
        margin: auto;
        border-radius: 10px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }

    h2, h3 {
        text-align: center;
        color: #333;
    }

    .summary-container {
        display: flex;
        justify-content: space-around;
        margin: 20px 0;
    }

    .summary-card {
        background: #ffcc00;
        color: white;
        padding: 20px;
        border-radius: 8px;
        text-align: center;
        width: 20%;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    }

    .summary-card h3 {
        margin-bottom: 10px;
    }

    .chart-container {
        background: #f9f9f9;
        padding: 20px;
        border-radius: 10px;
        margin-bottom: 20px;
        text-align: center;
    }

    canvas {
        width: 100%;
        max-height: 300px;
    }

    .recent-bookings {
        margin-top: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        background: #fff;
    }

    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background: #ffcc00;
        color: white;
    }
</style>
</html>
