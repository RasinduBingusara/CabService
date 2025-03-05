package org.megacity.cabservice.model;

public class Transaction {

    private String id;
    private String bookingId;
    private double amount;
    private String paymentMethod;
    private String paidAt;

    public Transaction() {
    }

    public Transaction(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Transaction(String id, String bookingId, double amount, String paymentMethod, String paidAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paidAt = paidAt;
    }

    public String getId() {
        return id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaidAt() {
        return paidAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
    }
}
