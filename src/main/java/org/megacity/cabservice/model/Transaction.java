package org.megacity.cabservice.model;

public class Transaction {

    private String id;
    private double amount;
    private String paymentMethod;
    private String paidAt;

    public Transaction() {
    }

    public Transaction(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Transaction(String id, double amount, String paymentMethod) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public String getId() {
        return id;
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
