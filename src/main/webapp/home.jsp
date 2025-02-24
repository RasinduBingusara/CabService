<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 04/02/2025
  Time: 11:07 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>

<body>
<%@ include file="Top_Navigation_Bar.jsp"%>

<section class="hero">
    <div class="hero-content">
        <h1>Reliable and Affordable Cab Services</h1>
        <p>Book your ride with Mega City Cab and travel with comfort and safety.</p>
        <a href="#book" class="btn">Book a Ride</a>
    </div>
</section>

<section id="services" class="services">
    <h2>Our Services</h2>
    <div class="service-container">
        <div class="service-box">
            <h3>City Rides</h3>
            <p>Quick and affordable city rides at your convenience.</p>
        </div>
        <div class="service-box">
            <h3>Airport Transfers</h3>
            <p>Reliable airport pickups and drop-offs.</p>
        </div>
        <div class="service-box">
            <h3>Luxury Rides</h3>
            <p>Experience comfort with our premium car selection.</p>
        </div>
    </div>
</section>

<footer>
    <p>&copy; 2025 Mega City Cab. All Rights Reserved.</p>
</footer>

<style>
    .hero {
        background-color: dodgerblue;
        color: white;
        text-align: center;
        padding: 100px 20px;
    }
    .btn {
        display: inline-block;
        background: #333;
        color: #fff;
        padding: 10px 20px;
        text-decoration: none;
        border-radius: 5px;
        margin-top: 10px;
    }
    .btn:hover {
        background: #ffcc00;
        color: black;
    }
    .services {
        padding: 50px 20px;
        text-align: center;
        background: #f4f4f4;
    }
    .service-container {
        display: flex;
        justify-content: center;
        gap: 20px;
    }
    .service-box {
        background: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        width: 250px;
    }
    footer {
        text-align: center;
        padding: 15px;
        background: #333;
        color: white;
    }
</style>
</body>
</html>
