<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 13/03/2025
  Time: 09:39 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega City Cab - Services</title>
</head>
<body>
<%@include file="Top_Navigation_Bar.jsp"%>

<div class="content">

    <section class="services">
        <h1>Our Services</h1>
        <p>We offer the best cab services in the city to meet your needs.</p>

        <div class="service-container">
            <div class="service-box">
                <h2>Standard Taxi</h2>
                <p>Affordable rides with professional drivers.</p>
            </div>

            <div class="service-box">
                <h2>Luxury Rides</h2>
                <p>Premium cars for business and special occasions.</p>
            </div>

            <div class="service-box">
                <h2>Airport Transfers</h2>
                <p>On-time pickups and drop-offs for flights.</p>
            </div>

            <div class="service-box">
                <h2>Ride Sharing</h2>
                <p>Cost-effective shared rides to save money.</p>
            </div>

            <div class="service-box">
                <h2>Corporate Rides</h2>
                <p>Reliable taxi services for business professionals.</p>
            </div>

            <div class="service-box">
                <h2>Outstation Travel</h2>
                <p>Long-distance travel with comfort and safety.</p>
            </div>
        </div>
    </section>

    <footer>
        <p>&copy; 2025 Mega City Cab. All rights reserved.</p>
    </footer>

</div>

</body>

<style>
    .services {
        margin-top: 100px;
        padding: 50px 20px;
        text-align: center;
    }

    .services h1 {
        font-size: 36px;
        color: #333;
    }

    .services p {
        font-size: 18px;
        color: #666;
    }

    /* Service Boxes */
    .service-container {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 20px;
        max-width: 1200px;
        margin: auto;
        padding: 20px;
    }

    .service-box {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        text-align: center;
        transition: transform 0.3s ease;
    }

    .service-box:hover {
        transform: scale(1.05);
    }

    .service-box img {
        width: 100%;
        height: 180px;
        border-radius: 10px;
        object-fit: cover;
    }

    .service-box h2 {
        font-size: 22px;
        color: #333;
        margin: 15px 0;
    }

    .service-box p {
        font-size: 16px;
        color: #666;
    }

    /* Footer */
    footer {
        background: #333;
        color: white;
        padding: 10px;
        margin-top: 50px;
    }
</style>
</html>
