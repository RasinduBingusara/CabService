<%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 08/03/2025
  Time: 01:19 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="payment-container">
    <h2>Secure Payment</h2>

    <form action="payment" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="booking_id" value='<%= request.getParameter("booking_id")%>'>
        <input type="hidden" name="transaction_id" value='<%= request.getParameter("transaction_id")%>'>
        <input type="hidden" name="amount" value='<%= request.getParameter("amount")%>'>
        <input type="hidden" name="method" value="Card">
        <div class="form-group">
            <label for="cardholder">Cardholder Name:</label>
            <input type="text" id="cardholder" name="card_holder" placeholder="John Doe" required>
        </div>

        <div class="form-group">
            <label for="cardnumber">Card Number:</label>
            <input type="text" id="cardnumber" name="card_number" maxlength="19" placeholder="1234 5678 9012 3456" required oninput="formatCardNumber(this)">
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="expiremonth">Expiry Month:</label>
                <select id="expiremonth" name="expire_month" required>
                    <option value="">MM</option>
                    <% for(int i=1; i<=12; i++) { %>
                    <option value="<%= String.format("%02d", i) %>"><%= String.format("%02d", i) %></option>
                    <% } %>
                </select>
            </div>

            <div class="form-group">
                <label for="expireyear">Expiry Year:</label>
                <select id="expireyear" name="expire_year" required>
                    <option value="">YY</option>
                    <%
                        int year = java.time.Year.now().getValue();
                        for(int i=0; i<10; i++) {
                    %>
                    <option value="<%= year + i %>"><%= year + i %></option>
                    <% } %>
                </select>
            </div>

            <div class="form-group">
                <label for="cvv">CVV:</label>
                <input type="password" id="cvv" name="cvv" maxlength="3" placeholder="123" required oninput="validateCVV(this)">
            </div>
        </div>

        <button type="submit" class="pay-btn">Pay Now</button>
    </form>
</div>

<script>
    function formatCardNumber(input) {
        let value = input.value.replace(/\D/g, '').substring(0, 16);
        value = value.replace(/(.{4})/g, '$1 ').trim();
        input.value = value;
    }

    function validateCVV(input) {
        input.value = input.value.replace(/\D/g, '').substring(0, 3);
    }

    function validateForm() {
        let cardNumber = document.getElementById("cardnumber").value.replace(/\s/g, '');
        if (cardNumber.length !== 16) {
            alert("Card number must be 16 digits.");
            return false;
        }
        return true;
    }
</script>
</body>

<style>
    body {
        font-family: Arial, sans-serif;
        background: #f5f5f5;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .payment-container {
        background: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        width: 350px;
        text-align: center;
    }

    h2 {
        margin-bottom: 15px;
        color: #333;
    }

    .form-group {
        margin-bottom: 15px;
        text-align: left;
    }

    label {
        font-size: 14px;
        font-weight: bold;
        color: #555;
        display: block;
    }

    input, select {
        width: 100%;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 5px;
        font-size: 16px;
        box-sizing: border-box;
    }

    .form-row {
        display: flex;
        justify-content: space-between;
        gap: 10px;
    }

    .pay-btn {
        width: 100%;
        background: #28a745;
        color: white;
        border: none;
        padding: 12px;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: 0.3s;
    }

    .pay-btn:hover {
        background: #218838;
    }

</style>
</html>
