<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 13/03/2025
  Time: 09:45 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - Mega City Cab</title>
</head>
<body>


<%@include file="Top_Navigation_Bar.jsp"%>

<div class="content">
    <section class="about">
        <h1>About Mega City Cab</h1>
        <p>Providing reliable and safe taxi services for your convenience.</p>

        <div class="about-content">
            <p>At Mega City Cab, our mission is to offer fast, secure, and affordable rides to our customers. Our dedicated team of professionals ensures that you reach your destination safely and on time.</p>
        </div>

        <h2>Why Choose Us?</h2>
        <div class="features">
            <div class="feature-box">
                <i class="fas fa-car"></i>
                <h3>Comfortable Rides</h3>
                <p>Enjoy smooth and luxurious rides with well-maintained vehicles.</p>
            </div>

            <div class="feature-box">
                <i class="fas fa-user-shield"></i>
                <h3>Safety First</h3>
                <p>All our drivers are background-checked and professionally trained.</p>
            </div>

            <div class="feature-box">
                <i class="fas fa-clock"></i>
                <h3>24/7 Availability</h3>
                <p>Book a ride anytime, anywhere, with our round-the-clock services.</p>
            </div>
        </div>
    </section>

    <section class="team">
        <h2>Meet Our Team</h2>
        <div class="team-container">
            <div class="team-member">
                <h3>John Doe</h3>
                <p>Founder & CEO</p>
            </div>

            <div class="team-member">
                <h3>Jane Smith</h3>
                <p>Operations Manager</p>
            </div>

            <div class="team-member">
                <h3>Mike Johnson</h3>
                <p>Customer Support</p>
            </div>
        </div>
    </section>

    <footer>
        <p>&copy; 2025 Mega City Cab. All rights reserved.</p>
    </footer>
</div>


</body>

<style>
    .about {
        margin-top: 100px;
        padding: 50px 20px;
        text-align: center;
    }

    .about h1 {
        font-size: 36px;
        color: #333;
    }

    .about p {
        font-size: 18px;
        color: #666;
    }

    .about-content {
        max-width: 900px;
        margin: auto;
        text-align: center;
    }

    .about-content img {
        width: 100%;
        max-width: 600px;
        border-radius: 10px;
        margin-top: 20px;
    }

    /* Features */
    .features {
        display: flex;
        justify-content: center;
        gap: 20px;
        max-width: 1200px;
        margin: auto;
        padding: 20px;
        flex-wrap: wrap;
    }

    .feature-box {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        text-align: center;
        width: 300px;
    }

    .feature-box i {
        font-size: 40px;
        color: #ffcc00;
        margin-bottom: 10px;
    }

    .feature-box h3 {
        font-size: 22px;
        color: #333;
    }

    .feature-box p {
        font-size: 16px;
        color: #666;
    }

    /* Team Section */
    .team {
        padding: 50px 20px;
        text-align: center;
        background: #fff;
    }

    .team h2 {
        font-size: 32px;
        color: #333;
    }

    .team-container {
        display: flex;
        justify-content: center;
        gap: 20px;
        max-width: 1000px;
        margin: auto;
        flex-wrap: wrap;
    }

    .team-member {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        text-align: center;
        width: 250px;
    }

    .team-member img {
        width: 100%;
        max-width: 200px;
        border-radius: 50%;
    }

    .team-member h3 {
        font-size: 20px;
        color: #333;
        margin-top: 10px;
    }

    .team-member p {
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
