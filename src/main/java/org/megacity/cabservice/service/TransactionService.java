package org.megacity.cabservice.service;

import org.megacity.cabservice.model.Bill;
import org.megacity.cabservice.model.Transaction;
import org.megacity.cabservice.repository.TransactionRepo;

import java.util.List;

public class TransactionService {

    private TransactionRepo transactionRepo = new TransactionRepo();

    public boolean updateTransaction(int transactionId) {
        return transactionRepo.updatePaidTime(transactionId);
    }

    public boolean makeTransactionWithCard(String cardHolder, String cardNumber, String expiryMonth, String expiryYear, String cvv, double amount) {
        System.out.println("Payment: Paid " + amount + " by " + cardHolder + " using Card: " + cardNumber + " " + expiryMonth + " " + expiryYear + " " + cvv);
        System.out.println("Payment: Paid " + amount + " using Card: " + cardNumber);
        return true;
    }

    public List<Double> getMonthlyRevenues() {
        return transactionRepo.getMonthlyRevenues();
    }

    public double getMonthlyRevenue() {
        double price = transactionRepo.getMonthlyRevenue();
        double factor = Math.pow(10, 2);
        return Math.ceil(price * factor) / factor;
    }

    public Bill getBillByBookingId(int bookingId) {
        return transactionRepo.getBillByBookingId(bookingId);
    }
}
