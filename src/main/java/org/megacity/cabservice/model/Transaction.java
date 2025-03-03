package org.megacity.cabservice.model;

public class Transaction {

    private String id;
    private String bookingId;
    private float amount;
    private String paymentMethod;
    private String paidAt;

    public Transaction() {
    }

    public Transaction(String id, float amount) {
        this.id = id;
        this.amount = amount;
    }

    public Transaction(String id, String bookingId, float amount, String paymentMethod, String paidAt) {
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

    public float getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaidAt() {
        return paidAt;
    }
}
