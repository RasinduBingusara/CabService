 <%--
  Created by IntelliJ IDEA.
  User: Rasindu
  Date: 08/03/2025
  Time: 03:47 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <html>
 <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Invoice</title>
     <link rel="stylesheet" href="bill.css">
 </head>
 <body>

 <div class="invoice-container" id="invoice">
     <header>
         <h1>Invoice</h1>
         <p>Date: <%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %></p>
     </header>

     <section class="customer-details">
         <h2>Customer Information</h2>
         <p><strong>Name:</strong> ${bill.name}</p>
         <p><strong>Email:</strong> ${bill.email}</p>
         <p><strong>Phone:</strong> ${bill.contactNumber}</p>
     </section>

     <section class="trip-details">
         <h2>Trip Details</h2>
         <p><strong>Pickup Location:</strong> ${bill.pickupLocation}</p>
         <p><strong>Drop-off Location:</strong> ${bill.dropOffLocation}</p>
         <p><strong>Distance:</strong> ${bill.distance} km</p>
     </section>

     <section class="invoice-details">
         <h2>Billing Details</h2>
         <table>
             <tbody>
             <tr>
                 <td>Subtotal:</td>
                 <td>$${bill.fare.subTotal}</td>
             </tr>
             <tr>
                 <td>Discount (${bill.fare.discount}%):</td>
                 <td>$${bill.fare.afterDiscount}</td>
             </tr>
             <tr class="total-row">
                 <td><strong>Grand Total (Tax ${bill.fare.tax}%):</strong></td>
                 <td><strong>$${bill.fare.total}</strong></td>
             </tr>
             </tbody>
         </table>
     </section>

     <footer>
         <p>Thank you for choosing Mega City Cab!</p>
         <button onclick="printInvoice()">Print Invoice</button>
     </footer>
 </div>

 <script>
     function printInvoice() {
         let originalContent = document.body.innerHTML;
         let invoiceContent = document.getElementById("invoice").outerHTML;

         document.body.innerHTML = invoiceContent;
         window.print();
         document.body.innerHTML = originalContent;
     }
 </script>

 </body>
 <style>
     body {
         font-family: Arial, sans-serif;
         margin: 0;
         padding: 20px;
         background: #f4f4f4;
         display: flex;
         justify-content: center;
     }

     .invoice-container {
         background: #fff;
         padding: 20px;
         width: 80%;
         max-width: 700px;
         border-radius: 8px;
         box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
     }

     header h1 {
         text-align: center;
         color: #333;
     }

     .customer-details, .trip-details, .invoice-details {
         margin-top: 20px;
         padding: 10px;
         background: #f9f9f9;
         border-radius: 5px;
     }

     h2 {
         border-bottom: 2px solid #ddd;
         padding-bottom: 5px;
     }

     table {
         width: 100%;
         border-collapse: collapse;
         margin-top: 10px;
     }

     td {
         padding: 10px;
         text-align: left;
         border-bottom: 1px solid #ddd;
     }

     .total-row td {
         font-size: 1.2em;
         font-weight: bold;
     }

     footer {
         text-align: center;
         margin-top: 20px;
     }

     button {
         background: #28a745;
         color: white;
         padding: 10px 15px;
         border: none;
         border-radius: 5px;
         cursor: pointer;
         font-size: 16px;
     }

     button:hover {
         background: #218838;
     }

 </style>
 </html>