package org.megacity.cabservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.megacity.cabservice.model.Bill;
import org.megacity.cabservice.model.Payments.CardPayment;
import org.megacity.cabservice.model.Payments.CashPayment;
import org.megacity.cabservice.model.Payments.PaymentMethod;
import org.megacity.cabservice.service.TransactionService;

import java.io.IOException;

public class PaymentController extends HttpServlet {

    private TransactionService transactionService = new TransactionService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        int transactionId = Integer.parseInt(request.getParameter("transaction_id"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        PaymentMethod paymentMethod = new PaymentMethod() {
            @Override
            public boolean pay(double amount) {
                System.out.println("Payment method executed");
                return false;
            }
        };

        switch (method) {
            case "Card":
                String cardNumber = request.getParameter("card_number");
                String cardHolder = request.getParameter("card_holder");
                String expiryMonth = request.getParameter("expiry_month");
                String expiryYear = request.getParameter("expiry_year");
                String cvv = request.getParameter("cvv");
                System.out.println("Card number: " + cardNumber);
                paymentMethod = new CardPayment(cardHolder,cardNumber,expiryMonth,expiryYear,cvv);
                break;
            case "Cash":
                paymentMethod = new CashPayment();
        }
        if(paymentMethod.pay(amount)){
            if(transactionService.updateTransaction(transactionId)) {
                if(request.getParameter("type").equals("driver") ) {
                    request.getRequestDispatcher("driver_bookings?action=paid").forward(request, response);
                }
                else{
                    request.getRequestDispatcher("customer_bookings?action=paid").forward(request, response);
                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "gateway":
                request.getRequestDispatcher("payment_gateway.jsp").forward(request, response);
                break;
            case "bill":
                int bookingId = Integer.parseInt(request.getParameter("booking_id"));
                Bill bill = transactionService.getBillByBookingId(bookingId);
                request.setAttribute("bill", bill);
                request.getRequestDispatcher("bill.jsp").forward(request, response);
                break;
        }

    }

}
