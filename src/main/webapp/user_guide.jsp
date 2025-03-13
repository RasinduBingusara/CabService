<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 13/03/2025
  Time: 10:48 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Guide</title>
</head>
<body>
<%@include file="Top_Navigation_Bar.jsp"%>
<div class="content">
  <section class="user-guide">
    <h1>User Guide</h1>
    <p>Follow these simple steps to use the Mega City Cab service.</p>

    <div class="guide-steps">
      <div class="step">
        <h2>1. Sign Up / Log In</h2>
        <p>Create an account by providing your name, email, and phone number, or log in if you already have an account.</p>
      </div>

      <div class="step">
        <h2>2. Book a Ride</h2>
        <p>Enter your pickup and drop-off locations, select a vehicle type, and confirm your booking.</p>
      </div>

      <div class="step">
        <h2>3. Track Your Ride</h2>
        <p>Once a driver is assigned, track the ride in real-time and receive notifications.</p>
      </div>

      <div class="step">
        <h2>4. Payment & Ratings</h2>
        <p>Pay securely via cash or online and rate your driver after the ride.</p>
      </div>
    </div>

    <h2>Frequently Asked Questions</h2>
    <div class="faq-section">
      <div class="faq">
        <h3 class="faq-question">How do I cancel a booking?</h3>
        <p class="faq-answer">Go to 'My Bookings', select the ride, and click 'Cancel'. A cancellation fee may apply.</p>
      </div>

      <div class="faq">
        <h3 class="faq-question">What payment methods are accepted?</h3>
        <p class="faq-answer">We accept cash, credit/debit cards, and online wallets.</p>
      </div>

      <div class="faq">
        <h3 class="faq-question">How do I contact customer support?</h3>
        <p class="faq-answer">Visit the 'Contact Us' page or call our helpline at +94 123 456 789.</p>
      </div>
    </div>
  </section>

  <footer>
    <p>&copy; 2025 Mega City Cab. All rights reserved.</p>
  </footer>
</div>


<script>
  $(document).ready(function(){
    $(".faq-answer").hide();
    $(".faq-question").click(function(){
      $(this).next(".faq-answer").slideToggle(200);
    });
  });
</script>

</body>

<style>
  .user-guide {
    margin-top: 100px;
    padding: 50px 20px;
    text-align: center;
  }

  .user-guide h1 {
    font-size: 36px;
    color: #333;
  }

  .user-guide p {
    font-size: 18px;
    color: #666;
  }

  .guide-steps {
    max-width: 800px;
    margin: auto;
    text-align: left;
  }

  .step {
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    margin: 10px 0;
  }

  .step h2 {
    font-size: 24px;
    color: #333;
  }

  .step p {
    font-size: 16px;
    color: #666;
  }

  .faq-section {
    max-width: 800px;
    margin: auto;
    text-align: left;
    padding: 20px;
  }

  .faq {
    background: white;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.1);
    margin-bottom: 10px;
  }

  .faq-question {
    font-size: 18px;
    font-weight: bold;
    cursor: pointer;
  }

  .faq-answer {
    font-size: 16px;
    color: #666;
    display: none;
    padding-top: 5px;
  }

  footer {
    background: #333;
    color: white;
    padding: 10px;
    margin-top: 50px;
  }
</style>
</html>
